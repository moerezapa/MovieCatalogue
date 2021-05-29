package com.example.moviecatalogue.viewmodel

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TVViewModelTest {

    private lateinit var viewModel: TVViewModel

    @Before
    fun setUp() {
        viewModel = TVViewModel()
    }

    @Test
    fun testGetTVList() {
        val tv = viewModel.getTVList()
        Assert.assertNotNull(tv)
        Assert.assertEquals(12, tv.size) // test ukuran array
    }
}