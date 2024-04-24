package com.vero.woopai.core.di

import android.content.Context
import androidx.room.Room
import com.vero.woopai.core.data.local.PlanDatabase
import com.vero.woopai.core.data.repository.PlanRepositoryImpl
import com.vero.woopai.core.domain.repository.PlanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlanRepository(db: PlanDatabase) : PlanRepository {
        return PlanRepositoryImpl(db.planDao())
    }

    @Provides
    @Singleton
    fun provideAgendaDatabase(@ApplicationContext context: Context) : PlanDatabase {
        return Room.databaseBuilder(
            context,
            PlanDatabase::class.java, "plan_db"
        ).build()
    }

}