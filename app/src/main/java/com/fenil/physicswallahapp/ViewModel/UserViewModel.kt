package com.fenil.physicswallahapp.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.fenil.physicswallahapp.Repository.UserRepository
import com.fenil.physicswallahapp.api.models.entities.User
import com.fenil.physicswallahapp.api.models.responses.UserResponse
import com.fenil.physicswallahapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class UserViewModel(application: Application,val userRepository: UserRepository):AndroidViewModel(application) {
    val repository:UserRepository
    val allUser: MutableLiveData<Resource<UserResponse>> =
        MutableLiveData()
    init {
        repository= UserRepository()
        viewModelScope.launch {
            getUserData()
        }
    }

    suspend fun getUserData(){
        allUser.postValue(Resource.Loading())
        try {
            val response = userRepository.getUserData()
            allUser.postValue(handleAllRestauntResponse(response))
        }catch (t: Throwable) {
            Log.d("fenil", "getUserData: ${t.message}")
            when (t) {
                is IOException -> allUser.postValue(Resource.Error("Network Failure"))
                else -> allUser.postValue(Resource.Error("Conversion Error"))
            }
        }

    }

    private fun handleAllRestauntResponse(response: Response<UserResponse>):
            Resource<UserResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}