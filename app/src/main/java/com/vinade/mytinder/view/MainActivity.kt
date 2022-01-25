package com.vinade.mytinder.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.vinade.mytinder.R
import com.vinade.mytinder.api.RetrofitClient
import com.vinade.mytinder.databinding.ActivityMainBinding
import com.vinade.mytinder.model.Result
import com.vinade.mytinder.repository.PersonRepository
import com.vinade.mytinder.utils.Navigator
import com.vinade.mytinder.viewmodel.PersonViewModel
import com.vinade.mytinder.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity(), Navigator{

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        hideButtons()

        val repository = PersonRepository()
        viewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(PersonViewModel::class.java)
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                add(R.id.fragment_container, StartFragment())
            }
        }


        viewModel.loading.observe(this, {
            val btn = binding.btnLike
            btn.isEnabled = !it
        })

        binding.btnGo.setOnClickListener {
            showButtons()
            refreshFragment(PersonFragment())

        }
        binding.btnLike.setOnClickListener {
            refreshFragment(PersonFragment())
            viewModel.getCurrentPerson()
        }

    }


    private fun refreshFragment(fragment: Fragment){
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)}
    }

    private fun hideButtons() {
        binding.btnDislike.visibility = View.GONE
        binding.btnLike.visibility = View.GONE
        binding.btnGo.visibility = View.GONE
    }

    private fun showButtons() {
        binding.btnDislike.visibility = View.VISIBLE
        binding.btnLike.visibility = View.VISIBLE
        binding.btnGo.visibility = View.GONE
    }

    override fun btnLike() {
        refreshFragment(PersonFragment())
    }

    override fun btnDisLike() {

    }

    override fun chooseGender(gender: String) {
        binding.btnGo.visibility = View.VISIBLE
        //viewModel.currentGender.value = gender

    }

}