package com.vero.woopai.features.history.domain.useCase

import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.core.domain.repository.PlanRepository
import javax.inject.Inject

class GetPlansUseCase @Inject constructor(
    private val planRepository: PlanRepository
) {
    suspend operator fun invoke() : List<PlanModel> {
        return planRepository.getPlans()
    }

}