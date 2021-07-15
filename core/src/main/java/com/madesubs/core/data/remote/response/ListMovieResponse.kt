package com.madesubs.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @field:SerializedName("page")
    val page: String,

    @field:SerializedName("results")
    val results: List<MovieResponse>,

    @field:SerializedName("total_pages")
    val totalPages: String,

    @field:SerializedName("total_results")
    val totalResults: String
)
