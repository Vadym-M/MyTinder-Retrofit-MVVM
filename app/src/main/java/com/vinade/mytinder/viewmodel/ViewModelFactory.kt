package com.vinade.mytinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinade.mytinder.repository.PersonRepository

class ViewModelFactory(private val repository: PersonRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PersonViewModel::class.java)){
            PersonViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}