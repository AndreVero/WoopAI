package com.vero.woopai.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vero.woopai.core.data.local.dao.PlanDao
import com.vero.woopai.core.data.local.entity.PlanEntity


@Database(entities = [PlanEntity::class], version = 1)
abstract class PlanDatabase : RoomDatabase() {

    abstract fun planDao() : PlanDao

}