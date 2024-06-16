package com.capstone.wishata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.wishata.repository.WishataRepository

class LoginViewModel(
    private val repository: WishataRepository
): ViewModel() {
    fun login(username: String, password: String) = repository.login(username, password)
}