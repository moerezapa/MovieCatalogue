package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.remote.response.TVResponse

class TVViewModel(private val movieAndTVRepository: MovieAndTVRepository) : ViewModel() {

    fun getTVList(): LiveData<List<TVResponse>> = movieAndTVRepository.getTVList()
}