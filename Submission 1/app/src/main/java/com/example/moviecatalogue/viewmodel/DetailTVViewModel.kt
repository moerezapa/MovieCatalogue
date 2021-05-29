package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.DataDummy
import com.example.moviecatalogue.model.TV

class DetailTVViewModel : ViewModel() {

    private lateinit var tvId : String

    fun selectedTV(tvId: String) {
        this.tvId = tvId
    }

    fun getTVInfo(): TV {
        lateinit var tv: TV
        val tvLists = DataDummy.generateDummyTVs()
        for (tvItem in tvLists) {
            if (tvItem.tvId == tvId)
                tv = tvItem
        }
        return tv
    }
}