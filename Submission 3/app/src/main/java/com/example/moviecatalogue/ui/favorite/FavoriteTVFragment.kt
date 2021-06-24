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
import com.example.moviecatalogue.databinding.FragmentTvFavoriteBinding
import com.example.moviecatalogue.ui.tv.DetailTvActivity
import com.example.moviecatalogue.utils.ViewModelFactory
import com.example.moviecatalogue.viewmodel.FavoriteViewModel
import com.google.android.material.snackbar.Snackbar

class FavoriteTVFragment : Fragment(), TVFavoriteAdapter.OnItemClickCallback {

    private lateinit var tvFragmentBinding: FragmentTvFavoriteBinding
    private lateinit var favoriteTVViewModel: FavoriteViewModel
    private lateinit var factoryViewModel : ViewModelFactory
    private lateinit var favoriteTVAdapter: TVFavoriteAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tvFragmentBinding = FragmentTvFavoriteBinding.inflate(layoutInflater, container, false)
        return tvFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(tvFragmentBinding.rvTvsFavorite)

        activity?.let {
            factoryViewModel = ViewModelFactory.getInstance(requireActivity())
            favoriteTVViewModel = ViewModelProvider(this, factoryViewModel)[FavoriteViewModel::class.java]
            favoriteTVAdapter = TVFavoriteAdapter()
            tvFragmentBinding.progressBarTvfavorite.visibility = View.VISIBLE
            favoriteTVViewModel.getTVFavoriteList().observe(viewLifecycleOwner, { tvList ->
                tvList?.let {
                    tvFragmentBinding.progressBarTvfavorite.visibility = View.GONE
                    favoriteTVAdapter.submitList(it)
                    favoriteTVAdapter.setOnItemClickCallback(this)
                    favoriteTVAdapter.notifyDataSetChanged()
                }
            })
            with(tvFragmentBinding.rvTvsFavorite) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTVAdapter
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
                val movieFav = favoriteTVAdapter.getSwipedData(swipedPosition)
                movieFav?.let { favoriteTVViewModel.setTVFavorite(it) }
                val snackBar = Snackbar.make(view as View, R.string.message_snackbar, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.message_undo) { movieFav?.let { favoriteTVViewModel.setTVFavorite(it) } }
                snackBar.show()
            }
        }
    })

    override fun onItemClicked(tvId: String) {
        val intent = Intent(context, DetailTvActivity::class.java)
        intent.putExtra(DetailTvActivity.EXTRA_TV, tvId)
        context?.startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}