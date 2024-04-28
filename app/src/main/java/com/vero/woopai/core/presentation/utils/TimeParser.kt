package com.vero.woopai.core.presentation.utils

import java.time.LocalDateTime
import java.time.format.TextStyle
import java.util.Locale

fun LocalDateTime.getPrettyTime(): String {
    return "Plan for day : ${this.dayOfMonth} ${
        this.month.getDisplayName(
            TextStyle.FULL,
            Locale.getDefault()
        )
    } ${this.year}"
}