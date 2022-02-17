package com.github.bitlinker.playshop.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopCardGradient() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(126.dp)
            .background(
                Brush.linearGradient(
                    0F to Color(0xFF050B18),
                    1F to Color(0x00050B18),
                    start = Offset.Zero,
                    end = Offset(0F, Float.POSITIVE_INFINITY),
                )

            )
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFF0000)
@Composable
internal fun OverlayGradientsPreview() {
    TopCardGradient()
}