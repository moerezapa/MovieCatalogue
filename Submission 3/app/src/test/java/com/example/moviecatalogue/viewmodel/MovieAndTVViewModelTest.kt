package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite
import com.example.moviecatalogue.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieAndTVViewModelTest {
    private lateinit var viewModel: MovieAndTVViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAndTVRepository: MovieAndTVRepository
    @Mock
    private lateinit var tvObserver: Observer<Resource<PagedList<TVFavorite>>>
    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<MovieFavorite>>>
    @Mock
    private lateinit var pagedMovieList: PagedList<MovieFavorite>
    @Mock
    private lateinit var pagedTVList: PagedList<TVFavorite>

    @Before
    fun setUp() {
        viewModel = MovieAndTVViewModel(movieAndTVRepository)
    }

    @Test
    fun getMovieList() {
        val dummyMovies = Resource.success(pagedMovieList)
        `when`(dummyMovies.data?.size).thenReturn(3)
        val movies = MutableLiveData<Resource<PagedList<MovieFavorite>>>()
        movies.value = dummyMovies

        `when`(movieAndTVRepository.getMovieList()).thenReturn(movies)
        val movie = viewModel.getMovieList().value?.data
        verify(movieAndTVRepository).getMovieList()
        assertNotNull(movie)
        assertEquals(3, movie?.size)

        viewModel.getMovieList().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTVList() {
        val dummyTVs = Resource.success(pagedTVList)
        `when`(dummyTVs.data?.size).thenReturn(3)
        val tvs = MutableLiveData<Resource<PagedList<TVFavorite>>>()
        tvs.value = dummyTVs

        `when`(movieAndTVRepository.getTVList()).thenReturn(tvs)
        val tv = viewModel.getTVList().value?.data
        verify(movieAndTVRepository).getTVList()
        assertNotNull(tv)
        assertEquals(3, tv?.size)

        viewModel.getTVList().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTVs)
    }
}