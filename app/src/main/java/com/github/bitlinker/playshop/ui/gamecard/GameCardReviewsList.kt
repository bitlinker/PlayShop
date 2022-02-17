package com.github.bitlinker.playshop.ui.gamecard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bitlinker.playshop.model.gamecard.GameCardModel
import com.github.bitlinker.playshop.model.gamecard.GameCardSampleData
import com.github.bitlinker.playshop.ui.theme.fontFamilySkModernist
import com.github.bitlinker.playshop.R

@Composable
fun GameCardReviewsList(items: List<GameCardModel.Review>) {
    Column {
        items.forEachIndexed { index, review ->
            ReviewItem(review)
            if (index < items.size - 1) {
                ReviewSeparator()
            }
        }
    }
}

@Composable
private fun ReviewItem(item: GameCardModel.Review) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Row(Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape),
                painter = painterResource(item.avatarRes),
                contentDescription = stringResource(R.string.gamecard_accessibility_review_avatar)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = item.author,
                    style = TextStyle(
                        color = Color(0xFFFFFFFF),
                        fontFamily = fontFamilySkModernist,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        letterSpacing = 0.5.sp,
                    ),
                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = item.formattedDate,
                    style = TextStyle(
                        color = Color(0x66FFFFFF),
                        fontFamily = fontFamilySkModernist,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        letterSpacing = 0.5.sp,
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = item.text,
            style = TextStyle(
                color = Color(0xFFA8ADB7),
                fontFamily = fontFamilySkModernist,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                lineHeight = 20.sp,
                letterSpacing = 0.5.sp,
            ),
        )
    }
}

@Composable
private fun ReviewSeparator() {
    Spacer(
        Modifier
            .padding(horizontal = 38.dp, vertical = 24.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(Color(0xFF1A1F29))
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
internal fun GameCardReviewsListPreview() {
    GameCardReviewsList(GameCardSampleData.sampleGameInfoModel.reviews)
}