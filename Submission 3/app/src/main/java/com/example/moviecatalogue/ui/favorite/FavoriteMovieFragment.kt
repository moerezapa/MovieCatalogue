package com.example.moviecatalogue.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import com.example.moviecatalogue.ui.movie.DetailMovieActivity
import com.example.moviecatalogue.utils.ViewModelFactory
import com.example.moviecatalogue.viewmodel.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar

class FavoriteMovieFragment : Fragment(), MovieFavoriteAdapter.OnItemClickCallback {

    private lateinit var movieFragmentBinding: FragmentMovieFavoriteBinding
    private lateinit var factoryViewModel : ViewModelFactory
    private lateinit var favoriteMovieViewModel: FavoriteViewModel
    private lateinit var movieFavoriteAdapter: MovieFavoriteAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        movieFragmentBinding = FragmentMovieFavoriteBinding.inflate(layoutInflater, container, false)
        return movieFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(movieFragmentBinding.rvMoviesFavorite)

        activity?.let {
            factoryViewModel = ViewModelFactory.getInstance(requireActivity())
            favoriteMovieViewModel = ViewModelProvider(this, factoryViewModel)[FavoriteViewModel::class.java]

            movieFavoriteAdapter = MovieFavoriteAdapter()
            movieFragmentBinding.progressBarMoviefavorite.visibility = View.VISIBLE
            favoriteMovieViewModel.getMovieFavoriteList().observe(viewLifecycleOwner, {movieList ->
                movieList?.let {
                    movieFragmentBinding.progressBarMoviefavorite.visibility = View.GONE
                    movieFavoriteAdapter.submitList(it)
                    movieFavoriteAdapter.setOnItemClickCallback(this)
                    movieFavoriteAdapter.notifyDataSetChanged()
                }
            })
            with(movieFragmentBinding.rvMoviesFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieFavoriteAdapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            view?.let {
                val swipedPosition = viewHolder.adapterPosition
                val movieFav = movieFavoriteAdapter.getSwipedData(swipedPosition)
                movieFav?.let { favoriteMovieViewModel.setMovieFavorite(it) }
                val snackBar = Snackbar.make(view as View, R.string.message_snackbar, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.message_undo) { v ->
                    movieFav?.let { favoriteMovieViewModel.setMovieFavorite(it) }
                }
                snackBar.show()
            }
        }
    })

    override fun onItemClicked(movieId: String) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movieId)
        context?.startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}