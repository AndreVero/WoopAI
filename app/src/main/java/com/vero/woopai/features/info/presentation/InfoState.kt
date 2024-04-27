package com.vero.woopai.features.info.presentation

import androidx.compose.ui.graphics.Color
import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.ui.theme.BlackTextColor

data class InfoState(
    val currentScreenState: ScreenState = ScreenState.Wish,
    val plan: PlanModel? = null,
    val text: String = "",
    val speechButtonColor: Color = BlackTextColor,
)
