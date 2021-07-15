package com.madesubs.myapplication.detail

import androidx.lifecycle.ViewModel
import com.madesubs.core.domain.model.Movie
import com.madesubs.core.domain.usecase.MovieUseCase

class DetailViewModel(private val useCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        useCase.setFavoriteMovie(movie, newState)
}