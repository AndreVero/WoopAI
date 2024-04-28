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

val Cartline = FontFamily(
    Font(R.font.carltine_black, FontWeight.Black),
    Font(R.font.carltine_bold, FontWeight.Bold),
    Font(R.font.carltine_extra_bold, FontWeight.ExtraBold),
    Font(R.font.carltine_extra_light, FontWeight.ExtraLight),
    Font(R.font.carltine_light, FontWeight.Light),
    Font(R.font.carltine_medium, FontWeight.Medium),
    Font(R.font.carltine_regular, FontWeight.Normal),
    Font(R.font.carltine_semi_bold, FontWeight.SemiBold),
    Font(R.font.carltine_thin, FontWeight.Thin),
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

val DefaultText = TextStyle(
    fontFamily = Cartline,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)

val AppBarStyle = TextStyle(
    fontFamily = MouldyCheese,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp
)
