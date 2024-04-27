package com.vero.woopai.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vero.woopai.R

val MouldyCheese = FontFamily(
    Font(R.font.mouldy_cheese_regular, FontWeight.Normal)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val ButtonStyle = TextStyle(
    fontFamily = MouldyCheese,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp
)

val AppBarStyle = TextStyle(
    fontFamily = MouldyCheese,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp
)
