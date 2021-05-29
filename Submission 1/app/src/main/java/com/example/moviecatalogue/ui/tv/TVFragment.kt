package com.example.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentTvBinding
import com.example.moviecatalogue.viewmodel.TVViewModel

class TVFragment : Fragment() {

    private lateinit var TVViewModel: TVViewModel
    private lateinit var tvFragmentTVBinding: FragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tvFragmentTVBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return tvFragmentTVBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            TVViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[com.example.moviecatalogue.viewmodel.TVViewModel::class.java]
            val tv = TVViewModel.getTVList()

            val tvAdapter = TVAdapter()
            tvAdapter.setTV(tv, it)

            with(tvFragmentTVBinding.rvTvs) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }
}