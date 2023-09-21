package com.example.image_search.Adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.image_search.Search.SearchModel

class FavoriteAdapter (var context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    var item = mutableListOf<SearchModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Glide.with(Context)
            .load(item[position].url)
            .info(holder as ItemViewHolder).image)

        holder.
    }
}