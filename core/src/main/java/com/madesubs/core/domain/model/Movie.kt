package com.madesubs.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val popularity: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: String,
    val backdropPath: String?,
    val posterPath: String?,
    val isFavorite: Boolean
) : Parcelable
