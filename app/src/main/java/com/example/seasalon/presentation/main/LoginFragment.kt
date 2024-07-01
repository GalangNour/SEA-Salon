package com.example.seasalon.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.seasalon.databinding.FragmentLoginBinding
import com.example.seasalon.presentation.state.State
import com.example.seasalon.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegisterHere.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmail.editText?.text.toString()
            val password = binding.editTextPassword.editText?.text.toString()

            Log.d("LoginFragment", "Email: $email, Password: $password")  // Debug log

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show()
                Log.d("LoginFragment", "Empty fields detected")  // Debug log
                return@setOnClickListener
            } else {
                userViewModel.loginUser(email, password)
                userViewModel.state.observe(viewLifecycleOwner) { state ->
                    when (state) {
                        is State.Error -> {
                            Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                            Log.e("LoginFragment", state.error)
                        }
                        State.Loading -> {
                            // do nothing
                            Log.d("LoginFragment", "Loading state")  // Debug log
                        }
                        is State.Success -> {
                            Toast.makeText(requireContext(), "Login Success, welcome back ${state.data.userFullName}", Toast.LENGTH_SHORT).show()
                            Log.d("LoginFragment", "Login success")  // Debug log
                            // Uncomment the next line if you want to navigate to another screen after login
                            // findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        }
                    }
                }
            }
        }

        binding.tvPageTitle.text = "SEA Salon \n Login Member"


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}