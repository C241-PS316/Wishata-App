package com.capstone.wishata.ui.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.wishata.R
import com.capstone.wishata.databinding.FragmentHomeBinding
import com.capstone.wishata.databinding.FragmentWishlistBinding
import com.capstone.wishata.databinding.FragmentWishlistBinding
import com.capstone.wishata.viewmodel.WishlistViewModel

class WishlistFragment : Fragment() {
    private val wishlistViewModel: WishlistViewModel by viewModels()

    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding

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
        binding.rvWishlist.apply {
            adapter = WishlistAdapter()
        }

        wishlistViewModel.getAllFavPlace().observe(viewLifecycleOwner, Observer {
            // do something
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

    }
}