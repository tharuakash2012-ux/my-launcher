package com.example.hybridlauncher

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppGridAdapter(private val apps: List<ApplicationInfo>, private val pm: PackageManager) : RecyclerView.Adapter<AppGridAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app_icon, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val app = apps[position]
        holder.bind(app, pm)
    }

    override fun getItemCount(): Int = apps.size

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val icon: ImageView = view.findViewById(R.id.appIcon)
        private val label: TextView = view.findViewById(R.id.appLabel)

        fun bind(app: ApplicationInfo, pm: PackageManager) {
            val launch = pm.getLaunchIntentForPackage(app.packageName)
            val appLabel = pm.getApplicationLabel(app).toString()
            val appIcon = pm.getApplicationIcon(app)
            icon.setImageDrawable(appIcon)
            label.text = appLabel

            itemView.setOnClickListener {
                it.animate().scaleX(0.92f).scaleY(0.92f).setDuration(120).withEndAction {
                    it.animate().scaleX(1f).scaleY(1f).setDuration(120).start()
                    if (launch != null) itemView.context.startActivity(launch)
                }.start()
            }
        }
    }
}
