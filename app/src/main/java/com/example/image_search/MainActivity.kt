package com.example.image_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.image_search.Favorite.FavoriteFragment
import com.example.image_search.Search.SearchFragment
import com.example.image_search.Search.SearchModel
import com.example.image_search.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var likeItem: ArrayList<SearchModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Emulator 실행 시 빈 화면이 아닌 SearchFragment가 보이게 만드는 부분
        supportFragmentManager.beginTransaction()
            .replace(R.id.view, SearchFragment())
            .commit()

        binding.searchButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, SearchFragment())
                .commit()
        }

        binding.favoriteButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.view, FavoriteFragment())
                .commit()
        }
    }

    fun saveLikeItem(item: SearchModel) {
        if (!likeItem.contains(item)) {
            likeItem.add(item)
        }
    }

    fun removeLikeItem(item: SearchModel) {
        likeItem.remove(item)
    }
}