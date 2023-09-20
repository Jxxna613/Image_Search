package com.example.image_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.image_search.Adapter.FavoriteAdapter
import com.example.image_search.Adapter.ViewPager2Adapter
import com.example.image_search.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            searchTabItem.setOnClickListener {
                setFragment(SearchFragment())
            }
            favoriteTabItem.setOnClickListener {
                setFragment(FavoriteFragment())
            }
        }
        setFragment(SearchFragment())

        val viewPager2: ViewPager2 = binding.viewPager2
        val viewPager2Adapter = ViewPager2Adapter(this)
        viewPager2.adapter = viewPager2Adapter
        val tabTitle = listOf<String>("검색", "보관함")

        TabLayoutMediator(
            binding.tabLayout,
            viewPager2,
            { tab, position -> tab.text = tabTitle[position] }).attach()
    }

    private fun setFragment(frag: Fragment) {
        val manager = supportFragmentManager.beginTransaction()
        manager.replace(R.id.fragment_search, frag)
        manager.setReorderingAllowed(true)
        manager.addToBackStack(null)
        manager.commit()
    }
}