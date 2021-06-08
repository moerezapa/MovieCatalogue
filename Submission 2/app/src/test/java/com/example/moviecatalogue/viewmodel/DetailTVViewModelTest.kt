package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.remote.response.TVResponse
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
class DetailTVViewModelTest {

    private lateinit var viewModel: DetailTVViewModel
    private val tv = DataDummy.generateDummyTVResponseList()[0]
    private val tvId = tv.tvId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAndTVRepository: MovieAndTVRepository

    @Mock
    private lateinit var observer: Observer<TVResponse>

    @Before
    fun setUp() {
        viewModel = DetailTVViewModel(movieAndTVRepository)
    }

    @Test
    fun getTVInfo() {
        val tvDummy = MutableLiveData<TVResponse>()
        tvDummy.value = tv

        Mockito.`when`(movieAndTVRepository.getTVDetail(tvId.toInt())).thenReturn(tvDummy)

        val tvData = viewModel.getTVDetail(tvId.toInt()).value as TVResponse

        assertNotNull(tvData)
        assertEquals(tv.tvId, tvData.tvId)
        assertEquals(tv.title, tvData.title)
        assertEquals(tv.overview, tvData.overview)
        assertEquals(tv.releaseDate, tvData.releaseDate)
        assertEquals(tv.popularity, tvData.popularity)
        assertEquals(tv.userScore, tvData.userScore)
        assertEquals(tv.imagePath, tvData.imagePath)

        viewModel.getTVDetail(tvId.toInt()).observeForever(observer)
        Mockito.verify(observer).onChanged(tv)
    }
}