package com.example.hybridlauncher

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

class ControlCenterView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_control_center, this, true)
        visibility = View.GONE
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // intercept touches so underlying pages don't respond
        return true
    }

    fun show() {
        visibility = View.VISIBLE
        translationY = height.toFloat()
        animate().translationY(0f).setDuration(280).start()
    }

    fun hide() {
        animate().translationY(height.toFloat()).setDuration(220).withEndAction { visibility = View.GONE }.start()
    }
}
