package com.example.seasalon.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seasalon.R
import com.example.seasalon.databinding.FragmentRegisterBinding
import com.example.seasalon.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val userFullName = binding.editTextUsername.editText?.text.toString()
            val userEmail = binding.editTextEmail.editText?.text.toString()
            val userPassword = binding.editTextPassword.editText?.text.toString()
            val userPhone = binding.editTextPhone.editText?.text.toString()
            val confirmPassword = binding.editTextPasswordConfirm.editText?.text.toString()

            if (userFullName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || userPhone.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (userPassword != confirmPassword) {
                Toast.makeText(requireContext(), "Password does not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            userViewModel.registerUser(userFullName, userEmail, userPassword, userPhone)
            Toast.makeText(requireContext(), "Register Success", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

        binding.tvPageTitle.text = "SEA Salon \n Register Member"


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}