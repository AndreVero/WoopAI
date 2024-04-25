package com.vero.woopai.features.info.domain.useCase

import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.core.domain.repository.PlanRepository
import javax.inject.Inject

class SavePlanModelUseCase @Inject constructor(
    private val planRepository: PlanRepository
) {

    suspend operator fun invoke(planModel: PlanModel) {
        planRepository.insertPlan(planModel)
    }

}