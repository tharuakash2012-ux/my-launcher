package com.example.hybridlauncher

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomePagerAdapter(
    private val pages: List<List<ApplicationInfo>>,
    private val pm: PackageManager
) : RecyclerView.Adapter<HomePagerAdapter.PageVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_page, parent, false)
        return PageVH(view)
    }

    override fun onBindViewHolder(holder: PageVH, position: Int) {
        holder.bind(pages[position], pm)
    }

    override fun getItemCount(): Int = pages.size

    class PageVH(view: View) : RecyclerView.ViewHolder(view) {
        private val recycler: RecyclerView = view.findViewById(R.id.pageRecycler)
        fun bind(apps: List<ApplicationInfo>, pm: PackageManager) {
            recycler.layoutManager = GridLayoutManager(recycler.context, 4)
            recycler.adapter = AppGridAdapter(apps, pm)
        }
    }
}
