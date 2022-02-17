package com.github.bitlinker.playshop.ui.common

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.bitlinker.playshop.findActivityComponent
import com.github.bitlinker.playshop.R

enum class BlurredRoundButtonSize {
    Big,
    Small
}

@Composable
fun BlurredRoundButton(
    modifier: Modifier = Modifier,
    size: BlurredRoundButtonSize,
    @DrawableRes iconRes: Int,
    contentDescription: String,
    onClick: () -> Unit,
) {
    Box(modifier = modifier
        .clip(CircleShape)
        .border(
            width = 0.7F.dp,
            brush = SolidColor(Color(0xFFF2F2F2).copy(alpha = 0.3F)),
            shape = CircleShape
        )
        .clickable { onClick() }
    ) {
        ButtonBackground(size)
        Icon(
            modifier = Modifier
                .size(Dp(24F))
                .align(Alignment.Center),
            painter = painterResource(iconRes),
            contentDescription = contentDescription,
            tint = Color.White
        )
    }
}

@Composable
private fun ButtonBackground(
    size: BlurredRoundButtonSize,
) {
    var buttonRectInRoot by remember { mutableStateOf(Rect.Zero) }
    var backgroundBitmap by remember<MutableState<ImageBitmap?>> { mutableStateOf(null) }

    LocalContext.current.findActivityComponent()?.let { mainActivityComponent ->
        if (buttonRectInRoot != Rect.Zero) {
            val blurRadius = with(LocalDensity.current) { 6.dp.toPx() }
            LaunchedEffect(key1 = size) {
                backgroundBitmap = mainActivityComponent.rootViewBitmapProvider.getRootViewBitmap()
                    ?.let { bitmap ->
                        val cropped = Bitmap.createBitmap(
                            bitmap,
                            buttonRectInRoot.left.toInt(),
                            buttonRectInRoot.top.toInt(),
                            buttonRectInRoot.width.toInt(),
                            buttonRectInRoot.height.toInt()
                        )
                        mainActivityComponent.bitmapBlur.blur(cropped, blurRadius).asImageBitmap()
                    }
            }
        }
    }
    Box(
        modifier = Modifier
            .size(size.dp)
            .onGloballyPositioned { coordinates ->
                if (buttonRectInRoot == Rect.Zero) {
                    buttonRectInRoot = coordinates.boundsInRoot()
                }
            }
    ) {
        if (buttonRectInRoot != Rect.Zero) {
            backgroundBitmap?.let { bitmap ->
                Image(
                    bitmap = bitmap,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        Box(
            Modifier
                .fillMaxSize()
                .alpha(0.15F)
                .background(Color(0xFFFFFFFF))
        )
    }
}

private val BlurredRoundButtonSize.dp: Dp
    get() = when (this) {
        BlurredRoundButtonSize.Big -> 56.dp
        BlurredRoundButtonSize.Small -> 48.dp
    }

@Preview(showBackground = true, backgroundColor = 0xFF333333)
@Composable
internal fun ButtonPreview() {
    Box {
        Image(painter = painterResource(R.drawable.background), contentDescription = null)
        BlurredRoundButton(
            size = BlurredRoundButtonSize.Big,
            iconRes = R.drawable.ic_arrowleft,
            contentDescription = "",
            onClick = {}
        )
    }
}
