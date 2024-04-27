package com.vero.woopai.features.info.presentation

sealed interface InfoEvent {
    object SaveText: InfoEvent
    object SavePlans: InfoEvent
    object GeneratePlans: InfoEvent
    class UpdateText(val text: String): InfoEvent
}
