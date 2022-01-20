package com.vinade.mytinder.repository

import com.vinade.mytinder.api.PersonApi

class PersonRepository(private val personApi: PersonApi) {
    suspend fun getPerson() = personApi.getPerson()
}