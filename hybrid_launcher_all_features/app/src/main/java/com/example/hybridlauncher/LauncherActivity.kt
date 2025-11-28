package com.example.hybridlauncher

import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit

import android.view.WindowInsets
import android.view.ViewCompat
import android.view.WindowInsetsController
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding


class LauncherActivity : AppCompatActivity() {

    private lateinit var gestureDetector: GestureDetector

    
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Make content draw behind status bar for notch handling
    window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

    // Apply WindowInsets to container so UI avoids notch
    val container = findViewById(android.R.id.content)
    ViewCompat.setOnApplyWindowInsetsListener(container) { v, insets ->
        val sys = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.updatePadding(top = sys.top, bottom = sys.bottom)
        insets
    }
        // Load home fragment (paged home screens like iOS)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.container, HomeFragment())
            }
        }

        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                if (e1 != null && e2 != null) {
                    val dy = e1.y - e2.y
                    if (dy > 200 && Math.abs(velocityY) > 800) {
                        // simulate going home: bring home fragment to front
                        supportFragmentManager.commit {
                            replace(R.id.container, HomeFragment())
                        }
                        return true
                    }
                }
                return false
            }
        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(ev)
        return super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        // prevent back navigation so launcher feels like home
    }
}
