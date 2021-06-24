package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.moviecatalogue.databinding.ContentDetailMovieBinding
import com.example.moviecatalogue.utils.LoadImageHelper
import com.example.moviecatalogue.utils.ViewModelFactory
import com.example.moviecatalogue.viewmodel.DetailViewModel
import com.example.moviecatalogue.vo.Status
import kotlin.math.round

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    private lateinit var detailViewModel: DetailViewModel

    private var isFavorite: Boolean = true

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.navigation_favorite) {
            detailViewModel.setFavoriteMovie()
            return true
        }
        return false
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        invalidateOptionsMenu()
        menu?.let {
            if (!isFavorite)
                it.findItem(R.id.navigation_favorite)?.setIcon(R.drawable.ic_favorite_border)
        }
        return super.onPrepareOptionsMenu(menu)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = detailMovieBinding.detailContent
        setContentView(detailMovieBinding.root)

        setSupportActionBar(detailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factoryModelView = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factoryModelView)[DetailViewModel::class.java]
        val extras = intent.extras
        extras?.let {
            val movieId = extras.getString(EXTRA_MOVIE)
            movieId?.let { id ->
                detailViewModel.setMovie(id.toInt())
                detailViewModel.getMovieDetail().observe(this, { movie ->
                    when (movie.status) {
                        Status.LOADING -> contentDetailMovieBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            contentDetailMovieBinding.progressBar.visibility = View.GONE
                            movie.data?.let { movieInfo ->
                                supportActionBar?.title = movieInfo.title
                                contentDetailMovieBinding.textTitleMovie.text = movieInfo.title
                                contentDetailMovieBinding.txtUserscoreMovie.text = "${round(movieInfo.userScore * 10)} %"
                                contentDetailMovieBinding.txtPopularityMovie.text = "${movieInfo.popularity}"
                                contentDetailMovieBinding.txtOverviewMovie.text = movieInfo.overview
                                contentDetailMovieBinding.txtReleasedateMovie.text = movieInfo.releaseDate
                                isFavorite = movieInfo.isFavorite
                                LoadImageHelper.showImage(this, movieInfo.imagePath, contentDetailMovieBinding.imagePosterMovie)
                            }
                        }
                        else -> {
                            contentDetailMovieBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Terjadi kesalahan dalam mengambil data", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}