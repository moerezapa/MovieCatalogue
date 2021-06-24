package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite
import com.example.moviecatalogue.vo.Resource

class DetailViewModel(private val movieAndTVRepository: MovieAndTVRepository) : ViewModel() {
    private lateinit var movieLiveData : LiveData<Resource<MovieFavorite>>
    private lateinit var tvLiveData : LiveData<Resource<TVFavorite>>

    fun setMovie(movieId: Int) {
        movieLiveData = movieAndTVRepository.getMovieDetail(movieId)
    }
    fun setTV(tvId: Int) {
        tvLiveData = movieAndTVRepository.getTVDetail(tvId)
    }

    fun getMovieDetail() : LiveData<Resource<MovieFavorite>> = movieLiveData
    fun getTVDetail() : LiveData<Resource<TVFavorite>> = tvLiveData

    fun setFavoriteMovie() {
        val movieFav = movieLiveData.value
        movieFav?.let { movieFav ->
            movieFav.data?.let {
                val newState = !it.isFavorite
                movieAndTVRepository.setMovieFavorite(it, newState)
            }
        }

    }
    fun setFavoriteTV() {
        val tvFav = tvLiveData.value
        tvFav?.let { tvFav ->
            tvFav.data?.let {
                val newState = !it.isFavorite
                movieAndTVRepository.setTVFavorite(it, newState)
            }
        }

    }
}