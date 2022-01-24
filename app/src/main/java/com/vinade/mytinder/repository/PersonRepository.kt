package com.vinade.mytinder.repository

import com.vinade.mytinder.api.PersonApi
import com.vinade.mytinder.api.RetrofitClient

class PersonRepository() {
    suspend fun getPerson() = RetrofitClient.api.getPerson()
     suspend fun getSentence() = RetrofitClient.apiSentence.getSentence()
}