package com.vinay.omdbapi.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    public const val IMAGE_BASE_URL = "http://www.omdbapi.com/"
    public const val API_KEY = "414edb62"
    public const val BASE_URL = "http://www.omdbapi.com"

    public const val EP_SEARCH = "/?"

    fun getRetrofit() : Retrofit {
        var retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    val apiInterface = getRetrofit().create(ApiInterface::class.java)


}