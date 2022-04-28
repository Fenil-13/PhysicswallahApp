package com.digitalgenius.api

import com.digitalgenius.api.services.UserAPI
import com.fenil.physicswallahapp.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UserClient {
    val retrofitBuilder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    val publicApi = retrofitBuilder.build().create(UserAPI::class.java)


}