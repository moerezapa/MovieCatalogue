package com.example.moviecatalogue.ui.tv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ActivityDetailTvBinding
import com.example.moviecatalogue.databinding.ContentDetailTvBinding
import com.example.moviecatalogue.viewmodel.DetailTVViewModel

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

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailTVViewModel::class.java]
        val extras = intent.extras
        extras?.let {
            val tvId = extras.getString(EXTRA_TV)
            tvId?.let {
                viewModel.selectedTV(tvId)
                val tvInfo = viewModel.getTVInfo()
                contentDetailTvBinding.textTitleTv.text = tvInfo.title
                contentDetailTvBinding.txtUserscoreTv.text = tvInfo.userScore
                contentDetailTvBinding.txtCreator.text = tvInfo.creator
                contentDetailTvBinding.txtOverviewTv.text = tvInfo.overview

                Glide.with(this)
                    .load(tvInfo.imagePath)
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(contentDetailTvBinding.imagePosterTv)

            }
        }
    }
}