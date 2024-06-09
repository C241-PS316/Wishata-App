package com.capstone.wishata.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import com.capstone.wishata.R
import com.capstone.wishata.databinding.FragmentWelcomeBinding
import com.capstone.wishata.ui.authentication.register.RegisterFragment

class WelcomeFragment : Fragment() {

    private var binding: FragmentWelcomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerFragment = RegisterFragment()

        binding?.apply {
            registerButton.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.welcome_main_container, registerFragment, RegisterFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()
            }
        }

    }

}