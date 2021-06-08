package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.remote.response.TVResponse

class DetailTVViewModel(private val movieAndTVRepository: MovieAndTVRepository) : ViewModel() {
    fun getTVDetail(movieId: Int): LiveData<TVResponse> = movieAndTVRepository.getTVDetail(movieId)
}