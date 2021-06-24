package com.example.moviecatalogue.model.source.remote.network

import com.example.moviecatalogue.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private lateinit var retrofit : Retrofit
        private var baseEndpoint = BuildConfig.BaseUrl
        fun getClient() : CatalogueAPI {
            retrofit = Retrofit.Builder()
                .baseUrl(baseEndpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CatalogueAPI::class.java)
        }
    }
}