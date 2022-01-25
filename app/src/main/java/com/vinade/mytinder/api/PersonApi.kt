package com.vinade.mytinder.api

import com.vinade.mytinder.model.Person
import com.vinade.mytinder.model.Sentences
import com.vinade.mytinder.utils.NetworkResult
import retrofit2.Response
import retrofit2.http.GET

interface PersonApi {

    @GET("api")
    suspend fun getPerson(): Response<Person>

    @GET("api/v1/texts?_quantity=1&_characters=500")
    suspend fun getSentence(): Response<Sentences>
}