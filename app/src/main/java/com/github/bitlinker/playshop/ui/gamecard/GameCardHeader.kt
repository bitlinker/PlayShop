package com.github.bitlinker.playshop.ui.gamecard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bitlinker.playshop.ui.common.RatingStars
import com.github.bitlinker.playshop.model.gamecard.GameCardSampleData
import com.github.bitlinker.playshop.ui.theme.fontFamilySkModernist
import com.github.bitlinker.playshop.R

@Composable
fun GameCardHeader(
    @DrawableRes backgroundRes: Int,
    title: String,
    @DrawableRes logoRes: Int,
    rating: Float,
    formattedReviewsCount: String
) {
    Box {
        GameBackgroundImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp),
            backgroundRes = backgroundRes,
        )
        GameHeader(
            modifier = Modifier.align(Alignment.BottomStart),
            title = title,
            logoRes = logoRes,
            rating = rating,
            formattedReviewsCount = formattedReviewsCount,
        )
    }
}

@Composable
private fun GameBackgroundImage(
    modifier: Modifier = Modifier,
    @DrawableRes backgroundRes: Int
) {
    Image(
        modifier = modifier,
        contentScale = ContentScale.FillWidth,
        painter = painterResource(id = backgroundRes),
        contentDescription = stringResource(R.string.gamecard_accessibility_background_image),
    )
}

@Composable
private fun GameHeader(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes logoRes: Int,
    rating: Float,
    formattedReviewsCount: String
) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        GameCardLogoImage(resId = logoRes)
        Column(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.Bottom),
            verticalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            GameTitle(title)
            GameRating(rating, formattedReviewsCount)
        }
    }
}

@Composable
private fun GameTitle(text: String) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = TextStyle(
            color = Color(0xFFFFFFFF),
            fontFamily = fontFamilySkModernist,
            fontWeight = FontWeight.W700,
            fontSize = 20.sp,
            letterSpacing = 0.5.sp,
        ),
    )
}

@Composable
private fun GameRating(rating: Float, formattedReviewsCount: String) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        RatingStars(rating)
        Text(
            text = formattedReviewsCount,
            style = TextStyle(
                color = Color(0xFF45454D),
                fontFamily = fontFamilySkModernist,
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                letterSpacing = 0.5.sp,
            ),
        )
    }
}

@Composable
private fun GameCardLogoImage(@DrawableRes resId: Int) {
    val shape = RoundedCornerShape(14.dp)
    Box(
        Modifier
            .size(87.dp)
            .border(width = 1.93.dp, color = Color(0xFF1F2430), shape = shape)
            .padding(1.dp)
            .background(color = Color(0xFF000000), shape = shape)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(54.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = resId),
            contentDescription = stringResource(R.string.gamecard_accessibility_game_logo)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
internal fun GameCardLogoImagePreview() {
    GameCardHeader(
        backgroundRes = GameCardSampleData.sampleGameInfoModel.backgroundRes,
        title = GameCardSampleData.sampleGameInfoModel.title,
        logoRes = GameCardSampleData.sampleGameInfoModel.logoRes,
        rating = GameCardSampleData.sampleGameInfoModel.rating,
        formattedReviewsCount = GameCardSampleData.sampleGameInfoModel.formattedReviewsCount,
    )
}