package com.capstone.wishata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.wishata.repository.WishataRepository

class WishlistViewModel(
    private val repository: WishataRepository
): ViewModel() {

    fun getAllFavPlace() = repository.getAllFavPlace()

}