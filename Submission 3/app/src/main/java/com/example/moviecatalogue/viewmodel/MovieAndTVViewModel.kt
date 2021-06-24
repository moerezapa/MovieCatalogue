package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.source.MovieAndTVRepository

class MovieAndTVViewModel(private val movieAndTVRepository: MovieAndTVRepository) : ViewModel() {

    fun getMovieList() = movieAndTVRepository.getMovieList()
    fun getTVList() = movieAndTVRepository.getTVList()
}