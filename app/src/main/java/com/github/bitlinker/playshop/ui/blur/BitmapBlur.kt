package com.github.bitlinker.playshop.ui.blur

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

@Suppress("DEPRECATION")
internal class BitmapBlur(context: Context) {

    private val renderScript = RenderScript.create(context)
    private val blurIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))

    fun blur(source: Bitmap, radius: Float): Bitmap {
        val bitmap = source.copy(source.config, true)
        renderScript.apply {
            val input = Allocation.createFromBitmap(this, source)
            val output = Allocation.createFromBitmap(this, source)
            blurIntrinsic.apply {
                setInput(input)
                setRadius(radius.coerceIn(0F..25F))
                forEach(output)
                output.copyTo(bitmap)
            }
            input.destroy()
            output.destroy()
        }
        return bitmap
    }
}