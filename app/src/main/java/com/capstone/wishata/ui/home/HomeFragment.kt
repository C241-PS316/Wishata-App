package com.capstone.wishata.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.wishata.adapter.HomeWisataAdapter
import com.capstone.wishata.databinding.FragmentHomeBinding
import com.capstone.wishata.utils.Result
import com.capstone.wishata.viewmodel.HomeViewModel
import com.capstone.wishata.viewmodel.factory.ViewModelFactory


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchWisata()
    }

    private fun fetchWisata() {
        val wisataAdapter = HomeWisataAdapter()

        /*binding?.rvWisata?.layoutManager = LinearLayoutManager(requireContext())

        binding?.rvWisata?.adapter = wisataAdapter*/

        /*homeViewModel.getWisata().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Success<*> -> {
                        wisataAdapter.setData(result.data.)
                        showToast("SUCCESS")
                    }

                    is Result.Error -> {
                        showToast("ERROR")
                    }

                    listOf(Result.Loading) -> {
                        showToast("Loading Mulai")
                    }
                }
            }
        }*/


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "HOME__FRAGMENT"
    }

}