package com.vero.woopai.features.info.presentation

import com.vero.woopai.core.domain.model.PlanModel

data class InfoState(
    val currentScreenState: ScreenState = ScreenState.Wish,
    val plans: List<PlanModel> = emptyList(),
    val text: String = ""
)
