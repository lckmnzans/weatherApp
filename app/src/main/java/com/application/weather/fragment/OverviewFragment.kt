package com.application.weather.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.application.weather.R
import com.application.weather.Utils
import com.application.weather.databinding.FragmentOverviewBinding
import com.application.weather.viewmodel.HomeViewModel

class OverviewFragment : Fragment() {
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Utils.isDaytime()) {
            binding.ivDay.setImageResource(R.drawable.ic_sunrise)
        } else {
            binding.ivDay.setImageResource(R.drawable.ic_sunset)
        }
        binding.tvDay.text = Utils.getCurrentDay()

        viewModel.cityName.observe(viewLifecycleOwner) {
            binding.tvCityName.text = it
        }
        viewModel.weatherCond.observe(viewLifecycleOwner) {
            binding.tvWeatherCond.text = it
        }
        viewModel.weatherTemp.observe(viewLifecycleOwner) {
            binding.tvWeatherTemp.text = it.toString()
        }
    }
}