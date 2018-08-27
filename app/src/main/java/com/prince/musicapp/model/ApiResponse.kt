package com.prince.musicapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiResponse {

    @SerializedName("resultCount")
    @Expose
    private var resultCount: Int = 0
    @SerializedName("results")
    @Expose
    private var results: List<Result>? = null

    fun getResultCount(): Int {
        return resultCount
    }

    fun setResultCount(resultCount: Int) {
        this.resultCount = resultCount
    }

    fun getResults(): List<Result>? {
        return results
    }

    fun setResults(results: List<Result>) {
        this.results = results
    }
}