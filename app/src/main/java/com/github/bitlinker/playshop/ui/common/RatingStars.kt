package com.github.bitlinker.playshop.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.github.bitlinker.playshop.R

@Composable
fun RatingStars(rating: Float) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        for (i in 0 until 5) {
            val starFillRatio = (rating.coerceIn(0F, 5F) - i).coerceIn(0F, 1F)
            RatingStar(starFillRatio)
        }
    }
}

@Composable
private fun RatingStar(fillRatio: Float) {
    val size = 12.dp
    Box {
        Image(
            modifier = Modifier
                .size(size)
                .clip(WidthRatioShape(0F, fillRatio)),
            painter = painterResource(R.drawable.ic_star),
            contentDescription = "Rating star",
        )
        if (fillRatio < 1F) {
            Image(
                modifier = Modifier
                    .size(size)
                    .clip(WidthRatioShape(fillRatio, 1F)),
                painter = painterResource(R.drawable.ic_star),
                colorFilter = ColorFilter.tint(
                    color = Color(0xFF282E3E),
                    blendMode = BlendMode.SrcIn
                ),
                contentDescription = null,
            )
        }
    }
}

private class WidthRatioShape(private val startRatio: Float, private val endRatio: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                addRect(Rect(size.width * startRatio, 0F, size.width * endRatio, size.height))
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
internal fun RatingStarsPreviews() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        listOf(0F, 1F, 2.4F, 4.7F, 5F).forEach {
            RatingStars(it)
        }
    }
}