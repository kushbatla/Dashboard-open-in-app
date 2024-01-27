package com.kushbatla.dashboard.repository.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api : ListedAPI by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.inopenapp.com/api/v1/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ListedAPI::class.java)
    }
}