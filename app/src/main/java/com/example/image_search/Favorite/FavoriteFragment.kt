package com.example.image_search.Favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.image_search.MainActivity
import com.example.image_search.Search.SearchModel
import com.example.image_search.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private lateinit var adapter: FavoriteAdapter
    private lateinit var xContext: Context

    private var heart: List<SearchModel> = listOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        xContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = activity as MainActivity
        heart = mainActivity.likeItem

        adapter = FavoriteAdapter(xContext).apply {
            items = heart.toMutableList()
        }

        binding = FragmentFavoriteBinding.inflate(inflater, container, false).apply {
            favoriteRecyclerview.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            favoriteRecyclerview.adapter = adapter
        }
        return binding?.root
    }

        override fun onDestroy() {
            super.onDestroy()
            binding = null
        }
    }