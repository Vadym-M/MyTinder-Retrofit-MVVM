package com.vinade.mytinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.vinade.mytinder.api.RetrofitClient
import com.vinade.mytinder.databinding.ActivityMainBinding
import com.vinade.mytinder.repository.PersonRepository
import com.vinade.mytinder.viewmodel.PersonViewModel
import com.vinade.mytinder.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: PersonViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager.commit {
                add(R.id.fragment_container, StartFragment())
            }
        }

        val repository = PersonRepository(RetrofitClient.api)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(PersonViewModel::class.java)
        viewModel.getRandomPerson()
        viewModel.person.observe(this, {
            Log.d("debug", "Person name: " + it.results[0].name)
    })

    }
}