package com.vero.woopai.features.home.domain.useCase

import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.core.domain.repository.PlanRepository
import java.time.LocalDate
import javax.inject.Inject

class GetPlansForDayUseCase @Inject constructor(
    private val planRepository: PlanRepository
) {

    suspend operator fun invoke(day: LocalDate) : List<PlanModel> {
        return planRepository.getPlansForDay(day)
    }

}