package com.vero.woopai.features.info.presentation

import com.vero.woopai.core.domain.model.PlanModel

data class InfoState(
    val currentScreenState: ScreenState = ScreenState.Wish,
    val plan: PlanModel? = null,
    val text: String = "",
    val isMicWorking: Boolean = false,
)
