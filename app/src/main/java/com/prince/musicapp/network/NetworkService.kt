package com.prince.musicapp.network

import com.prince.musicapp.model.ApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("search")
    fun getSongs(@Query("term") term: String): Observable<ApiResponse>
}