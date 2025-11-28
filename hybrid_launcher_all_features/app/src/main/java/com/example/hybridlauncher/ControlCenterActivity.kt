package com.example.hybridlauncher

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class ControlCenterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_center)

        val wifiBtn = findViewById<Button>(R.id.btnWifi)
        val btBtn = findViewById<Button>(R.id.btnBluetooth)
        val panelBtn = findViewById<Button>(R.id.btnPanel)
        val brightnessSeek = findViewById<SeekBar>(R.id.seekBrightness)

        wifiBtn.setOnClickListener {
            try {
                // Try settings panel (API 29+), else open wifi settings
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    startActivity(Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY))
                } else {
                    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                }
            } catch (e: Exception) {
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
        }

        btBtn.setOnClickListener {
            startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS))
        }

        panelBtn.setOnClickListener {
            startActivity(Intent(Settings.ACTION_SETTINGS))
        }

        brightnessSeek.max = 100
        brightnessSeek.progress = (android.provider.Settings.System.getInt(contentResolver, android.provider.Settings.System.SCREEN_BRIGHTNESS, 128) * 100) / 255
        brightnessSeek.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val lp = window.attributes
                lp.screenBrightness = progress / 100f
                window.attributes = lp
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
