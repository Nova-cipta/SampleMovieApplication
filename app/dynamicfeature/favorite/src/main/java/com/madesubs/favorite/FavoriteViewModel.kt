package com.madesubs.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.madesubs.core.domain.usecase.MovieUseCase

class FavoriteViewModel(useCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = useCase.getFavoriteMovie().asLiveData()
}