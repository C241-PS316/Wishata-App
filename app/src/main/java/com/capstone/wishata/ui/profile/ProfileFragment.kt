package com.capstone.wishata.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.capstone.wishata.R
import com.capstone.wishata.databinding.FragmentHomeBinding
import com.capstone.wishata.databinding.FragmentProfileBinding
import com.capstone.wishata.ui.authentication.WelcomeActivity


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_wishlist, R.id.navigation_profile)
        )

        val navHostFragment = NavHostFragment.findNavController(this@ProfileFragment)
        binding?.profileToolbar?.let { NavigationUI.setupWithNavController(it, navHostFragment, appBarConfiguration) }


        binding?.buttonLogout?.setOnClickListener {
            val logOutIntent = Intent(requireActivity(), WelcomeActivity::class.java)
            logOutIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(logOutIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

    }
}