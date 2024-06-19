package com.capstone.wishata.viewmodel

import androidx.lifecycle.ViewModel
import com.capstone.wishata.repository.WishataRepository

class HomeViewModel(private val repository: WishataRepository) : ViewModel() {

    fun getWisata() = repository.getWisata()
}