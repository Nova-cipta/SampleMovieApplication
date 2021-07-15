package com.madesubs.myapplication.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.madesubs.core.domain.usecase.MovieUseCase

class HomeViewModel(useCase: MovieUseCase) : ViewModel() {
    val movie = useCase.getAllMovie().asLiveData()
}