package com.vinade.mytinder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinade.mytinder.model.Person
import com.vinade.mytinder.model.PersonObject
import com.vinade.mytinder.model.Result
import com.vinade.mytinder.model.Sentences
import com.vinade.mytinder.repository.PersonRepository
import com.vinade.mytinder.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class PersonViewModel(private val repository: PersonRepository): ViewModel() {
    companion object{
        const val FIRST_PERSON = "first_person"
        const val NEXT_PERSON = "next_person"
    }
    init {
        getRandomPerson(FIRST_PERSON)
        getRandomPerson(NEXT_PERSON)
    }
    val currentGender = MutableLiveData<String>()


    lateinit var currentPerson: NetworkResult<PersonObject>
    private lateinit var nextPerson: NetworkResult<PersonObject>

    var loading = MutableLiveData<Boolean>()


    private fun getRandomPerson(request: String){
        var persn: NetworkResult<PersonObject>?
        try {
            viewModelScope.launch {



            val responsePerson = async { repository.getPerson()}.await()
            val responseSentence = async { repository.getSentence()}.await()

                persn = if(responsePerson.code() == 200 && responseSentence.code() == 200){
                    (NetworkResult.Success(PersonObject(responsePerson.body()!!.results[0], responseSentence.body()!!.data[0].content)))
                }else{
                    (NetworkResult.Error("ERROR"))
                }
                if(request == FIRST_PERSON){
                    persn?.let { currentPerson = it }
                }else{
                    persn?.let { nextPerson = it }
                }
                loading.postValue(false)
            }
        }catch (e: Exception){
            persn = (NetworkResult.Error("ERROR"))
            loading.postValue(false)
        }

    }


        fun getCurrentPerson(){
           currentPerson = nextPerson

            getRandomPerson(NEXT_PERSON)
            loading.postValue(true)
    }
}