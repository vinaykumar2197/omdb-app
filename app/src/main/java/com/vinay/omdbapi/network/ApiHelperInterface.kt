package com.vinay.omdbapi.network

import android.net.Uri
import com.vinay.omdbapi.model.MovieResponse
import com.vinay.omdbapi.model.detail.DetailResponse
import com.vinay.omdbapi.network.RetrofitBuilder.API_KEY

class ApiHelperInterface (private val apiInterface: ApiInterface)  : ApiHelper{


    override suspend fun getSearchedMovies(query: String, page: Int, perPage: Int): MovieResponse? {

        val builder = Uri.Builder()
        builder.scheme("http")
            .authority("www.omdbapi.com")
            .appendPath("")
            .appendQueryParameter("apikey", API_KEY)
            .appendQueryParameter("s", query)
            .appendQueryParameter("page", page.toString())

        val myUrl = builder.build().toString()



        return  apiInterface.getSearchedMovies(myUrl)
    }


    override suspend fun getDetail(i: String): DetailResponse? {

        val builder = Uri.Builder()
        builder.scheme("http")
            .authority("www.omdbapi.com")
            .appendPath("")
            .appendQueryParameter("apikey", API_KEY)
            .appendQueryParameter("i", i)


        val myUrl = builder.build().toString()



        return  apiInterface.getDetail(myUrl)
    }


}