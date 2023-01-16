package com.example.demotivation30days.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.demotivation30days.R

val Lora = FontFamily(
    Font(R.font.lora),
    Font(R.font.lora_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = Lora,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        fontFeatureSettings = "c2sc, smcp"
    ),
    body1 = TextStyle(
        fontFamily = Lora,
        fontWeight = FontWeight.Normal,
        fontSize=16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)