package com.example.moviecatalogue.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.databinding.ItemsTvBinding
import com.example.moviecatalogue.model.source.local.db.TVFavorite
import com.example.moviecatalogue.utils.LoadImageHelper

class TVFavoriteAdapter : PagedListAdapter<TVFavorite, TVFavoriteAdapter.TVViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val itemsAcademyBinding = ItemsTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(tvId: String)
    }
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) { this.onItemClickCallback = onItemClickCallback }
    fun getSwipedData(swipedPosition: Int): TVFavorite? = getItem(swipedPosition)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TVFavorite>() {
            override fun areItemsTheSame(oldItem: TVFavorite, newItem: TVFavorite): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: TVFavorite, newItem: TVFavorite): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class TVViewHolder(private val binding: ItemsTvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(TV: TVFavorite) {
            with(binding) {
                tvItemTvtitle.text = TV.title
                tvItemCreator.text = "On Air: ${TV.releaseDate}"
                tvItemTvoverview.text = TV.overview
                itemView.setOnClickListener { onItemClickCallback.onItemClicked(TV.tvId) }
                LoadImageHelper.showImage(itemView.context, TV.imagePath, imgTvposter)
            }
        }
    }
}