package com.vero.woopai.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vero.woopai.core.data.local.entity.PlanEntity

@Dao
interface PlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlans(vararg plans: PlanEntity)

    @Delete
    suspend fun deletePlans(vararg plans: PlanEntity)

    @Query("""
        SELECT *
        FROM `plan`
        WHERE time BETWEEN :from AND :to
    """)
    suspend fun loadPlansForDay(from: Long, to: Long) : List<PlanEntity>

    @Query("""
        SELECT *
        FROM `plan`
    """)
    suspend fun loadAllPlans() : List<PlanEntity>
}