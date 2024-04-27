package com.vero.woopai.features.info.presentation

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.woopai.R
import com.vero.woopai.features.info.domain.speech.SpeechHandler
import com.vero.woopai.features.info.domain.speech.SpeechRecognizerListener
import com.vero.woopai.features.info.domain.useCase.GetPlanSuggestionsUseCase
import com.vero.woopai.features.info.domain.useCase.SavePlanModelUseCase
import com.vero.woopai.ui.theme.BlackTextColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val getPlanSuggestionsUseCase: GetPlanSuggestionsUseCase,
    private val insertPlanModel: SavePlanModelUseCase,
    private val speechHandler: SpeechHandler,
) : ViewModel() {

    private val _uiEvents = Channel<InfoUiEvent>()
    val uiEvent = _uiEvents.receiveAsFlow()

    private val woopModel = WoopModel()

    var state by mutableStateOf(InfoState())
        private set

    init {
        speechHandler.init()
        speechHandler.setListener(object : SpeechRecognizerListener {
            override fun onResult(result: String) {
                state = state.copy(text = result, speechButtonColor = BlackTextColor)
            }

            override fun onReadyForSpeech() {
                state = state.copy(speechButtonColor = Color.Green)
            }
        })
    }

    fun onEvent(event: InfoEvent) {
        when (event) {
            InfoEvent.GeneratePlans -> getPlans()
            InfoEvent.SaveText -> openNextScreen()
            is InfoEvent.UpdateText -> updateText(event.text)
            InfoEvent.SavePlans -> savePlanModel()
            InfoEvent.StartSpeechToText -> startSpeechToText()
        }
    }

    private fun startSpeechToText() {
        speechHandler.startRecognition()
    }

    private fun updateText(text: String) {
        state = state.copy(text = text)
    }

    private fun getPlans() {
        state = state.copy(currentScreenState = ScreenState.Loading)
        viewModelScope.launch {
            getPlanSuggestionsUseCase(woopModel)
                .onSuccess {
                    state = state.copy(plan = it, currentScreenState = ScreenState.Plan)
                }
                .onFailure {
                    state = state.copy(
                        currentScreenState = ScreenState.Obstacle,
                        text = woopModel.obstacle
                    )
                    _uiEvents.send(InfoUiEvent.ShowError(R.string.default_error))
                }
        }
    }

    private fun openNextScreen() {
        when (state.currentScreenState) {
            ScreenState.Wish -> {
                woopModel.wish = state.text
                state = state.copy(
                    currentScreenState = ScreenState.Outcome,
                    text = ""
                )
            }

            ScreenState.Outcome -> {
                woopModel.outcome = state.text
                state = state.copy(
                    currentScreenState = ScreenState.Obstacle,
                    text = ""
                )
            }

            ScreenState.Obstacle -> {
                woopModel.obstacle = state.text
                state = state.copy(
                    currentScreenState = ScreenState.Loading,
                    text = ""
                )
                getPlans()
            }

            else -> Unit
        }
    }

    private fun savePlanModel() {
        viewModelScope.launch {
            state.plan?.let { insertPlanModel(it) }
            _uiEvents.send(InfoUiEvent.OpenHomeScreen)
        }
    }

    override fun onCleared() {
        speechHandler.onDestroy()
        super.onCleared()
    }

}