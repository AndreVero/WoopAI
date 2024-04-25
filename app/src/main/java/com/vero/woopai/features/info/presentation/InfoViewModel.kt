package com.vero.woopai.features.info.presentation

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.woopai.R
import com.vero.woopai.features.info.domain.useCase.GetPlanSuggestionsUseCase
import com.vero.woopai.features.info.domain.useCase.SavePlanModelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val getPlanSuggestionsUseCase: GetPlanSuggestionsUseCase,
    private val insertPlanModel: SavePlanModelUseCase
) : ViewModel() {

    private val _uiEvents = Channel<InfoUiEvent>()
    val uiEvent = _uiEvents.receiveAsFlow()

    private val woopModel = WoopModel()

    var state by mutableStateOf(InfoState())
        private set

    fun onEvent(event: InfoEvent) {
        when (event) {
            InfoEvent.GetPlan -> getPlans()
            InfoEvent.Next -> openNextScreen()
        }
    }

    private fun getPlans() {
        viewModelScope.launch {
            getPlanSuggestionsUseCase(woopModel)
                .onSuccess {
                    state = state.copy(plans = it)
                }
                .onFailure {
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
                    currentScreenState = ScreenState.Plan,
                    text = ""
                )
            }

            ScreenState.Plan -> {
                viewModelScope.launch {
                    savePlanModel()
                    _uiEvents.send(InfoUiEvent.OpenHomeScreen)
                }
            }
        }
    }

    private suspend fun savePlanModel() {
        state.plans.forEach { planModel ->
            insertPlanModel(planModel)
        }
    }

}