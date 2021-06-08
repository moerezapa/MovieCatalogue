package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.remote.response.MovieResponse
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val movie = DataDummy.generateDummyMovieResponseList()[0]
    private val movieId = movie.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAndTVRepository: MovieAndTVRepository

    @Mock
    private lateinit var observer: Observer<MovieResponse>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieAndTVRepository)
    }

    @Test
    fun getMovieInfo() {
        val movieDummy = MutableLiveData<MovieResponse>()
        movieDummy.value = movie

        Mockito.`when`(movieAndTVRepository.getMovieDetail(movieId.toInt())).thenReturn(movieDummy)

        val movieData = viewModel.getMovieDetail(movieId.toInt()).value as MovieResponse

        assertNotNull(movieData)
        assertEquals(movie.movieId, movieData.movieId)
        assertEquals(movie.title, movieData.title)
        assertEquals(movie.overview, movieData.overview)
        assertEquals(movie.releaseDate, movieData.releaseDate)
        assertEquals(movie.userScore, movieData.userScore)
        assertEquals(movie.popularity, movieData.popularity)
        assertEquals(movie.imagePath, movieData.imagePath)

        viewModel.getMovieDetail(movieId.toInt()).observeForever(observer)
        Mockito.verify(observer).onChanged(movie)
    }
}