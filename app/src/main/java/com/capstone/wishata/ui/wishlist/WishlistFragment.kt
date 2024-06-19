package com.capstone.wishata.ui.wishlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.capstone.wishata.R
import com.capstone.wishata.databinding.FragmentWishlistBinding
import com.capstone.wishata.viewmodel.HomeViewModel
import com.capstone.wishata.viewmodel.WishlistViewModel
import com.capstone.wishata.viewmodel.factory.ViewModelFactory

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding

    private val wishlistViewModel by viewModels<WishlistViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWishlistBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_wishlist, R.id.navigation_profile)
        )

        val navHostFragment = NavHostFragment.findNavController(this@WishlistFragment)
        binding?.wishlistToolbar?.let { NavigationUI.setupWithNavController(it, navHostFragment, appBarConfiguration) }

        // recycler view setup
        binding?.rvWishlist?.apply {
            adapter = WishlistAdapter()
        }

        /*wishlistViewModel.getAllFavPlace().observe(viewLifecycleOwner) {
            // do something
        }*/
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {}
}