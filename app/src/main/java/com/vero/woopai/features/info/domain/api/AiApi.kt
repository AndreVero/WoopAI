package com.vero.woopai.features.info.domain.api

import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.features.info.presentation.WoopModel

interface AiApi {
    suspend fun getPlanSuggestion(
        woopModel: WoopModel
    ) : Result<List<PlanModel>>

}