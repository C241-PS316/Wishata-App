package com.capstone.wishata.ui.authentication.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.wishata.data.network.response.LoginResponse
import com.capstone.wishata.databinding.ActivityLoginBinding
import com.capstone.wishata.ui.main.MainActivity
import com.capstone.wishata.viewmodel.LoginViewModel
import com.capstone.wishata.viewmodel.factory.ViewModelFactory
import com.capstone.wishata.utils.Status
import com.capstone.wishata.utils.showToast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val factory = ViewModelFactory.getInstance(this)
        val loginViewModel: LoginViewModel by viewModels<LoginViewModel> { factory}

        binding.loginButton.setOnClickListener {
            val username = binding.nameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            loginViewModel.login(username, password).observe(this) {
                if (it != null) {
                    when(it) {
                        is Status.Loading -> {}
                        is Status.Success -> {
                            processLogin(username)
                        }
                        is Status.Error -> {
                            showToast(it.error, this)
                        }
                    }
                }
            }
        }
    }

    private fun processLogin(username: String) {
        val toMainIntent = Intent(this@LoginActivity, MainActivity::class.java)
        toMainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(toMainIntent)
    }
}