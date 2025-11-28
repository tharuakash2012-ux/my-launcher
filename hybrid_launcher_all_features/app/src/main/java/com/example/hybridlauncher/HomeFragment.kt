package com.example.hybridlauncher

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dock: DockView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager = root.findViewById(R.id.homePager)
        dock = root.findViewById(R.id.dockView)

        val pm = requireContext().packageManager
        val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { pm.getLaunchIntentForPackage(it.packageName) != null }
            .sortedBy { pm.getApplicationLabel(it).toString() }

        val appsPerPage = 20
        val pages = apps.chunked(appsPerPage)

        viewPager.adapter = HomePagerAdapter(pages, pm)

        val dockApps = pages.flatten().take(4)
        dock.bindApps(dockApps, pm)

        ViewCompat.setTranslationZ(viewPager, 5f)

        return root
    }
}
