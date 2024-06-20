package com.capstone.wishata.ui.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.capstone.wishata.R
import com.capstone.wishata.databinding.FragmentFilterBinding
import com.capstone.wishata.utils.showToast

class FilterFragment : Fragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!

    // prepare variable
    private var category: String? = null
    private var environment: String? = null
    private var scenery: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_filterFragment_to_navigation_home)
        }

        // Save to preferences or
        binding.buttonApply.setOnClickListener {
            showToast("Category: $category\n, Environment: $environment\n, " +
                    "Scenery: $scenery", requireContext())

            // back to home
            if (category == null || environment == null || scenery != null) {
                findNavController().navigate(R.id.action_filterFragment_to_navigation_home)
            } else {
                showToast("Mohon setidaknya ada 1 filter yang dipilih!", requireContext())
            }

        }

        setupRadioListener()
    }


    // Get value from all radio button, save to global variable: Category, Environment, Scenery
    private fun setupRadioListener() {
        // Category
        binding.radioGroupCategory.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                binding.radioAttraction.id -> {
                    category = binding.radioAttraction.text.toString()
                    Log.e("TAG FILTER", "Tag Selected: $category")
                }

                binding.radioGreenery.id -> {
                    category = binding.radioGreenery.text.toString()
                    Log.e("TAG FILTER", "Tag Selected: $category")
                }

                binding.radioHistorical.id -> {
                    category = binding.radioHistorical.text.toString()
                    Log.e("TAG FILTER", "Tag Selected: $category")
                }
            }
        }

        // Environment
        binding.radioGroupEnvironment.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                binding.radioLand.id -> {
                    environment = binding.radioLand.text.toString()
                    Log.e("TAG FILTER", "Tag Selected: $environment")
                }
                binding.radioWater.id -> {
                    environment = binding.radioWater.text.toString()
                    Log.e("TAG FILTER", "Tag Selected: $environment")
                }
            }
        }

        // Scenery
        binding.radioGroupScenery.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                binding.radioNature.id -> {
                    scenery = binding.radioNature.text.toString()
                    Log.e("TAG FILTER", "Tag Selected: $scenery")
                }
                binding.radioUrban.id -> {
                    scenery = binding.radioUrban.text.toString()
                    Log.e("TAG FILTER", "Tag Selected: $scenery")
                }

            }
        }
    }

    companion object {
    }
}