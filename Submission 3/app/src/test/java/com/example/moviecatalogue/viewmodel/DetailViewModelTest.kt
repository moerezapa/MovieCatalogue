package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite
import com.example.moviecatalogue.vo.Resource
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val tvDummy = DataDummy.generateDummyTVList()[0]
    private val movieDummy = DataDummy.generateDummyMovieList()[0]
    private val tvId = tvDummy.tvId
    private val movieId = movieDummy.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAndTVRepository: MovieAndTVRepository

    @Mock
    private lateinit var movieDetailObserver: Observer<Resource<MovieFavorite>>
    @Mock
    private lateinit var tvDetailObserver: Observer<Resource<TVFavorite>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieAndTVRepository)
    }

    @Test
    fun getMovieInfo() {
        val dummyDetailMovie = Resource.success(movieDummy)
        val movie = MutableLiveData<Resource<MovieFavorite>>()
        movie.value = dummyDetailMovie

        `when`(movieAndTVRepository.getMovieDetail(movieId.toInt())).thenReturn(movie)
        viewModel.setMovie(movieId.toInt())
        verify(movieAndTVRepository).getMovieDetail(movieId.toInt())

        val movieData = viewModel.getMovieDetail().value
        Assert.assertNotNull(movieData)
        Assert.assertEquals(dummyDetailMovie.data?.movieId, movieData?.data?.movieId)
        Assert.assertEquals(dummyDetailMovie.data?.title, movieData?.data?.title)
        Assert.assertEquals(dummyDetailMovie.data?.overview, movieData?.data?.overview)
        Assert.assertEquals(dummyDetailMovie.data?.releaseDate, movieData?.data?.releaseDate)
        Assert.assertEquals(dummyDetailMovie.data?.userScore, movieData?.data?.userScore)
        Assert.assertEquals(dummyDetailMovie.data?.popularity, movieData?.data?.popularity)
        Assert.assertEquals(dummyDetailMovie.data?.imagePath, movieData?.data?.imagePath)

        viewModel.getMovieDetail().observeForever(movieDetailObserver)
        verify(movieDetailObserver).onChanged(dummyDetailMovie)
    }

    @Test
    fun getTVInfo() {
        val dummyDetailTV = Resource.success(tvDummy)
        val tv = MutableLiveData<Resource<TVFavorite>>()
        tv.value = dummyDetailTV

        `when`(movieAndTVRepository.getTVDetail(tvId.toInt())).thenReturn(tv)
        viewModel.setTV(tvId.toInt())
        verify(movieAndTVRepository).getTVDetail(tvId.toInt())

        val tvData = viewModel.getTVDetail().value
        Assert.assertNotNull(tvData)
        Assert.assertEquals(dummyDetailTV.data?.tvId, tvData?.data?.tvId)
        Assert.assertEquals(dummyDetailTV.data?.title, tvData?.data?.title)
        Assert.assertEquals(dummyDetailTV.data?.overview, tvData?.data?.overview)
        Assert.assertEquals(dummyDetailTV.data?.releaseDate, tvData?.data?.releaseDate)
        Assert.assertEquals(dummyDetailTV.data?.userScore, tvData?.data?.userScore)
        Assert.assertEquals(dummyDetailTV.data?.popularity, tvData?.data?.popularity)
        Assert.assertEquals(dummyDetailTV.data?.imagePath, tvData?.data?.imagePath)

        viewModel.getTVDetail().observeForever(tvDetailObserver)
        verify(tvDetailObserver).onChanged(dummyDetailTV)
    }
}