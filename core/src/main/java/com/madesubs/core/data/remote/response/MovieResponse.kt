package com.madesubs.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    var id: String,

    @field:SerializedName("title")
    var title: String,

    @field:SerializedName("popularity")
    var popularity: String,

    @field:SerializedName("overview")
    var overview: String,

    @field:SerializedName("release_date")
    var releaseDate: String,

    @field:SerializedName("vote_average")
    var voteAverage: String,

    @field:SerializedName("backdrop_path")
    var backdropPath: String,

    @field:SerializedName("poster_path")
    var posterPath: String,
)
