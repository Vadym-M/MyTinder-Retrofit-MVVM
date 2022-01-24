package com.vinade.mytinder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinade.mytinder.model.Person
import com.vinade.mytinder.model.PersonObject
import com.vinade.mytinder.repository.PersonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class PersonViewModel(private val repository: PersonRepository): ViewModel() {

    init {
        getRandomPerson()
    }
    val currentGender = MutableLiveData<String>()


    private val _currentPerson = MutableLiveData<PersonObject>()
            val currentPerson:LiveData<PersonObject>
            get()= _currentPerson

    private val _exception = MutableLiveData<String>()
    val exception:LiveData<String>
        get()= _exception

    lateinit var person: PersonObject

    private fun getRandomPerson(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val currentPerson = getPersonObject()
                if(::person.isInitialized){
                    person = currentPerson!!
                }else{
                    _currentPerson.postValue(currentPerson!!)
                    person = getPersonObject()!!
                }
            }catch (e: Exception){
                _exception.postValue("Oops! An Error Occurred, Too Many Requests")
            }

        }
    }

    private suspend fun getPersonObject(): PersonObject? {
        val prsn = repository.getPerson()
        val sentence = repository.getSentence()
        if(prsn.isSuccessful && sentence.isSuccessful){
          return PersonObject(prsn.body()!!.results[0], sentence.body()!!.data[0].content)
        }
        return null
    }
    fun getCurrentPerson(){
        _currentPerson.value = person
        getRandomPerson()
    }
}