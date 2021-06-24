package com.example.moviecatalogue.model.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.model.source.remote.network.CatalogueAPI
import com.example.moviecatalogue.model.source.remote.network.RetrofitClient
import com.example.moviecatalogue.model.source.remote.response.MovieResponse
import com.example.moviecatalogue.model.source.remote.response.TVResponse
import com.example.moviecatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class RemoteDataSource {

    private fun movieAPI() : CatalogueAPI = RetrofitClient.getClient()

    fun getMovieList(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        val movieResponseList = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = movieAPI().getMovieList().await()
                response.result?.let {
                    movieResponseList.postValue(ApiResponse.success(it))
                }
            } catch (e: IOException) {
                e.printStackTrace()
                movieResponseList.postValue( ApiResponse.error(e.message.toString(), mutableListOf()))
            }
        }
        EspressoIdlingResource.decrement()
        return movieResponseList
    }

    fun getTVList(): LiveData<ApiResponse<List<TVResponse>>> {
        EspressoIdlingResource.increment()
        val tvResponseList = MutableLiveData<ApiResponse<List<TVResponse>>>()
        CoroutineScope(IO).launch {
            try {
                val response = movieAPI().getTVList().await()
                response.result?.let {
                    tvResponseList.postValue(ApiResponse.success(it))
                }
            } catch (e: IOException) {
                e.printStackTrace()
                tvResponseList.postValue( ApiResponse.error(e.message.toString(), mutableListOf()))
            }
        }
        EspressoIdlingResource.decrement()
        return tvResponseList
    }

    fun getMovieDetail(movieID: Int) : LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val movie = MutableLiveData<ApiResponse<MovieResponse>>()
        CoroutineScope(IO).launch {
            movieAPI().getMovieDetail(movieID).await().let { movieDetail ->
                movie.postValue(ApiResponse.success(movieDetail))
                EspressoIdlingResource.decrement() // setiap akhir dari proses Idling Resource harus dalam kondisi netral atau 0
            }
        }
        return movie
    }
    fun getTVDetail(tvID: Int) : LiveData<ApiResponse<TVResponse>> {
        EspressoIdlingResource.increment()
        val tv = MutableLiveData<ApiResponse<TVResponse>>()
        CoroutineScope(IO).launch {
            movieAPI().getTVDetail(tvID).await().let { tvDetail ->
                tv.postValue(ApiResponse.success(tvDetail))
                EspressoIdlingResource.decrement() // setiap akhir dari proses Idling Resource harus dalam kondisi netral atau 0
            }
        }
        return tv
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }
}