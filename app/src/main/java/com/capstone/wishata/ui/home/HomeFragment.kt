package com.capstone.wishata.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.wishata.R
import com.capstone.wishata.adapter.HomeWisataAdapter
import com.capstone.wishata.adapter.TopPlaceAdapter
import com.capstone.wishata.data.network.response.TopWisataResponse
import com.capstone.wishata.databinding.FragmentHomeBinding
import com.capstone.wishata.utils.Result
import com.capstone.wishata.utils.showToast
import com.capstone.wishata.viewmodel.HomeViewModel
import com.capstone.wishata.viewmodel.factory.ViewModelFactory
import com.google.android.material.search.SearchBar

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchBar: SearchBar = view.findViewById(R.id.searchBar)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_wishlist, R.id.navigation_profile)
        )

        val navHostFragment = NavHostFragment.findNavController(this@HomeFragment)
        NavigationUI.setupWithNavController(searchBar, navHostFragment, appBarConfiguration)


        binding.searchBar.setOnMenuItemClickListener {  menu ->
            when(menu.itemId) {
                R.id.action_filter -> {
                    findNavController().navigate(R.id.action_navigation_home_to_filterFragment)
                    true
                }

                else -> {
                    false
                }
            }

        }

        /*binding.searchView.setupWithSearchBar(binding.searchBar)
        binding.searchView.editText
            .setOnEditorActionListener { textView, actionId, event ->
                searchBar.setText(binding.searchView.text)
                binding.searchView.hide()
                Toast.makeText(requireContext(), binding.searchView.text, Toast.LENGTH_SHORT).show()
                false
            }*/


        // tring to save location to room


        fetchTopPlace()
        fetchNearestPlace()
    }

    // Top Location
    private fun fetchTopPlace() {
        val topPlaceAdapter = TopPlaceAdapter()

        binding.rvTopPlaces.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        homeViewModel.getTopPlace().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Success -> {
                        topPlaceAdapter.setData(result.data.data)
                        binding.rvTopPlaces.adapter = topPlaceAdapter
                        binding.progressIndicatorTop.visibility = View.GONE
                    }

                    is Result.Error -> {
                        binding.progressIndicatorTop.visibility = View.GONE
                        showToast(result.error, requireContext())
                    }

                    is Result.Loading -> {
                        binding.progressIndicatorTop.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    // Nearest Location. Setting adapter, Layout, Set Data to adapter
    private fun fetchNearestPlace() {
        val wisataAdapter = HomeWisataAdapter()

        binding.rvNearestPlaces.layoutManager = GridLayoutManager(requireContext(), 2)

        homeViewModel.getWisata().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Success -> {
                        binding.progressIndicatorNearest.visibility = View.GONE
                        wisataAdapter.setData(result.data.data)
                        binding.rvNearestPlaces.adapter = wisataAdapter
                    }

                    is Result.Error -> {
                        binding.progressIndicatorNearest.visibility = View.GONE
                        showToast(result.error, requireContext())
                    }

                    is Result.Loading -> {
                        binding.progressIndicatorNearest.visibility = View.VISIBLE
                    }
                }
            }
        }

    }

    override fun onClick(v: View?) {
        //
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}