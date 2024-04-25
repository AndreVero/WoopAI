package com.vero.woopai.features.info.presentation

sealed interface InfoEvent {
    object Next: InfoEvent
    object GetPlan: InfoEvent
}
