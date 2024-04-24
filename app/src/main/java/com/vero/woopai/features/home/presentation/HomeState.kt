package com.vero.woopai.features.home.presentation

import com.vero.woopai.core.domain.model.PlanModel

data class HomeState(
    val plans: List<PlanModel> = emptyList()
)
