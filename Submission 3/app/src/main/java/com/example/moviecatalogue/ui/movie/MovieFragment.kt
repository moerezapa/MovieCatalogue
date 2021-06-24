package com.example.moviecatalogue.ui.movie

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
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.utils.ViewModelFactory
import com.example.moviecatalogue.viewmodel.MovieAndTVViewModel
import com.example.moviecatalogue.vo.Status

class MovieFragment : Fragment(), MovieAdapter.OnItemClickCallback {

    private lateinit var movieFragmentBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieFragmentBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return movieFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val factoryViewModel = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factoryViewModel)[MovieAndTVViewModel::class.java]

            val movieAdapter = MovieAdapter()

            movieFragmentBinding.progressBar.visibility = View.VISIBLE
            viewModel.getMovieList().observe(viewLifecycleOwner, { movieList ->
                when (movieList.status) {
                    Status.LOADING -> movieFragmentBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        movieFragmentBinding.progressBar.visibility = View.GONE
                        movieAdapter.submitList(movieList.data)
                        movieAdapter.setOnItemClickCallback(this)
                        movieAdapter.notifyDataSetChanged()
                    }
                    else -> {
                        movieFragmentBinding.progressBar.visibility = View.GONE
                        Toast.makeText(requireActivity(), "Terjadi kesalahan dalam mengambil data", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            with(movieFragmentBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onItemClicked(movieId: String) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieId)
        context?.startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}