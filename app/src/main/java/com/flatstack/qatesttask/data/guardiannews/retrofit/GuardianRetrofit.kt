package com.flatstack.qatesttask.data.guardiannews.retrofit

import com.flatstack.qatesttask.data.guardiannews.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface GuardianRetrofit {

    @GET("/search")
    suspend fun getSectionNewsList(
        @Query("page") page: Int,
        @Query("section") section: String,
        @Query("lang") lang: String
    ): GuardianResponse

    @GET("/sections")
    suspend fun getSections(): CategoryResponseBase
}
