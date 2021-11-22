package com.crypto.base

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.unit.sp
import com.crypto.resources.SharedColors
import com.crypto.resources.SharedFont
import com.crypto.resources.SharedFontSize

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography(
            defaultFontFamily = SharedFont.defaultFontFamily,
            h1 = TextStyle(
                fontWeight = W900,
                fontSize = 40.sp,
                color = SharedColors.Grey900,
                fontFamily = LocalTextStyle.current.fontFamily
            ),
            h2 = TextStyle(
                fontWeight = W700,
                fontSize = 32.sp,
                color = SharedColors.Grey900,
                fontFamily = LocalTextStyle.current.fontFamily
            ),
            h3 = TextStyle(
                fontWeight = W700,
                fontSize = SharedFontSize.Large2,
                color = SharedColors.Grey900,
                fontFamily = LocalTextStyle.current.fontFamily
            ),
            h4 = TextStyle(
                fontWeight = W700,
                fontSize = 20.sp,
                color = SharedColors.Grey900,
                fontFamily = LocalTextStyle.current.fontFamily
            ),
            h5 = TextStyle(
                fontWeight = W700,
                fontSize = SharedFontSize.Medium,
                color = SharedColors.Grey900,
                fontFamily = LocalTextStyle.current.fontFamily
            ),
            h6 = TextStyle(
                fontWeight = W700,
                fontSize = SharedFontSize.Small2,
                color = SharedColors.Grey900,
                fontFamily = LocalTextStyle.current.fontFamily
            ),
        ),
        content = content
    )
}