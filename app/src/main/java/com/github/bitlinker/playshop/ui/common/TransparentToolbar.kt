package com.github.bitlinker.playshop.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.bitlinker.playshop.model.gamecard.GameCardAction
import com.github.bitlinker.playshop.model.gamecard.GameCardDispatcher
import com.github.bitlinker.playshop.R

@Composable
fun TransparentToolbar(dispatcher: GameCardDispatcher, scrollState: Int) {
    val scrollDp = with(LocalDensity.current) { scrollState.toDp() }
    Box(Modifier.fillMaxWidth()) {
        TopCardGradient()
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 70.dp, start = 24.dp, end = 24.dp)
                .offset(y = -scrollDp)
                .alpha((1F - scrollState / 126F).coerceIn(0F..1F))
        ) {
            BlurredRoundButton(
                size = BlurredRoundButtonSize.Big,
                iconRes = R.drawable.ic_arrowleft,
                contentDescription = stringResource(R.string.toolbar_accessibility_back),
                onClick = { dispatcher.dispatch(GameCardAction.GoBackClicked) },
            )
            Spacer(Modifier.weight(1F))
            BlurredRoundButton(
                size = BlurredRoundButtonSize.Big,
                iconRes = R.drawable.ic_more_square,
                contentDescription = stringResource(R.string.toolbar_accessibility_more),
                onClick = { dispatcher.dispatch(GameCardAction.MoreClicked) },
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFF0000)
@Composable
internal fun OverlayToolbarPreview() {
    TransparentToolbar(GameCardDispatcher.Empty, 0)
}