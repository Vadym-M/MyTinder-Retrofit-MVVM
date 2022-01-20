package com.vinade.mytinder.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val BASE_URL = "https://randomuser.me/api/"

    val retrofitClient: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val api: PersonApi by lazy {
        retrofitClient.build().create(PersonApi::class.java)
    }
}