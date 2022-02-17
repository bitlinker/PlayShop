@file:JvmName("GameCardMediaListKt")

package com.github.bitlinker.playshop.ui.gamecard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.bitlinker.playshop.ui.common.BlurredRoundButton
import com.github.bitlinker.playshop.ui.common.BlurredRoundButtonSize
import com.github.bitlinker.playshop.model.gamecard.GameCardModel
import com.github.bitlinker.playshop.model.gamecard.GameCardAction
import com.github.bitlinker.playshop.model.gamecard.GameCardDispatcher
import com.github.bitlinker.playshop.model.gamecard.GameCardSampleData
import com.github.bitlinker.playshop.R

@Composable
fun GameCardMediaList(dispatcher: GameCardDispatcher, items: List<GameCardModel.Media>) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(135.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(horizontal = 24.dp),
        ) {
            items(
                count = items.size,
                key = { index -> items[index].id },
                itemContent = { index -> MediaItem(dispatcher, items[index]) }
            )
        }
        Row {
            SideGradient(gradientOffsetStart, gradientOffsetEnd)
            Spacer(modifier = Modifier.weight(1F))
            SideGradient(gradientOffsetEnd, gradientOffsetStart)
        }
    }
}

@Composable
private fun MediaItem(dispatcher: GameCardDispatcher, item: GameCardModel.Media) {
    Box(contentAlignment = Alignment.Center) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(14.dp)),
            contentScale = ContentScale.FillHeight,
            painter = painterResource(item.previewRes),
            contentDescription = stringResource(R.string.gamecard_accessibility_game_media)
        )
        if (item.isPlayable) {
            BlurredRoundButton(
                modifier = Modifier.align(Alignment.Center),
                size = BlurredRoundButtonSize.Small,
                iconRes = R.drawable.ic_play,
                contentDescription = stringResource(R.string.gamecard_accessibility_game_media_play),
                onClick = { dispatcher.dispatch(GameCardAction.PlayPauseClicked(item.id)) }
            )
        }
    }
}

@Composable
private fun SideGradient(start: Offset, end: Offset) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(24.dp)
            .background(
                Brush.linearGradient(
                    0F to Color(0xFF000000),
                    1F to Color(0x00000000),
                    start = start,
                    end = end,
                )
            )
    )
}

private val gradientOffsetStart = Offset.Zero
private val gradientOffsetEnd = Offset(Float.POSITIVE_INFINITY, 0F)

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
internal fun GameCardMediaListPreview() {
    GameCardMediaList(GameCardDispatcher.Empty, GameCardSampleData.sampleGameInfoModel.media)
}