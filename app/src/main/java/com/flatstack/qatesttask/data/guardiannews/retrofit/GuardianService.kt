package com.flatstack.qatesttask.data.guardiannews.retrofit

import com.flatstack.qatesttask.data.guardiannews.model.GuardianResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GuardianService {
    @GET("/search")
    suspend fun getSectionNewsList(
        @Query("page") page: Int,
        @Query("section") section: String,
        @Query("lang") lang: String
    ): GuardianResponse
}