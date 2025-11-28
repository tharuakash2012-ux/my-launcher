package com.example.hybridlauncher

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.view.View

object BlurUtil {
    fun applyBlur(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            try {
                view.setRenderEffect(RenderEffect.createBlurEffect(20f, 20f, Shader.TileMode.CLAMP))
            } catch (e: Exception) {
                view.alpha = 0.95f
            }
        } else {
            // fallback: slightly translucent background
            view.alpha = 0.95f
        }
    }
}
