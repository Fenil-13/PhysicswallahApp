package com.fenil.physicswallahapp.ViewModel

import android.app.Application
import com.fenil.physicswallahapp.Repository.UserRepository
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.fenil.physicswallahapp.ViewModel.UserViewModel

class UserViewModelProviderFactory(
    private val app: Application,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(aClass: Class<T>): T {
        return UserViewModel(app, userRepository) as T
    }
}