package com.digitalgenius.api.services

import com.fenil.physicswallahapp.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserAPI {
    @GET("data/users")
    suspend fun getAllUserData():Response<UserResponse>


}