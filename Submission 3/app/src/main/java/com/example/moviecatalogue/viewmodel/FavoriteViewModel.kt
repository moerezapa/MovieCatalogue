package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite

class FavoriteViewModel(private val movieAndTVRepository: MovieAndTVRepository): ViewModel() {
    fun getMovieFavoriteList() = movieAndTVRepository.getMovieFavoriteList()
    fun getTVFavoriteList() = movieAndTVRepository.getTVFavoriteList()

    fun setMovieFavorite(movieFavorite: MovieFavorite) {
        val newState = !movieFavorite.isFavorite
        movieAndTVRepository.setMovieFavorite(movieFavorite, newState)
    }
    fun setTVFavorite(tvFavorite: TVFavorite) {
        val newState = !tvFavorite.isFavorite
        movieAndTVRepository.setTVFavorite(tvFavorite, newState)
    }
}