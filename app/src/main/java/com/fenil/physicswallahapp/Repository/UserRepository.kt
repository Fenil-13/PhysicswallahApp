package com.fenil.physicswallahapp.Repository

import android.util.Log
import com.digitalgenius.api.UserClient
import com.fenil.physicswallahapp.api.models.responses.UserResponse
import retrofit2.Response

class UserRepository {
    suspend fun getUserData(): Response<UserResponse> {
        val response=UserClient.publicApi.getAllUserData();
        Log.d("fenil", "getUserData: ${response.body()!!.size}")
        return response;
    }
}