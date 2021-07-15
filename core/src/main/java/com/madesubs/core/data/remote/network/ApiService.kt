package com.madesubs.core.data.remote.network

import com.madesubs.core.data.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("search/movie?api_key=1a53684fdb34a11432d2db69913a1941&language=en-US&page=1&include_adult=false&query=a")
    suspend fun getList(): ListMovieResponse
}