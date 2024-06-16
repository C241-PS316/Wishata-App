package com.capstone.wishata.ui.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.wishata.data.local.database.entity.Place
import com.capstone.wishata.databinding.FragmentWishlistBinding
import com.capstone.wishata.viewmodel.WishlistViewModel
import com.capstone.wishata.viewmodel.factory.ViewModelFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class WishlistFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.rvWishlist.layoutManager = GridLayoutManager(requireContext(), 2)

        val factory = ViewModelFactory.getInstance(requireContext())
        val wishlistViewModel: WishlistViewModel by viewModels<WishlistViewModel> { factory }

        wishlistViewModel.getAllFavPlace().observe(viewLifecycleOwner) { allFavPlace ->
            setAllFavPlace(allFavPlace)
        }

        return view
    }

    private fun setAllFavPlace(allFavPlace: List<Place>) {
        val adapter = WishlistAdapter()
        adapter.submitList(allFavPlace)
        binding.rvWishlist.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}