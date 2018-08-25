package com.prince.musicapp.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object NetworkClient {
    private const val BASE_PATH = "https://itunes.apple.com/"

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_PATH)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getNetworkService(): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }
}