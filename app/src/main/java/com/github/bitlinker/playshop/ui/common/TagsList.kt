package com.github.bitlinker.playshop.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bitlinker.playshop.ui.theme.fontFamilyMontserrat
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun TagsList(tags: List<String>) {
    FlowRow(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        mainAxisSpacing = 10.dp
    ) {
        tags.forEach {
            TagItem(it)
        }
    }
}

@Composable
private fun TagItem(tag: String) {
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFF44A9F4).copy(alpha = 0.24F),
                shape = RoundedCornerShape(100)
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)

    ) {
        Text(
            text = tag.toUpperCase(Locale.current),
            style = TextStyle(
                color = Color(0xFF44A9F4),
                fontFamily = fontFamilyMontserrat,
                fontWeight = FontWeight.W500,
                fontSize = 10.sp,
                lineHeight = 12.19.sp,
            ),
            maxLines = 1,
        )
    }
}
