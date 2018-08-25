package com.prince.musicapp.network

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("search?limit=4")
    fun getSongs(@Query("term") term: String): Single<ResponseBody>
}