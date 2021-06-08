package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.remote.response.MovieResponse

class DetailMovieViewModel(private val movieAndTVRepository: MovieAndTVRepository) : ViewModel() {
    fun getMovieDetail(movieId: Int): LiveData<MovieResponse> = movieAndTVRepository.getMovieDetail(movieId)
}