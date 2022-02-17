package com.github.bitlinker.playshop

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.bitlinker.playshop.ui.gamecard.GameCardScreen
import com.github.bitlinker.playshop.model.gamecard.GameCardAction
import com.github.bitlinker.playshop.model.gamecard.GameCardDispatcher
import com.github.bitlinker.playshop.model.gamecard.GameCardSampleData
import com.github.bitlinker.playshop.ui.blur.BitmapBlur
import com.github.bitlinker.playshop.ui.blur.RootViewBitmapProvider

class MainActivity : ComponentActivity(), RootViewBitmapProvider {
    private val dispatcher by lazy {
        object : GameCardDispatcher {
            override fun dispatch(action: GameCardAction) {
                when (action) {
                    is GameCardAction.GoBackClicked -> onBackPressed()
                    is GameCardAction.InstallClicked -> showToast("Install clicked")
                    is GameCardAction.MoreClicked -> showToast("More clicked")
                    is GameCardAction.PlayPauseClicked -> showToast("Play/pause clicked")
                }
            }
        }
    }

    private val rootView by lazy {
        findViewById<View>(android.R.id.content).rootView
    }

    internal val mainActivityComponent by lazy {
        object : MainActivityComponent {
            override val rootViewBitmapProvider: RootViewBitmapProvider = this@MainActivity

            override val bitmapBlur: BitmapBlur by lazy {
                BitmapBlur(applicationContext)
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.navigationBarColor = 0xFF000000.toInt()

        setContent {
            GameCardScreen(GameCardSampleData.sampleGameInfoModel, dispatcher)
        }
    }

    @Suppress("DEPRECATION")
    override fun getRootViewBitmap(): Bitmap? {
        return rootView?.let {
            it.isDrawingCacheEnabled = true
            val bitmap = Bitmap.createBitmap(it.drawingCache)
            it.isDrawingCacheEnabled = false
            bitmap
        }
    }
}

internal fun Context.findActivityComponent(): MainActivityComponent? {
    return if (this is MainActivity) mainActivityComponent else null
}