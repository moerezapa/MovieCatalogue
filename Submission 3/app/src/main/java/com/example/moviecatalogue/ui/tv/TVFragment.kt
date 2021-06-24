package com.example.moviecatalogue.ui.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentTvBinding
import com.example.moviecatalogue.utils.ViewModelFactory
import com.example.moviecatalogue.viewmodel.MovieAndTVViewModel
import com.example.moviecatalogue.vo.Status

class TVFragment : Fragment(), TVAdapter.OnItemClickCallback {
    private lateinit var tvFragmentTVBinding: FragmentTvBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tvFragmentTVBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return tvFragmentTVBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val factoryViewModel = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factoryViewModel)[MovieAndTVViewModel::class.java]

            val tvAdapter = TVAdapter()

            tvFragmentTVBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTVList().observe(viewLifecycleOwner, { tvList ->
                when (tvList.status) {
                    Status.LOADING -> tvFragmentTVBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        tvFragmentTVBinding.progressBar.visibility = View.GONE
                        tvAdapter.submitList(tvList.data)
                        tvAdapter.setOnItemClickCallback(this)
                        tvAdapter.notifyDataSetChanged()
                    }
                    else -> {
                        tvFragmentTVBinding.progressBar.visibility = View.GONE
                        Toast.makeText(requireActivity(), "Terjadi kesalahan dalam mengambil data", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            with(tvFragmentTVBinding.rvTvs) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    override fun onItemClicked(tvId: String) {
        val intent = Intent(context, DetailTvActivity::class.java)
        intent.putExtra(DetailTvActivity.EXTRA_TV, tvId)
        context?.startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}