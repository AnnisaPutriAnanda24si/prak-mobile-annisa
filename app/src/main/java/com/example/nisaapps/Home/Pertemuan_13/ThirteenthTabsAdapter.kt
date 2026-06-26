package com.example.nisaapps.Home.Pertemuan_13

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ThirteenthTabsAdapter (activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabQrcodeFragment()
            1 -> TabCaptureFragment()
            2 -> TabScanFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}