package com.capstone.wishata.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.wishata.injection.Injection
import com.capstone.wishata.repository.WishataRepository
import com.capstone.wishata.viewmodel.RegisterViewModel

class ViewModelFactory(private val repository: WishataRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }


            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        fun getInstance(context: Context): ViewModelFactory = ViewModelFactory(Injection.provideStoryRepository(context))
    }
}