package com.vero.woopai.core.domain.repository

import com.vero.woopai.core.domain.model.PlanModel
import java.time.LocalDate

interface PlanRepository {

    suspend fun insertPlan(plan: PlanModel)

    suspend fun deletePlan(plan: PlanModel)

    suspend fun getPlans() : List<PlanModel>

    suspend fun getPlansForDay(day: LocalDate) : List<PlanModel>

}