package com.vero.woopai.features.info.di

import android.content.Context
import com.vero.woopai.features.info.data.api.AiApiImpl
import com.vero.woopai.features.info.data.speech.SpeechHandlerImpl
import com.vero.woopai.features.info.domain.api.AiApi
import com.vero.woopai.features.info.domain.speech.SpeechHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn()
object InfoModule {

    @Provides
    @Singleton
    fun provideSpeechHandler(@ApplicationContext context: Context) : SpeechHandler {
        return SpeechHandlerImpl(context)
    }

    @Provides
    @Singleton
    fun provideAiPlanRepository() : AiApi {
        return AiApiImpl()
    }

}