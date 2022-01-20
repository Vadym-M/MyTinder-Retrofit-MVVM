package com.vinade.mytinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.vinade.mytinder.api.RetrofitClient
import com.vinade.mytinder.repository.PersonRepository
import com.vinade.mytinder.viewmodel.PersonViewModel
import com.vinade.mytinder.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = PersonRepository(RetrofitClient.api)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(PersonViewModel::class.java)
        viewModel.getRandomPerson()
        viewModel.person.observe(this, {
            Log.d("debug", "Person name: " + it.results[0].name)
    })
    }
}