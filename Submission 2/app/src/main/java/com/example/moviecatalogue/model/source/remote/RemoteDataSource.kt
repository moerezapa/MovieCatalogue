package com.example.moviecatalogue.model.source.remote

import com.example.moviecatalogue.model.source.remote.network.MovieCatalogueAPI
import com.example.moviecatalogue.model.source.remote.network.RetrofitClient
import com.example.moviecatalogue.model.source.remote.response.MovieResponse
import com.example.moviecatalogue.model.source.remote.response.TVResponse
import com.example.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    private fun movieAPI() : MovieCatalogueAPI = RetrofitClient.getClient()

    suspend fun getMovieList(movieListCallback: MovieListCallback) {
        EspressoIdlingResource.increment()
        movieAPI().getMovieList().await().result?.let { movielist ->
            movieListCallback.fetchingMovieSucceed(movielist)
            EspressoIdlingResource.decrement() // setiap akhir dari proses Idling Resource harus dalam kondisi netral atau 0
        }

    }
    suspend fun getTVList(tvListCallback: TVListCallback) {
        EspressoIdlingResource.increment()
        movieAPI().getTVList().await().result?.let { tvList ->
            tvListCallback.fetchingTVSucceed(tvList)
            EspressoIdlingResource.decrement() // setiap akhir dari proses Idling Resource harus dalam kondisi netral atau 0
        }
    }

    suspend fun getMovieDetail(movieID: Int, callbackMovieDetail: MovieDetailCallback) {
        EspressoIdlingResource.increment()
        movieAPI().getMovieDetail(movieID).await().let { movieDetail ->
            callbackMovieDetail.fetchingMovieDetailSucceed(movieDetail)
            EspressoIdlingResource.decrement() // setiap akhir dari proses Idling Resource harus dalam kondisi netral atau 0
        }
    }
    suspend fun getTVDetail(tvID: Int, callbackTVDetail: TVDetailCallback) {
        EspressoIdlingResource.increment()
        movieAPI().getTVDetail(tvID).await().let { tvDetail ->
            callbackTVDetail.fetchingTVDetailSucceed(tvDetail)
            EspressoIdlingResource.decrement() // setiap akhir dari proses Idling Resource harus dalam kondisi netral atau 0
        }
    }

    interface MovieListCallback {
        fun fetchingMovieSucceed(movieResponse: List<MovieResponse>)
    }
    interface TVListCallback {
        fun fetchingTVSucceed(tvResponse: List<TVResponse>)
    }
    interface MovieDetailCallback {
        fun fetchingMovieDetailSucceed(movieDetail: MovieResponse)
    }
    interface TVDetailCallback {
        fun fetchingTVDetailSucceed(tvDetail: TVResponse)
    }
}