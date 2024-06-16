package com.capstone.wishata.ui.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.wishata.R
import com.capstone.wishata.databinding.FragmentWishlistBinding
import com.capstone.wishata.viewmodel.WishlistViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class WishlistFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentWishlistBinding? = null
    private val binding get() = _binding!!
    private val wishlistViewModel: WishlistViewModel by viewModels()

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
    ): View? {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.rvWishlist.apply {
            adapter = WishlistAdapter()
        }

        wishlistViewModel.getAllFavPlace().observe(viewLifecycleOwner, Observer {
            // do something
        })

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WishlistFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}