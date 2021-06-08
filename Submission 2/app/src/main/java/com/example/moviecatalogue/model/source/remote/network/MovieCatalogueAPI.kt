package com.example.moviecatalogue.model.source.remote.network

import com.example.moviecatalogue.model.source.remote.response.MovieResponse
import com.example.moviecatalogue.model.source.remote.response.Response
import com.example.moviecatalogue.model.source.remote.response.TVResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieCatalogueAPI {
    @GET("movie/now_playing")
    fun getMovieList(
        @Query("api_key") apiKey: String = RetrofitClient.apiKey
    ) : Call<Response<MovieResponse>>

    @GET("tv/on_the_air")
    fun getTVList(
        @Query("api_key") apiKey: String = RetrofitClient.apiKey
    ) : Call<Response<TVResponse>>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey: String = RetrofitClient.apiKey
    ) : Call<MovieResponse>

    @GET("tv/{tv_id}")
    fun getTVDetail(
        @Path("tv_id") tvId : Int,
        @Query("api_key") apiKey: String = RetrofitClient.apiKey
    ) : Call<TVResponse>
}