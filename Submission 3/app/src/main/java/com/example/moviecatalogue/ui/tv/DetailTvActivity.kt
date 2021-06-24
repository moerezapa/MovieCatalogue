package com.example.moviecatalogue.ui.tv

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityDetailTvBinding
import com.example.moviecatalogue.databinding.ContentDetailTvBinding
import com.example.moviecatalogue.utils.LoadImageHelper.showImage
import com.example.moviecatalogue.utils.ViewModelFactory
import com.example.moviecatalogue.viewmodel.DetailViewModel
import com.example.moviecatalogue.vo.Status
import kotlin.math.round

class DetailTvActivity : AppCompatActivity() {

    private lateinit var detailTvBinding: ActivityDetailTvBinding
    private lateinit var contentDetailTvBinding: ContentDetailTvBinding
    private lateinit var detailViewModel: DetailViewModel
    private var isFavorite: Boolean = true

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.navigation_favorite) {
            detailViewModel.setFavoriteTV()
            return true
        }
        return false
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        invalidateOptionsMenu()
        menu?.let {
            if (!isFavorite)
                it.findItem(R.id.navigation_favorite)?.setIcon(R.drawable.ic_favorite_border)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailTvBinding = ActivityDetailTvBinding.inflate(layoutInflater)
        contentDetailTvBinding = detailTvBinding.detailContent
        setContentView(detailTvBinding.root)

        setSupportActionBar(detailTvBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factoryModelView = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factoryModelView)[DetailViewModel::class.java]
        val extras = intent.extras
        extras?.let {
            val tvId = extras.getString(EXTRA_TV)
            tvId?.let { id ->
                detailViewModel.setTV(id.toInt())
                detailViewModel.getTVDetail().observe(this, { tv ->
                    when (tv.status) {
                        Status.LOADING -> contentDetailTvBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            contentDetailTvBinding.progressBar.visibility = View.GONE
                            tv.data?.let { tvInfo ->
                                supportActionBar?.title = tvInfo.title
                                contentDetailTvBinding.textTitleTv.text = tvInfo.title
                                contentDetailTvBinding.txtUserscoreTv.text = "${round(tvInfo.userScore * 10)} %"
                                contentDetailTvBinding.txtPopularityTv.text = "${tvInfo.popularity}"
                                contentDetailTvBinding.txtOverviewTv.text = tvInfo.overview
                                isFavorite = tvInfo.isFavorite
                                showImage(this, tvInfo.imagePath, contentDetailTvBinding.imagePosterTv)
                            }
                        }
                        else -> {
                            contentDetailTvBinding.progressBar.visibility = View.GONE
                            Toast.makeText(this, "Terjadi kesalahan dalam mengambil data", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    companion object {
        const val EXTRA_TV = "extra_tv"
    }
}