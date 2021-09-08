package com.vinay.omdbapi.network

import com.vinay.omdbapi.model.MovieResponse
import com.vinay.omdbapi.model.detail.DetailResponse
import com.vinay.omdbapi.network.RetrofitBuilder.EP_SEARCH
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiInterface {

    @GET()
    suspend fun getSearchedMovies(@Url url : String): MovieResponse?

    @GET()
    suspend fun getDetail(@Url url : String): DetailResponse?


}