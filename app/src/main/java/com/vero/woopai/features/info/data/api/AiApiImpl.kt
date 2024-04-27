package com.vero.woopai.features.info.data.api

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.vero.woopai.BuildConfig
import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.features.info.data.utils.AiResponseParser
import com.vero.woopai.features.info.data.utils.safeSuspendCall
import com.vero.woopai.features.info.domain.api.AiApi
import com.vero.woopai.features.info.presentation.WoopModel

class AiApiImpl : AiApi {

    override suspend fun getPlanSuggestion(woopModel: WoopModel): Result<List<PlanModel>> {
        return safeSuspendCall {
            val generativeModel = GenerativeModel(
                modelName = "gemini-pro",
                apiKey = BuildConfig.API_KEY
            )

            val prompt = "Write a story about a magic backpack."
            val response = generativeModel.generateContent(prompt)
            AiResponseParser.responseToPlanModel(response.text)
        }
    }

}