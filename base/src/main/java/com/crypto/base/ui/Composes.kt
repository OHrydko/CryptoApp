package com.crypto.base.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.crypto.base.Constants.MIN_TOOLBAR_HEIGHT
import com.crypto.base.R
import com.crypto.resources.SharedColors
import com.crypto.resources.SharedFontSize

@Composable
fun ScreenLoader() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(enabled = false, onClick = {})
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = SharedColors.DarkBlue,
        )
    }
}

@Composable
fun AppToolbar(
    title: String,
    titleColor: Color = Color.Black,
    iconColor: Color = Color.Black,
    onClick: () -> Unit,
    styleToolbarText: TextStyle = TextStyle(
        fontWeight = FontWeight.W700,
        fontSize = SharedFontSize.Large,
        fontFamily = LocalTextStyle.current.fontFamily,
        textAlign = TextAlign.Center,
    ),
    modifier: Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .requiredHeight(MIN_TOOLBAR_HEIGHT.dp)
            .fillMaxWidth()
    ) {
        val (backButton, textTitle) = createRefs()

        IconButton(
            modifier = Modifier
                .size(40.dp)
                .constrainAs(backButton) {
                    start.linkTo(parent.start, 16.dp)
                    centerVerticallyTo(parent)
                },
            onClick = onClick
        ) {
            Image(
                painter = painterResource(R.drawable.shared_ic_arrow_left),
                contentDescription = "back button",
                colorFilter = ColorFilter.tint(
                    iconColor
                )
            )
        }
        Text(
            text = title,
            style = styleToolbarText,
            color = titleColor,
            maxLines = 1,
            modifier = Modifier.constrainAs(textTitle) {
                centerVerticallyTo(backButton)
                centerHorizontallyTo(parent)
            }
        )
    }
}