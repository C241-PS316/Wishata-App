package com.capstone.wishata.viewmodel

import com.capstone.wishata.repository.WishataRepository

class RegisterViewModel(private val repository: WishataRepository) {

    fun register() = repository.register()
}