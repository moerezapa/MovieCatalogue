package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.utils.ViewModelFactory
import com.example.moviecatalogue.viewmodel.MovieViewModel

class MovieFragment : Fragment() {

    private lateinit var movieFragmentBinding: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieFragmentBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return movieFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val factoryViewModel = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factoryViewModel)[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()

            movieFragmentBinding.progressBar.visibility = View.VISIBLE
            viewModel.getMovieList().observe(viewLifecycleOwner, { movieList ->
                movieFragmentBinding.progressBar.visibility = View.GONE
                movieAdapter.setMovie(movieList, it)
                movieAdapter.notifyDataSetChanged()
            })
            with(movieFragmentBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}