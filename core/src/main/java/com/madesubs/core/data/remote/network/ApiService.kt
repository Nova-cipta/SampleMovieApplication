package com.madesubs.core.data.remote.network

import com.madesubs.core.data.remote.response.ListMovieResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("search/movie?api_key=1a53684fdb34a11432d2db69913a1941&language=en-US&page=1&include_adult=false&query=a")
    suspend fun getList(): ListMovieResponse

    @GET("trending/movie/day?api_key=1a53684fdb34a11432d2db69913a1941")
    suspend fun getTrendList(): ListMovieResponse

    @GET("authentication/token/new?api_key=1a53684fdb34a11432d2db69913a1941")
    suspend fun getToken()

    @GET()
    suspend fun getSession()

    @POST()
    suspend fun authenticate()

    @GET()
    suspend fun getAccountId()

    @GET()
    suspend fun getAccountList()

    @GET()
    suspend fun getListDetail()

    @POST()
    suspend fun createList()

    @DELETE()
    suspend fun deleteList()

    @POST()
    suspend fun addMovie()

    @DELETE()
    suspend fun removeMovie()
}