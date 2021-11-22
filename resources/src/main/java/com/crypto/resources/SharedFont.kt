package com.crypto.resources

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

object SharedFont {


    private val roboto_light = Font(R.font.shared_roboto_light, FontWeight.W300)
    private val roboto_regular = Font(R.font.shared_roboto_regular, FontWeight.W400)
    private val roboto_medium = Font(R.font.shared_roboto_medium, FontWeight.W500)
    private val roboto_semibold = Font(R.font.shared_roboto_bold, FontWeight.W600)
    private val roboto_bold = Font(R.font.shared_roboto_bold, FontWeight.W700)

    val robotoFontFamily = FontFamily(
        roboto_light, roboto_regular, roboto_medium, roboto_semibold, roboto_bold
    )

    val defaultFontFamily = robotoFontFamily

}