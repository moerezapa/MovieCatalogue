package com.example.moviecatalogue.model.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.model.source.remote.response.MovieResponse
import com.example.moviecatalogue.model.source.remote.response.TVResponse

interface MovieAndTVDataSource {
    fun getMovieList() : LiveData<List<MovieResponse>>
    fun getTVList() : LiveData<List<TVResponse>>
    fun getMovieDetail(movieId: Int) : LiveData<MovieResponse>
    fun getTVDetail(tvId: Int) : LiveData<TVResponse>
}