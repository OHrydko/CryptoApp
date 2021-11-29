package com.crypto.base.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.crypto.resources.SharedColors

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