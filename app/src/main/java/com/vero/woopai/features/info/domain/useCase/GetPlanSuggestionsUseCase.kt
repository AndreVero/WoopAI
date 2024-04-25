package com.vero.woopai.features.info.domain.useCase

import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.features.info.domain.api.AiApi
import com.vero.woopai.features.info.presentation.WoopModel
import javax.inject.Inject

class GetPlanSuggestionsUseCase @Inject constructor(
    private val aiApi: AiApi
) {

    suspend operator fun invoke(woopModel: WoopModel) : Result<List<PlanModel>> {
        return aiApi.getPlanSuggestion(woopModel)
    }

}