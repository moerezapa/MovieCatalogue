package com.example.moviecatalogue.viewmodel

import com.example.moviecatalogue.model.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val movie = DataDummy.generateDummyMovies()[0]
    private val movieId = movie.movieId

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.selectedMovie(movieId)
    }

    @Test
    fun getMovieInfo() {
        viewModel.selectedMovie(movie.movieId)
        val movieItem = viewModel.getMovieInfo()
        assertNotNull(movieItem)
        assertEquals(movie.movieId, movieItem.movieId)
        assertEquals(movie.title, movieItem.title)
        assertEquals(movie.overview, movieItem.overview)
        assertEquals(movie.director, movieItem.director)
        assertEquals(movie.userScore, movieItem.userScore)
        assertEquals(movie.releaseDate, movieItem.releaseDate)
        assertEquals(movie.imagePath, movieItem.imagePath)
    }
}