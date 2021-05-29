package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.Movie

class MovieViewModel : ViewModel() {

    fun getMovieList() : List<Movie> = DataDummy.generateDummyMovies()
}