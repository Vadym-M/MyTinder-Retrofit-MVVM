package com.vinade.mytinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vinade.mytinder.model.Person
import com.vinade.mytinder.repository.PersonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonViewModel(private val repository: PersonRepository): ViewModel() {

    private val _person = MutableLiveData<Person>()
            val person:LiveData<Person>
            get()= _person

    fun getRandomPerson(){
        CoroutineScope(Dispatchers.IO).launch {
            val person = repository.getPerson()
            _person.postValue(person.body())
        }
    }
}