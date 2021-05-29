package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.moviecatalogue.databinding.ContentDetailMovieBinding
import com.example.moviecatalogue.viewmodel.DetailMovieViewModel

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = detailMovieBinding.detailContent
        setContentView(detailMovieBinding.root)

        setSupportActionBar(detailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailMovieViewModel::class.java]
        val extras = intent.extras
        extras?.let {
            val movieId = extras.getString(EXTRA_MOVIE)
            movieId?.let {
                viewModel.selectedMovie(movieId)
                val movieInfo = viewModel.getMovieInfo()
                contentDetailMovieBinding.textTitleMovie.text = movieInfo.title
                contentDetailMovieBinding.txtUserscoreMovie.text = movieInfo.userScore
                contentDetailMovieBinding.txtDirector.text = movieInfo.director
                contentDetailMovieBinding.txtOverviewMovie.text = movieInfo.overview
                contentDetailMovieBinding.txtReleasedateMovie.text = movieInfo.releaseDate

                Glide.with(this)
                        .load(movieInfo.imagePath)
                        .transform(RoundedCorners(20))
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error))
                        .into(contentDetailMovieBinding.imagePosterMovie)
            }
        }
    }
}