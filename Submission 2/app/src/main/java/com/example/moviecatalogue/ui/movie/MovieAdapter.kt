package com.example.moviecatalogue.ui.movie

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ItemsMovieBinding
import com.example.moviecatalogue.model.source.remote.response.MovieResponse
import com.example.moviecatalogue.utils.LoadImageHelper.showImage

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<MovieResponse>()
    private lateinit var activity: Activity

    fun setMovie(movies: List<MovieResponse>, activity: Activity) {
        this.listMovie.clear()
        this.listMovie.addAll(movies)
        this.activity = activity
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsAcademyBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val course = listMovie[position]
        holder.bind(course)
        holder.setActivity(activity)
    }

    override fun getItemCount(): Int = listMovie.size

    class MovieViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var activity: Activity

        fun setActivity(activity: Activity) {
            this.activity = activity
        }
        fun bind(movie: MovieResponse) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text = movie.releaseDate
                tvItemOverview.text = movie.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.movieId)
                    itemView.context.startActivity(intent)
                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
                showImage(itemView.context, movie.imagePath, imgPoster)
            }
        }
    }
}