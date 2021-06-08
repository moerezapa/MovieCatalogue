package com.example.moviecatalogue.model.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.model.source.remote.RemoteDataSource
import com.example.moviecatalogue.model.source.remote.response.MovieResponse
import com.example.moviecatalogue.model.source.remote.response.TVResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieAndTVRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieAndTVDataSource {

    companion object {
        @Volatile
        private var instance: MovieAndTVRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MovieAndTVRepository =
            instance ?: synchronized(this) {
                instance ?: MovieAndTVRepository(remoteData).apply { instance = this }
            }
    }

    override fun getMovieList(): LiveData<List<MovieResponse>> {
        val listMovieResult = MutableLiveData<List<MovieResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieList(object : RemoteDataSource.MovieListCallback {
                override fun fetchingMovieSucceed(movieResponse: List<MovieResponse>) {
                    val movies = ArrayList<MovieResponse>()
                    for (movieItem in movieResponse) {
                        val movie = MovieResponse(
                                movieItem.movieId,
                                movieItem.title,
                                movieItem.overview,
                                movieItem.userScore,
                                movieItem.popularity,
                                movieItem.releaseDate,
                                movieItem.imagePath
                        )
                        movies.add(movie)
                    }
                    listMovieResult.postValue(movies)
                }

            })
        }

        return listMovieResult
    }

    override fun getTVList(): LiveData<List<TVResponse>> {
        val listTVResult = MutableLiveData<List<TVResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTVList(object : RemoteDataSource.TVListCallback {
                override fun fetchingTVSucceed(tvResponse: List<TVResponse>) {
                    val tvs = ArrayList<TVResponse>()
                    for (tvItem in tvResponse) {
                        val tv = TVResponse(
                                tvItem.tvId,
                                tvItem.title,
                                tvItem.overview,
                                tvItem.userScore,
                                tvItem.popularity,
                                tvItem.releaseDate,
                                tvItem.imagePath
                        )
                        tvs.add(tv)
                    }
                    listTVResult.postValue(tvs)
                }

            })
        }

        return listTVResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieResponse> {
        val movie = MutableLiveData<MovieResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.MovieDetailCallback{
                override fun fetchingMovieDetailSucceed(movieDetail: MovieResponse) {
                    val movieInfo = MovieResponse(
                            movieDetail.movieId,
                            movieDetail.title,
                            movieDetail.overview,
                            movieDetail.userScore,
                            movieDetail.popularity,
                            movieDetail.releaseDate,
                            movieDetail.imagePath
                    )
                    movie.postValue(movieInfo)
                }

            })
        }
        return movie
    }

    override fun getTVDetail(tvId: Int): LiveData<TVResponse> {
        val tv = MutableLiveData<TVResponse>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTVDetail(tvId, object : RemoteDataSource.TVDetailCallback{
                override fun fetchingTVDetailSucceed(tvDetail: TVResponse) {
                    val tvInfo = TVResponse(
                            tvDetail.tvId,
                            tvDetail.title,
                            tvDetail.overview,
                            tvDetail.userScore,
                            tvDetail.popularity,
                            tvDetail.releaseDate,
                            tvDetail.imagePath
                    )
                    tv.postValue(tvInfo)
                }

            })
        }
        return tv
    }

}