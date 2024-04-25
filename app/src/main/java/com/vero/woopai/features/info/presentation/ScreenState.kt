package com.vero.woopai.features.info.presentation

sealed interface ScreenState {
    object Wish: ScreenState
    object Obstacle: ScreenState
    object Outcome: ScreenState
    object Plan: ScreenState
}
