package com.vero.woopai.features.info.domain.api

import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.features.info.presentation.WoopModel

interface AiApi {
    suspend fun getPlanSuggestion(
        woopModel: WoopModel
    ) : Result<PlanModel>


    companion object {
        fun getWoopRequest(woop: WoopModel) : String {
            return "I'm using the WOOP system to improve my discipline and time management." +
                    "Build a practical plan according to WOOP rules for this Wish: ${woop.wish}," +
                    "this Outcome: ${woop.outcome}, and" +
                    "this Obstacle: ${woop.obstacle}." +
                    "Write three if-then plans on how to fix my problem."
        }
    }
}