package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.remote.response.MovieResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAndTVRepository: MovieAndTVRepository

    @Mock
    private lateinit var observer: Observer<List<MovieResponse>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieAndTVRepository)
    }

    @Test
    fun getMovieList() {
        val dummyMovies = DataDummy.generateDummyMovieResponseList()
        val movies = MutableLiveData<List<MovieResponse>>()
        movies.value = dummyMovies

        Mockito.`when`(movieAndTVRepository.getMovieList()).thenReturn(movies)
        val movieResponseList = viewModel.getMovieList().value
        Mockito.verify(movieAndTVRepository).getMovieList()
        Assert.assertNotNull(movieResponseList)
        Assert.assertEquals(12, movieResponseList?.size)

        viewModel.getMovieList().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }
}