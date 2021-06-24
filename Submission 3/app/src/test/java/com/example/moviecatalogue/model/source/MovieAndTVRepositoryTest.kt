package com.example.moviecatalogue.model.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.LiveDataTestUtil
import com.example.moviecatalogue.model.PagedListTestUtil
import com.example.moviecatalogue.model.source.local.LocalDataSource
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite
import com.example.moviecatalogue.model.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class MovieAndTVRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val fakeMovieAndTVRepository = FakeMovieAndTVRepository(remoteDataSource, localDataSource, appExecutors)

    private val movieList = DataDummy.generateDummyMovieList()
    private val tvList = DataDummy.generateDummyTVList()
    private val movieDummy = movieList[0]
    private val tvDummy = tvList[0]
    private val movieId = movieDummy.movieId
    private val tvId = tvDummy.tvId


    @Test
    fun getMovieList() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieFavorite>
        `when`(localDataSource.getMovieList()).thenReturn(dataSource)
        fakeMovieAndTVRepository.getMovieList()

        val movies = Resource.success(PagedListTestUtil.mockPagedList(DataDummy.generateDummyMovieList()))
        verify(localDataSource).getMovieList()
        assertNotNull(movies)
        assertEquals(movieList.size, movies.data?.size)
    }

    @Test
    fun getTVList() {
        val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVFavorite>
        `when`(localDataSource.getTVList()).thenReturn(dataSource)
        fakeMovieAndTVRepository.getTVList()

        val tvs = Resource.success(PagedListTestUtil.mockPagedList(DataDummy.generateDummyTVList()))
        verify(localDataSource).getTVList()
        assertNotNull(tvs)
        assertEquals(tvList.size, tvs.data?.size)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetail = MutableLiveData<MovieFavorite>()
        dummyDetail.value = movieDummy
        `when`(localDataSource.getMovieFavoriteDetail(movieId.toInt())).thenReturn(dummyDetail)

        val movie = LiveDataTestUtil.getValue(fakeMovieAndTVRepository.getMovieDetail(movieId.toInt()))
        verify(localDataSource).getMovieFavoriteDetail(movieId.toInt())
        assertNotNull(movie)
        assertEquals(movieDummy.id, movie.data?.id)
    }

    @Test
    fun getTVDetail() {
        val dummyDetail = MutableLiveData<TVFavorite>()
        dummyDetail.value = tvDummy
        `when`(localDataSource.getTVFavoriteDetail(tvId.toInt())).thenReturn(dummyDetail)

        val tv = LiveDataTestUtil.getValue(fakeMovieAndTVRepository.getTVDetail(tvId.toInt()))
        verify(localDataSource).getTVFavoriteDetail(tvId.toInt())
        assertNotNull(tv)
        assertEquals(tvDummy.id, tv.data?.id)
    }

    @Test
    fun getMovieFavoriteDetail() {
        val dummyDetail = MutableLiveData<MovieFavorite>()
        dummyDetail.value = movieDummy
        `when`(localDataSource.getMovieFavoriteDetail(movieId.toInt())).thenReturn(dummyDetail)

        val movie = LiveDataTestUtil.getValue(fakeMovieAndTVRepository.getMovieDetail(movieId.toInt()))
        verify(localDataSource).getMovieFavoriteDetail(movieId.toInt())
        assertNotNull(movie)
        assertEquals(movieDummy.id, movie.data?.id)
    }

    @Test
    fun getTVFavoriteDetail() {
        val dummyDetail = MutableLiveData<TVFavorite>()
        dummyDetail.value = tvDummy
        `when`(localDataSource.getTVFavoriteDetail(tvId.toInt())).thenReturn(dummyDetail)

        val tv = LiveDataTestUtil.getValue(fakeMovieAndTVRepository.getTVDetail(tvId.toInt()))
        verify(localDataSource).getTVFavoriteDetail(tvId.toInt())
        assertNotNull(tv)
        assertEquals(tvDummy.id, tv.data?.id)
    }

    @Test
    fun getMovieFavoriteList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieFavorite>
        `when`(localDataSource.getMovieFavoriteList()).thenReturn(dataSourceFactory)
        fakeMovieAndTVRepository.getMovieFavoriteList()

        val movies = Resource.success(PagedListTestUtil.mockPagedList(DataDummy.generateDummyMovieList()))
        verify(localDataSource).getMovieFavoriteList()
        assertNotNull(movies)
        assertEquals(movieList.size, movies.data?.size)
    }

    @Test
    fun getTVFavoriteList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVFavorite>
        `when`(localDataSource.getTVFavoriteList()).thenReturn(dataSourceFactory)
        fakeMovieAndTVRepository.getTVFavoriteList()

        val tvs = Resource.success(PagedListTestUtil.mockPagedList(DataDummy.generateDummyTVList()))
        verify(localDataSource).getTVFavoriteList()
        assertNotNull(tvs)
        assertEquals(tvList.size, tvs.data?.size)
    }

    @Test
    fun setMovieFavorite() {
        val movie = DataDummy.generateDummyMovieList()[0]
        fakeMovieAndTVRepository.setMovieFavorite(movie, true)
        verify(localDataSource).updateMovieFavorite(movie, true)
        verifyNoMoreInteractions(localDataSource)
    }

    @Test
    fun setTVFavorite() {
        fakeMovieAndTVRepository.setTVFavorite(tvList[0], true)
        verify(localDataSource).updateTVFavorite(tvList[0], true)
        verifyNoMoreInteractions(localDataSource)
    }
}