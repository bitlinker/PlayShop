package com.github.bitlinker.playshop.ui.gamecard

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bitlinker.playshop.R
import com.github.bitlinker.playshop.ui.common.TopCardGradient
import com.github.bitlinker.playshop.ui.common.RatingStars
import com.github.bitlinker.playshop.ui.common.TagsList
import com.github.bitlinker.playshop.ui.common.TransparentToolbar
import com.github.bitlinker.playshop.model.gamecard.GameCardAction
import com.github.bitlinker.playshop.model.gamecard.GameCardDispatcher
import com.github.bitlinker.playshop.model.gamecard.GameCardModel
import com.github.bitlinker.playshop.model.gamecard.GameCardSampleData
import com.github.bitlinker.playshop.ui.theme.fontFamilySkModernist

@Composable
fun GameCardScreen(model: GameCardModel, dispatcher: GameCardDispatcher) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF000000)) {
        Box(Modifier.fillMaxSize()) {

            val scrollState = rememberScrollState()
            GameCardScrollableContent(model, dispatcher, scrollState)
            TopCardGradient()
            TransparentToolbar(dispatcher, scrollState.value)
        }
    }
}

@Composable
private fun GameCardScrollableContent(
    model: GameCardModel,
    dispatcher: GameCardDispatcher,
    scrollState: ScrollState
) {
    Column(
        Modifier
            .verticalScroll(scrollState)
    ) {
        GameCardHeader(
            backgroundRes = model.backgroundRes,
            title = model.title,
            logoRes = model.logoRes,
            rating = model.rating,
            formattedReviewsCount = model.formattedReviewsCount,
        )
        Spacer(modifier = Modifier.height(24.dp))
        TagsList(model.tags)
        Spacer(modifier = Modifier.height(24.dp))
        Description(model.description)
        Spacer(modifier = Modifier.height(21.dp))
        if (model.media.isNotEmpty()) {
            GameCardMediaList(dispatcher, model.media)
            Spacer(modifier = Modifier.height(20.dp))
        }
        if (model.reviews.isNotEmpty()) {
            ReviewAndRatingsHeader(
                rating = model.rating,
                formattedReviewsCount = model.formattedReviewsCount
            )
            Spacer(modifier = Modifier.height(32.dp))
            GameCardReviewsList(model.reviews)
        }
        InstallButtonItem(dispatcher)
    }
}

@Composable
private fun Description(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = contentPaddingHorizontal),
        text = text,
        style = TextStyle(
            fontFamily = fontFamilySkModernist,
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            lineHeight = 19.sp,
            color = Color(0xB2EEF2FB)
        ),
    )
}

@Composable
private fun ReviewAndRatingsHeader(
    rating: Float,
    formattedReviewsCount: String,
) {
    Column(modifier = Modifier.padding(horizontal = contentPaddingHorizontal)) {
        Text(
            text = stringResource(R.string.gamecard_review_and_ratings),
            color = Color(0xFFEEF2FB),
            style = TextStyle(
                fontFamily = fontFamilySkModernist,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(Modifier.fillMaxWidth()) {
            Text(
                text = String.format(java.util.Locale.US, "%2.1f", rating),
                color = Color(0xFFFFFFFF),
                style = TextStyle(
                    fontFamily = fontFamilySkModernist,
                    fontWeight = FontWeight.W700,
                    fontSize = 48.sp
                ),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                RatingStars(rating)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(
                        R.string.gamecard_review_formatted_count,
                        formattedReviewsCount
                    ),
                    color = Color(0xFFA8ADB7),
                    style = TextStyle(
                        fontFamily = fontFamilySkModernist,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        letterSpacing = 0.5.sp
                    ),
                )
            }
        }
    }
}

@Composable
private fun InstallButtonItem(dispatcher: GameCardDispatcher) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = contentPaddingHorizontal, vertical = 40.dp)
            .height(64.dp)
            .clickable { dispatcher.dispatch(GameCardAction.InstallClicked) }
            .background(
                color = Color(0xFFF4D144),
                shape = RoundedCornerShape(12.dp),
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.gamecard_button_install),
            color = Color(0xFF050B18),
            style = TextStyle(
                fontFamily = fontFamilySkModernist,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp,
                letterSpacing = 0.6.sp
            ),
        )
    }
}

private val contentPaddingHorizontal = 24.dp

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GameCardScreen(GameCardSampleData.sampleGameInfoModel, GameCardDispatcher.Empty)
}
