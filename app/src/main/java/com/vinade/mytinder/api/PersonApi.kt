package com.vinade.mytinder.api

import com.vinade.mytinder.model.Person
import retrofit2.Response
import retrofit2.http.GET

interface PersonApi {

    @GET
    suspend fun getPerson(): Response<Person>
}