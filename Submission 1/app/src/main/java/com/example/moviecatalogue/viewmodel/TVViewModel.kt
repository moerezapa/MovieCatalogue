package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.TV

class TVViewModel : ViewModel() {

    fun getTVList() : List<TV> = DataDummy.generateDummyTVs()
}