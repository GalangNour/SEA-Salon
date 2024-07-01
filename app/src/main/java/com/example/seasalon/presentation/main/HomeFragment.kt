package com.example.seasalon.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.seasalon.databinding.FragmentHomeBinding
import com.example.seasalon.domain.model.Service
import com.example.seasalon.presentation.adapter.AdapterService
import com.example.seasalon.presentation.state.State
import com.example.seasalon.presentation.viewmodel.ServiceViewModel
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), AdapterService.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val serviceViewModel: ServiceViewModel by viewModels()
    private lateinit var serviceListAdapter: AdapterService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        serviceViewModel.getAllServices()
        serviceViewModel.state.observe(viewLifecycleOwner){state->
            when(state){
                is State.Error -> {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_SHORT).show()
                    Log.e("HomeFragment", state.error)

                }
                State.Loading -> {

                }
                is State.Success -> {
                    serviceListAdapter = AdapterService(state.data, this)
                    binding.rvServiceList.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
                    val snapHelper = CarouselSnapHelper()
                    snapHelper.attachToRecyclerView(binding.rvServiceList)
                    binding.rvServiceList.adapter = serviceListAdapter

                }
            }
        }


    }

    override fun onItemClick(service: Service) {

    }
}