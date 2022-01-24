package com.vinade.mytinder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vinade.mytinder.databinding.FragmentStartBinding
import com.vinade.mytinder.utils.navigator

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        binding.chipFemale.setOnClickListener {
            with(binding.chipFemale){
                isChecked = true
            }
            with(binding.chipMale){
                isChecked = false
            }

            chooseGender(FEMALE)
        }
        binding.chipMale.setOnClickListener{
            with(binding.chipFemale){
                isChecked = false
            }
            with(binding.chipMale){
                isChecked = true
            }
            chooseGender(MALE)
        }
        return binding.root
    }
    private fun chooseGender(gender: String){
        navigator().chooseGender(gender)
    }

    companion object {
        @JvmStatic val MALE = "male"
        @JvmStatic val FEMALE = "female"
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}