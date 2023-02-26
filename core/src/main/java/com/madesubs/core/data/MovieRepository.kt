package com.madesubs.core.data

import android.util.Log
import com.madesubs.core.data.local.LocalDataSource
import com.madesubs.core.data.remote.RemoteDataSource
import com.madesubs.core.data.remote.network.ApiResponse
import com.madesubs.core.data.remote.response.MovieResponse
import com.madesubs.core.domain.model.Movie
import com.madesubs.core.domain.repository.IMovieRepository
import com.madesubs.core.utils.AppExecutors
import com.madesubs.core.utils.DataMapper
import kotlinx.coroutines.flow.*

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {
//    companion object {
//        @Volatile
//        private var instance: MovieRepository? = null
//
//        fun getInstance(
//            remoteDataSource: RemoteDataSource,
//            localDataSource: LocalDataSource,
//            appExecutors: AppExecutors
//        ): MovieRepository =
//            instance
//                ?: synchronized(this) {
//                    instance
//                        ?: MovieRepository(
//                            remoteDataSource,
//                            localDataSource,
//                            appExecutors
//                        )
//                }
//    }

    override fun getTrendingMovie(): Flow<Resource<List<Movie>>> {
        return flow {
            try {
                emit(Resource.Loading())
                when(val response = remoteDataSource.getTrendList().first()){
                    is ApiResponse.Success -> {
                        emit(Resource.Success<List<Movie>>(DataMapper.mapResponseToDomain(response.data)))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Error<List<Movie>>("No Data Found"))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error<List<Movie>>(response.errorMessage))
                    }
                }
            }catch (e:Exception){
                Log.e("MovieRepository", e.toString())
                emit(Resource.Error<List<Movie>>(e.message.toString()))
            }
        }
    }

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors)
        {
            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getAllMovie().map {
                DataMapper.mapEntitiesToDomain(it)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> =
        localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavorite(movieEntity, newState) }
    }
}