package com.vero.woopai.features.info.presentation.utils

import com.vero.woopai.R
import com.vero.woopai.features.info.presentation.ScreenState

object ScreenStateToTextParser {

    fun screenStateToHint(screenState: ScreenState) : Int {
        return when (screenState) {
            ScreenState.Obstacle -> R.string.add_obstacle_hint
            ScreenState.Outcome -> R.string.add_outcome_hint
            ScreenState.Wish -> R.string.add_wish_hint
            else -> R.string.unknown
        }
    }

    fun screenStateToButtonText(screenState: ScreenState) : Int {
        return when (screenState) {
            ScreenState.Obstacle -> R.string.add_obstacle_button
            ScreenState.Outcome -> R.string.add_outcome_button
            ScreenState.Wish -> R.string.add_wish_button
            else -> R.string.unknown
        }
    }

}