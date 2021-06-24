package com.example.moviecatalogue.model.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.model.source.local.LocalDataSource
import com.example.moviecatalogue.model.source.local.db.MovieFavorite
import com.example.moviecatalogue.model.source.local.db.TVFavorite
import com.example.moviecatalogue.model.source.remote.ApiResponse
import com.example.moviecatalogue.model.source.remote.RemoteDataSource
import com.example.moviecatalogue.model.source.remote.response.MovieResponse
import com.example.moviecatalogue.model.source.remote.response.TVResponse
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.vo.Resource

class MovieAndTVRepository private constructor(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors) : MovieAndTVDataSource {

    override fun getMovieList(): LiveData<Resource<PagedList<MovieFavorite>>> {
        return object : NetworkBoundResource<PagedList<MovieFavorite>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieFavorite>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getMovieList(), config).build() // membaca dari LocalDataSource
            }

            override fun shouldFetch(data: PagedList<MovieFavorite>?): Boolean = data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> = remoteDataSource.getMovieList()

            public override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieFavorite>()
                for (movieItem in data) {
                    val movie = MovieFavorite(
                            null,
                            movieItem.movieId,
                            movieItem.title,
                            movieItem.overview,
                            movieItem.userScore,
                            movieItem.popularity,
                            movieItem.releaseDate,
                            movieItem.imagePath,
                            false
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovieFavorite(movieList)
            }

        }.asLiveData()
    }

    override fun getTVList(): LiveData<Resource<PagedList<TVFavorite>>> {
        return object : NetworkBoundResource<PagedList<TVFavorite>, List<TVResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TVFavorite>> {
                val config = PagedList.Config.Builder().apply {
                    setEnablePlaceholders(false)
                    setInitialLoadSizeHint(4)
                    setPageSize(4)
                }.build()
                return LivePagedListBuilder(localDataSource.getTVList(), config).build()
            }

            override fun shouldFetch(data: PagedList<TVFavorite>?): Boolean = data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TVResponse>>> = remoteDataSource.getTVList()

            public override fun saveCallResult(data: List<TVResponse>) {
                val tvList = ArrayList<TVFavorite>()
                for (movieItem in data) {
                    val tv = TVFavorite(
                            null,
                            movieItem.tvId,
                            movieItem.title,
                            movieItem.overview,
                            movieItem.userScore,
                            movieItem.popularity,
                            movieItem.releaseDate,
                            movieItem.imagePath,
                            false
                    )
                    tvList.add(tv)
                }

                localDataSource.insertTVFavorite(tvList)
            }

        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieFavorite>> {
        return object : NetworkBoundResource<MovieFavorite, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieFavorite> = localDataSource.getMovieFavoriteDetail(movieId)
            override fun shouldFetch(data: MovieFavorite?): Boolean = data == null
            override fun createCall(): LiveData<ApiResponse<MovieResponse>> = remoteDataSource.getMovieDetail(movieId)
            override fun saveCallResult(movieItem: MovieResponse) {
                val movie = MovieFavorite(
                        null,
                        movieItem.movieId,
                        movieItem.title,
                        movieItem.overview,
                        movieItem.userScore,
                        movieItem.popularity,
                        movieItem.releaseDate,
                        movieItem.imagePath,
                        false
                )
                localDataSource.updateMovieFavorite(movie, false)
            }
        }.asLiveData()
    }
    override fun getTVDetail(tvId: Int): LiveData<Resource<TVFavorite>> {
        return object : NetworkBoundResource<TVFavorite, TVResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TVFavorite> = localDataSource.getTVFavoriteDetail(tvId)
            override fun shouldFetch(data: TVFavorite?): Boolean = data == null
            override fun createCall(): LiveData<ApiResponse<TVResponse>> = remoteDataSource.getTVDetail(tvId)
            override fun saveCallResult(tvItem: TVResponse) {
                val tv = TVFavorite(
                        null,
                        tvItem.tvId,
                        tvItem.title,
                        tvItem.overview,
                        tvItem.userScore,
                        tvItem.popularity,
                        tvItem.releaseDate,
                        tvItem.imagePath,
                        false
                )
                localDataSource.updateTVFavorite(tv, false)
            }
        }.asLiveData()
    }

    override fun getMovieFavoriteList(): LiveData<PagedList<MovieFavorite>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getMovieFavoriteList(), config).build()
    }

    override fun getTVFavoriteList(): LiveData<PagedList<TVFavorite>> {
        val config = PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(4)
            setPageSize(4)
        }.build()
        return LivePagedListBuilder(localDataSource.getTVFavoriteList(), config).build()
    }

    override fun setMovieFavorite(movieFavorite: MovieFavorite, newState: Boolean) { appExecutors.diskIO().execute { localDataSource.updateMovieFavorite(movieFavorite, newState) } }

    override fun setTVFavorite(tvFavorite: TVFavorite, newState: Boolean) { appExecutors.diskIO().execute { localDataSource.updateTVFavorite(tvFavorite, newState) } }

    companion object {
        @Volatile
        private var instance: MovieAndTVRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): MovieAndTVRepository =
            instance ?: synchronized(this) {
                instance ?: MovieAndTVRepository(remoteData, localDataSource, appExecutors).apply { instance = this }
            }
    }
}