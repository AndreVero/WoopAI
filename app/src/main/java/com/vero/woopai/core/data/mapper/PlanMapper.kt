package com.vero.woopai.core.data.mapper

import com.vero.woopai.core.data.local.entity.PlanEntity
import com.vero.woopai.core.domain.model.PlanModel
import com.vero.woopai.core.data.utils.LocalDateTimeConverter

fun PlanModel.toPlanEntity() : PlanEntity {
    return PlanEntity(
        description = this.description,
        time = LocalDateTimeConverter.getEpochForUTC(this.time)
    )
}

fun PlanEntity.toPlanModel() : PlanModel {
    return PlanModel(
        id = this.id ?: 0,
        description = this.description,
        time = LocalDateTimeConverter.longToLocalDateTimeWithTimezone(this.time)
    )
}