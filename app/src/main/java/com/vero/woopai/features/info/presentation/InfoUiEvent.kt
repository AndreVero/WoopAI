package com.vero.woopai.features.info.presentation

sealed interface InfoUiEvent {
    class ShowError(val error: Int) : InfoUiEvent
    object OpenHomeScreen: InfoUiEvent

}