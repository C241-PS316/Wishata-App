package com.capstone.wishata.ui.authentication.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.capstone.wishata.R
import com.capstone.wishata.databinding.FragmentRegisterBinding
import com.capstone.wishata.utils.Result
import com.capstone.wishata.viewmodel.RegisterViewModel
import com.capstone.wishata.viewmodel.factory.ViewModelFactory

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.registerButton.setOnClickListener {
            Log.d(TAG, "Username: $userName\n, email: $email\n, password: $password\n, confirm: $confirmPassword\n")
            showMessage("Username: $userName\n, email: $email\n, password: $password\n, confirm: $confirmPassword\n")
            register(userName, email, password, confirmPassword)
        }*/

        setupComponent()

    }

    private fun setupComponent() {
        val userName = binding.nameEditText.text
        val email = binding.emailEditText.text
        val password = binding.passwordEditText.text
        val confirmPassword = binding.passwordConfirmEditText.text

        binding.registerButton.setOnClickListener {
            register(userName.toString(), email.toString(), password.toString(), confirmPassword.toString())
            Log.d(TAG, "Username: $userName\n, email: $email\n, password: $password\n, confirm: $confirmPassword\n")

        }

    }

    private fun register(userName: String, email: String, password: String, confirmPassword: String) {
        registerViewModel.register(userName, email, password, confirmPassword).observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when(result) {
                    Result.Loading -> {
                        binding.progressIndicator.visibility = View.VISIBLE
                    }

                    is Result.Error -> {
                        binding.progressIndicator.visibility = View.GONE
                        showMessage(result.error)

                    }
                    is Result.Success -> {
                        binding.progressIndicator.visibility = View.GONE
                        result.data.message?.let { showMessage(it) }
                        parentFragmentManager.beginTransaction().remove(this).commit()
                    }
                }
            }
        }
    }

    // avoid memory leak
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun showMessage(string: String) {
        Toast.makeText(requireActivity(), string, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "REGISTER ==="
    }
}