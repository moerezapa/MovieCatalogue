package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.moviecatalogue.databinding.ContentDetailMovieBinding
import com.example.moviecatalogue.utils.LoadImageHelper
import com.example.moviecatalogue.utils.ViewModelFactory
import com.example.moviecatalogue.viewmodel.DetailMovieViewModel
import kotlin.math.round

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

        val factoryModelView = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factoryModelView)[DetailMovieViewModel::class.java]
        val extras = intent.extras
        extras?.let {
            val movieId = extras.getString(EXTRA_MOVIE)
            movieId?.let {
                viewModel.getMovieDetail(it.toInt()).observe(this, { movieInfo ->
                    contentDetailMovieBinding.textTitleMovie.text = movieInfo.title
                    contentDetailMovieBinding.txtUserscoreMovie.text = "${round(movieInfo.userScore * 10)} %"
                    contentDetailMovieBinding.txtPopularityMovie.text = "${movieInfo.popularity}"
                    contentDetailMovieBinding.txtOverviewMovie.text = movieInfo.overview
                    contentDetailMovieBinding.txtReleasedateMovie.text = movieInfo.releaseDate

                    LoadImageHelper.showImage(this, movieInfo.imagePath, contentDetailMovieBinding.imagePosterMovie)
                })
            }
        }
    }
}