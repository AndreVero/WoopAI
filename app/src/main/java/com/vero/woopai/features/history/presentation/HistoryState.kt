package com.vero.woopai.features.history.presentation

import com.vero.woopai.core.domain.model.PlanModel

data class HistoryState(
    val plans: List<PlanModel> = emptyList()
)
