package com.vero.woopai.features.info.presentation

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(

) : ViewModel() {

    private val _uiEvents = Channel<InfoUiEvent>()
    val uiEvent = _uiEvents.receiveAsFlow()

    var state by mutableStateOf(InfoState())
        private set

    fun onEvent(event: InfoEvent) {
        when(event) {
            InfoEvent.GetPlan -> getPlan()
            InfoEvent.Next -> openNextScreen()
            InfoEvent.SaveWoopModel -> saveWoopModel()
        }
    }

    private fun getPlan() {

    }

    private fun openNextScreen() {

    }

    private fun saveWoopModel() {

    }


}