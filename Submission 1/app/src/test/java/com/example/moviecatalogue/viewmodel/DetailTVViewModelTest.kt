package com.example.moviecatalogue.viewmodel

import com.example.moviecatalogue.model.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailTVViewModelTest {

    private lateinit var viewModel: DetailTVViewModel
    private val tv = DataDummy.generateDummyTVs()[0]
    private val tvId = tv.tvId

    @Before
    fun setUp() {
        viewModel = DetailTVViewModel()
        viewModel.selectedTV(tvId)
    }

    @Test
    fun getMovieInfo() {
        viewModel.selectedTV(tv.tvId)
        val movieItem = viewModel.getTVInfo()
        assertNotNull(movieItem)
        assertEquals(tv.tvId, movieItem.tvId)
        assertEquals(tv.title, movieItem.title)
        assertEquals(tv.overview, movieItem.overview)
        assertEquals(tv.creator, movieItem.creator)
        assertEquals(tv.userScore, movieItem.userScore)
        assertEquals(tv.imagePath, movieItem.imagePath)
    }
}