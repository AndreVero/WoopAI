package com.vero.woopai.core.domain.model

import java.time.LocalDateTime

data class PlanModel(
    val id: Int,
    val description: String,
    val time: LocalDateTime
)