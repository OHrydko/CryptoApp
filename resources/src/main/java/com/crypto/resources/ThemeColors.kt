package com.crypto.resources

import android.annotation.SuppressLint
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors

val lightColors = lightColors(
    primary = SharedColors.Grey400,
    primaryVariant = SharedColors.Grey200,
    secondary = SharedColors.Grey700,
    secondaryVariant = SharedColors.Black,
    background = SharedColors.White,
    error = SharedColors.Error,
    onPrimary = SharedColors.Grey900,
    onSecondary = SharedColors.Primary900
)

@SuppressLint("ConflictingOnColor")
val darkColors = darkColors(
    primary = SharedColors.Grey100,
    primaryVariant = SharedColors.White,
    secondary = SharedColors.White,
    secondaryVariant = SharedColors.White,
    background = SharedColors.Grey900,
    error = SharedColors.Error,
    onPrimary = SharedColors.White,
    onSecondary = SharedColors.Primary400
)