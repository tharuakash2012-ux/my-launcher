package com.example.hybridlauncher

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LockScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lockscreen)
        // immersive fullscreen
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

        val btnSettings = findViewById<Button>(R.id.btnSettings)
        btnSettings.setOnClickListener {
            startActivity(Intent(Settings.ACTION_SETTINGS))
        }

        val btnUnlock = findViewById<Button>(R.id.btnUnlock)
        btnUnlock.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        // prevent back
    }
}
