package com.example.moviecatalogue.model.source.remote.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private lateinit var retrofit : Retrofit
        private var baseEndpoint = "https://api.themoviedb.org/3/"
        const val apiKey = "12e9a4179676130733c36f4c28358b1f"
        fun getClient() : MovieCatalogueAPI {
            retrofit = Retrofit.Builder()
                .baseUrl(baseEndpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(MovieCatalogueAPI::class.java)
        }
    }
}