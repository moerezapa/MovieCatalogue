package com.example.moviecatalogue.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.databinding.ItemsMovieBinding
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.utils.LoadImageHelper.showImage

class MovieAdapter : PagedListAdapter<MovieFavorite, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsAcademyBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(movieId: String)
    }
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) { this.onItemClickCallback = onItemClickCallback }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieFavorite>() {
            override fun areItemsTheSame(oldItem: MovieFavorite, newItem: MovieFavorite): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieFavorite, newItem: MovieFavorite): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieFavorite) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text = movie.releaseDate
                tvItemOverview.text = movie.overview
                itemView.setOnClickListener { onItemClickCallback.onItemClicked(movie.movieId) }
                showImage(itemView.context, movie.imagePath, imgPoster)
            }
        }
    }
}