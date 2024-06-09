package com.capstone.wishata.viewmodel

import androidx.lifecycle.ViewModel
import com.capstone.wishata.repository.WishataRepository

class RegisterViewModel(private val repository: WishataRepository): ViewModel() {

    fun register(userName: String, email: String, password: String, confirmPassword: String) = repository.register(userName, email, password, confirmPassword)
}