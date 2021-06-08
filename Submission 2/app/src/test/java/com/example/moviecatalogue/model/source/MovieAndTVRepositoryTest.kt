package com.example.moviecatalogue.model.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.LiveDataTestUtil
import com.example.moviecatalogue.model.source.remote.RemoteDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock

class MovieAndTVRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val fakeMovieAndTVRepository = FakeMovieAndTVRepository(remote)

    private val movieResponsesList = DataDummy.generateDummyMovieResponseList()
    private val tvResponsesList = DataDummy.generateDummyTVResponseList()
    private val movieId = movieResponsesList[0].movieId
    private val tvId = tvResponsesList[0].tvId
    private val movieResponse = DataDummy.generateDummyMovieResponseList()[0]
    private val tvResponse = DataDummy.generateDummyTVResponseList()[0]

    @Test
    fun getMovieList() {
        /**
         * RunBlocking:
         * Runs a new coroutine and blocks the current thread interruptible until its completion
         *
         * alternatif nggak nge suspend getMovieList() (?)
         */
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.MovieListCallback)
                        .fetchingMovieSucceed(movieResponsesList)
                null
            }.`when`(remote).getMovieList(any())
        }
        val movie = LiveDataTestUtil.getValue(fakeMovieAndTVRepository.getMovieList())
        runBlocking {
            verify(remote).getMovieList(any())
        }
        assertNotNull(movie)
        assertEquals(movieResponsesList.size.toLong(), movie.size.toLong())
    }

    @Test
    fun getTVList() {
        /**
         * RunBlocking:
         * Runs a new coroutine and blocks the current thread interruptible until its completion
         *
         * alternatif nggak nge suspend getMovieList() (?)
         */
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.TVListCallback)
                        .fetchingTVSucceed(tvResponsesList)
                null
            }.`when`(remote).getTVList(any())
        }
        val tv = LiveDataTestUtil.getValue(fakeMovieAndTVRepository.getTVList())
        runBlocking {
            verify(remote).getTVList(any())
        }
        assertNotNull(tv)
        assertEquals(tvResponsesList.size.toLong(), tv.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.MovieDetailCallback)
                        .fetchingMovieDetailSucceed(movieResponse)
                null
            }.`when`(remote).getMovieDetail(eq(movieId.toInt()), any())
        }

        val movie = LiveDataTestUtil.getValue(fakeMovieAndTVRepository.getMovieDetail(movieId.toInt()))
        runBlocking {
            verify(remote)
                    .getMovieDetail(eq(movieId.toInt()), any())
        }

        assertNotNull(movie)
    }

    @Test
    fun getTVDetail() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.TVDetailCallback)
                        .fetchingTVDetailSucceed(tvResponse)
                null
            }.`when`(remote).getTVDetail(eq(tvId.toInt()), any())
        }

        val tv = LiveDataTestUtil.getValue(fakeMovieAndTVRepository.getTVDetail(tvId.toInt()))
        runBlocking {
            verify(remote)
                    .getTVDetail(eq(tvId.toInt()), any())
        }

        assertNotNull(tv)
    }
}