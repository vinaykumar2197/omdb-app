package com.vinay.omdbapi.network

import com.vinay.omdbapi.model.MovieResponse
import com.vinay.omdbapi.model.detail.DetailResponse

interface ApiHelper {


    suspend fun getSearchedMovies(query :String, page : Int , perPage : Int) : MovieResponse?

    suspend fun getDetail(i :String) : DetailResponse?


}