package com.madesubs.myapplication.di

import com.madesubs.core.domain.usecase.MovieInteractor
import com.madesubs.core.domain.usecase.MovieUseCase
import com.madesubs.myapplication.detail.DetailViewModel
import com.madesubs.myapplication.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usecaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}