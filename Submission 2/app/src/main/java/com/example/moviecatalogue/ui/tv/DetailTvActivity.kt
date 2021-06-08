package com.example.moviecatalogue.ui.tv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.databinding.ActivityDetailTvBinding
import com.example.moviecatalogue.databinding.ContentDetailTvBinding
import com.example.moviecatalogue.utils.LoadImageHelper.showImage
import com.example.moviecatalogue.utils.ViewModelFactory
import com.example.moviecatalogue.viewmodel.DetailTVViewModel
import kotlin.math.round

class DetailTvActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV = "extra_tv"
    }
    private lateinit var detailTvBinding: ActivityDetailTvBinding
    private lateinit var contentDetailTvBinding: ContentDetailTvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailTvBinding = ActivityDetailTvBinding.inflate(layoutInflater)
        contentDetailTvBinding = detailTvBinding.detailContent
        setContentView(detailTvBinding.root)

        setSupportActionBar(detailTvBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factoryModelView = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factoryModelView)[DetailTVViewModel::class.java]
        val extras = intent.extras
        extras?.let {
            val tvId = extras.getString(EXTRA_TV)
            tvId?.let {
                viewModel.getTVDetail(it.toInt()).observe(this, { tvInfo ->
                    contentDetailTvBinding.textTitleTv.text = tvInfo.title
                    contentDetailTvBinding.txtUserscoreTv.text = "${round(tvInfo.userScore * 10)} %"
                    contentDetailTvBinding.txtPopularityTv.text = "${tvInfo.popularity}"
                    contentDetailTvBinding.txtOverviewTv.text = tvInfo.overview

                    showImage(this, tvInfo.imagePath, contentDetailTvBinding.imagePosterTv)
                })
            }
        }
    }
}