package com.vero.woopai.core.data.repository

import com.vero.woopai.core.data.local.dao.PlanDao
import com.vero.woopai.core.data.mapper.toPlanEntity
import com.vero.woopai.core.data.mapper.toPlanModel
import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.core.domain.repository.PlanRepository
import com.vero.woopai.core.data.utils.LocalDateTimeConverter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class PlanRepositoryImpl(private val dao: PlanDao) : PlanRepository {

    override suspend fun insertPlan(plan: PlanModel) {
        dao.insertPlans(plan.toPlanEntity())
    }

    override suspend fun deletePlan(plan: PlanModel) {
        dao.deletePlans(plan.toPlanEntity())
    }

    override suspend fun getPlans(): List<PlanModel> {
        return dao.loadAllPlans().map { it.toPlanModel() }
    }

    override suspend fun getPlansForDay(day: LocalDate): List<PlanModel> {
        val from = LocalDateTimeConverter.getEpochForUTC(day.atStartOfDay())
        val to = LocalDateTimeConverter.getEpochForUTC(
            LocalDateTime.of(day, LocalTime.of(23, 59))
        )

        return dao.loadPlansForDay(from, to).map { it.toPlanModel() }
    }

}