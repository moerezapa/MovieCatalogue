package com.example.moviecatalogue.viewmodel

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovieList() {
        val movies = viewModel.getMovieList()
        Assert.assertNotNull(movies)
        Assert.assertEquals(12, movies.size) // test ukuran array
    }
}