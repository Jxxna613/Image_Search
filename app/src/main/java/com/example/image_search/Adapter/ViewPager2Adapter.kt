package com.example.image_search.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.image_search.Data.SearchData
import com.example.image_search.SearchFragment

class ViewPager2Adapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
    private lateinit var viewPager2Adapter: ViewPager2Adapter
    val fragments = listOf<SearchData>(SearchFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}