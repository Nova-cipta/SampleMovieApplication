package com.madesubs.core.domain.usecase

import com.madesubs.core.data.Resource
import com.madesubs.core.domain.model.Movie
import com.madesubs.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getTrendingMovie(): Flow<Resource<List<Movie>>> = movieRepository.getTrendingMovie()

    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, status: Boolean) =
        movieRepository.setFavoriteMovie(movie, status)
}