package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.source.MovieAndTVRepository
import com.example.moviecatalogue.model.source.remote.response.TVResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVViewModelTest {
    private lateinit var viewModel: TVViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieAndTVRepository: MovieAndTVRepository

    @Mock
    private lateinit var observer: Observer<List<TVResponse>>

    @Before
    fun setUp() {
        viewModel = TVViewModel(movieAndTVRepository)
    }

    @Test
    fun testGetTVList() {
        val dummyTVs = DataDummy.generateDummyTVResponseList()
        val tvs = MutableLiveData<List<TVResponse>>()
        tvs.value = dummyTVs

        `when`(movieAndTVRepository.getTVList()).thenReturn(tvs)
        val movieResponseList = viewModel.getTVList().value
        verify(movieAndTVRepository).getTVList()
        assertNotNull(movieResponseList)
        assertEquals(12, movieResponseList?.size)

        viewModel.getTVList().observeForever(observer)
        verify(observer).onChanged(dummyTVs)
    }
}