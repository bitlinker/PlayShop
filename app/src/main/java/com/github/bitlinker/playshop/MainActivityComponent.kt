package com.github.bitlinker.playshop

import com.github.bitlinker.playshop.ui.blur.BitmapBlur
import com.github.bitlinker.playshop.ui.blur.RootViewBitmapProvider

internal interface MainActivityComponent {
    val rootViewBitmapProvider: RootViewBitmapProvider
    val bitmapBlur: BitmapBlur
}