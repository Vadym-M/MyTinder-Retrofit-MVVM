package com.vinade.mytinder.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.vinade.mytinder.R
import com.vinade.mytinder.databinding.FragmentPersonBinding
import com.vinade.mytinder.model.Result
import com.vinade.mytinder.repository.PersonRepository
import com.vinade.mytinder.utils.NetworkResult
import com.vinade.mytinder.viewmodel.PersonViewModel
import com.vinade.mytinder.viewmodel.ViewModelFactory


class PersonFragment : Fragment(){

    lateinit var result: Result
    lateinit var binding: FragmentPersonBinding
    lateinit var viewModel: PersonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val repository = PersonRepository()
        viewModel = ViewModelProvider(requireActivity(), ViewModelFactory(repository)).get(PersonViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            when(viewModel.currentPerson){
                is NetworkResult.Success ->{
                    val result = viewModel.currentPerson.data!!.result
                    val sentence = viewModel.currentPerson.data!!.sentence
                    binding.personCountry.text = getString(R.string.country, result.location.country)
                    binding.personCity.text = getString(R.string.city, result.location.city)
                    binding.personGender.text = getString(R.string.gender, result.gender)
                    binding.personeAge.text = getString(R.string.age, result.dob.age.toString())
                    binding.personName.text = "${result.name.first} ${result.name.last}"

                    binding.personAboutMe.text = sentence.toString()
                    loadImage(result.picture.large, binding.personPicture)
                }
                is NetworkResult.Error -> Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()

            }



    }
    private fun loadImage(from:String, to: ImageView){
        Glide.with(requireActivity())
            .load(from)
            .into(to)
    }


    companion object {

        @JvmStatic
        fun newInstance(result: Result) =
            PersonFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}