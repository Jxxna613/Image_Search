package com.example.image_search.Favorite

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.image_search.Search.SearchAdapter
import com.example.image_search.Search.SearchModel
import com.example.image_search.Utils
import com.example.image_search.databinding.SearchBinding

class FavoriteAdapter (var xContext : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder> () {

    var items = mutableListOf<SearchModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val binding = SearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(xContext)
            .load(items[position].url)
            .into((holder as FavoriteAdapter.ItemViewHolder).search_image)

        holder.title.text = items[position].title
        holder.heart_icon.visibility = View.GONE
        holder.searchDate.text = Utils.getDataTimes(
            items[position].date,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: SearchBinding) : RecyclerView.ViewHolder(binding.root){

        var search_image: ImageView = binding.searchImage
        var heart_icon: ImageView = binding.heartIcon
        var title: TextView = binding.searchTitle
        var searchDate: TextView = binding.searchDate
        var constraintItems: ConstraintLayout = binding.searchConstraint

        init{
            heart_icon.visibility = View.GONE
            constraintItems.setOnClickListener{
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    items.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }
}