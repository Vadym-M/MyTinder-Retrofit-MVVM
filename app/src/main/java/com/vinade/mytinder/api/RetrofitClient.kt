package com.vinade.mytinder.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val BASE_URL_PERSON = "https://randomuser.me"
    private const val BASE_URL_SENTENCE = "https://fakerapi.it"

    private val retrofitPerson: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(BASE_URL_PERSON)
            .addConverterFactory(GsonConverterFactory.create())
    }

    private val retrofitSentence: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(BASE_URL_SENTENCE)
            .addConverterFactory(GsonConverterFactory.create())
    }
    val apiSentence: PersonApi by lazy {
        retrofitSentence.build().create(PersonApi::class.java)
    }

    val api: PersonApi by lazy {
        retrofitPerson.build().create(PersonApi::class.java)

    }
}