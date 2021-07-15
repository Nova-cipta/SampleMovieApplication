package com.madesubs.core.utils

import com.madesubs.core.data.local.entity.MovieEntity
import com.madesubs.core.data.remote.response.MovieResponse
import com.madesubs.core.domain.model.Movie

object DataMapper {
    fun mapResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                popularity = it.popularity,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                popularity = it.popularity,
                overview = it.overview,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                backdropPath = it.backdropPath,
                posterPath = it.posterPath,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        popularity = input.popularity,
        overview = input.overview,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        backdropPath = input.backdropPath,
        posterPath = input.posterPath,
        isFavorite = input.isFavorite
    )
}