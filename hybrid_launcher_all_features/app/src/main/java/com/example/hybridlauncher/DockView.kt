package com.example.hybridlauncher

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class DockView(context: android.content.Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private val container: LinearLayout

    init {
        // apply blur or translucent fallback
        BlurUtil.applyBlur(this)
        val root = LayoutInflater.from(context).inflate(R.layout.view_dock, this, true)
        container = root.findViewById(R.id.dockContainer)
    }

    fun bindApps(apps: List<ApplicationInfo>, pm: PackageManager) {
        container.removeAllViews()
        apps.forEach { app ->
            val v = LayoutInflater.from(context).inflate(R.layout.item_app_icon, container, false)
            val icon = v.findViewById<ImageView>(R.id.appIcon)
            val label = v.findViewById<TextView>(R.id.appLabel)
            icon.setImageDrawable(pm.getApplicationIcon(app))
            label.text = ""
            v.setOnClickListener { pm.getLaunchIntentForPackage(app.packageName)?.let { context.startActivity(it) } }
            container.addView(v)
        }
    }
}
