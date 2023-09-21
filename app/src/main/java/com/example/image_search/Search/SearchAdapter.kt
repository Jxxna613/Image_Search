package com.example.image_search.Search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.image_search.MainActivity
import com.example.image_search.Utils.getDataTimes
import com.example.image_search.databinding.SearchBinding

class SearchAdapter(private val xcontext: Context) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>(){
    var items = ArrayList<SearchModel>()

    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.ItemViewHolder {
        val binding = SearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]

        Glide.with(xcontext)
            .load(currentItem.url)
            .into(holder.search_image)

        holder.heart_icon.visibility = if (currentItem.favorite) View.VISIBLE else View.INVISIBLE
        holder.title.text = currentItem.title
        holder.searchDate.text = getDataTimes(
            currentItem.date,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(binding: SearchBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        var search_image: ImageView = binding.searchImage
        var heart_icon: ImageView = binding.heartIcon
        var title: TextView = binding.searchTitle
        var searchDate: TextView = binding.searchDate
        var constraintItems : ConstraintLayout = binding.searchConstraint

        init {
            heart_icon.visibility = View.GONE
            search_image.setOnClickListener(this)
            constraintItems.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]

            item.favorite = !item.favorite

            if(item.favorite) {
                (xcontext as MainActivity).saveLikeItem(item)
            } else{
                (xcontext as MainActivity).removeLikeItem(item)
            }

            notifyItemChanged(position)
        }
    }
}
