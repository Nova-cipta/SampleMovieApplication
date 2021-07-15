package com.madesubs.core.data.local

import com.madesubs.core.data.local.entity.MovieEntity
import com.madesubs.core.data.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance
                    ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovie(): Flow<List<MovieEntity>> =
        movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> =
        movieDao.getFavoriteMovie()

    suspend fun insertMovie(movie: List<MovieEntity>) =
        movieDao.insertMovie(movie)

    fun setFavorite(
        movieEntity: MovieEntity,
        newState: Boolean
    ) {
        movieEntity.isFavorite = newState
        movieDao.updateMovie(movieEntity)
    }
}