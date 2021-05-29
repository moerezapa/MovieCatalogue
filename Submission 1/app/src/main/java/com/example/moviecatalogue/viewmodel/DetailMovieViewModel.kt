package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.Movie

class DetailMovieViewModel : ViewModel() {

    private lateinit var movieId : String

    fun selectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovieInfo(): Movie {
        lateinit var movie: Movie
        val movieLists = DataDummy.generateDummyMovies()
        for (movieItem in movieLists) {
            if (movieItem.movieId == movieId)
                movie = movieItem
        }
        return movie
    }
}