package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAndTVRepository: MovieAndTVRepository
    @Mock
    private lateinit var tvObserver: Observer<PagedList<TVFavorite>>
    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieFavorite>>
    @Mock
    private lateinit var pagedMovieList: PagedList<MovieFavorite>
    @Mock
    private lateinit var pagedTVList: PagedList<TVFavorite>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieAndTVRepository)
    }

    @Test
    fun getMovieFavoriteList() {
        val dummyMovies = pagedMovieList
        Mockito.`when`(dummyMovies.size).thenReturn(3)
        val movies = MutableLiveData<PagedList<MovieFavorite>>()
        movies.value = dummyMovies

        Mockito.`when`(movieAndTVRepository.getMovieFavoriteList()).thenReturn(movies)
        val movie = viewModel.getMovieFavoriteList().value
        verify(movieAndTVRepository).getMovieFavoriteList()
        Assert.assertNotNull(movie)
        Assert.assertEquals(3, movie?.size)

        viewModel.getMovieFavoriteList().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovies)
    }

    @Test
    fun getTVFavoriteList() {
        val dummyTVs = pagedTVList
        Mockito.`when`(dummyTVs.size).thenReturn(3)
        val tvs = MutableLiveData<PagedList<TVFavorite>>()
        tvs.value = dummyTVs

        Mockito.`when`(movieAndTVRepository.getTVFavoriteList()).thenReturn(tvs)
        val tv = viewModel.getTVFavoriteList().value
        verify(movieAndTVRepository).getTVFavoriteList()
        Assert.assertNotNull(tv)
        Assert.assertEquals(3, tv?.size)

        viewModel.getTVFavoriteList().observeForever(tvObserver)
        verify(tvObserver).onChanged(dummyTVs)
    }

    @Test
    fun setMovieFavorite() {
        val movie = DataDummy.generateDummyMovieList()[0]
        viewModel.setMovieFavorite(movie)
        verify(movieAndTVRepository).setMovieFavorite(movie, true)
        verifyNoMoreInteractions(movieAndTVRepository)
    }

    @Test
    fun setTVFavorite() {
        val tv = DataDummy.generateDummyTVList()[0]
        viewModel.setTVFavorite(tv)
        verify(movieAndTVRepository).setTVFavorite(tv, true)
        verifyNoMoreInteractions(movieAndTVRepository)
    }
}