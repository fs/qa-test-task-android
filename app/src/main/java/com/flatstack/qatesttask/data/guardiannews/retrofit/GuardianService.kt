package com.flatstack.qatesttask.data.guardiannews.retrofit

import com.flatstack.qatesttask.data.guardiannews.model.GuardianResponse
import com.flatstack.qatesttask.repository.NewsRepository
import retrofit2.http.GET
import retrofit2.http.Query

interface GuardianService : NewsRepository {
    @GET("/search")
    override suspend fun getSectionNewsList(
        @Query("page") page: Int,
        @Query("section") section: String,
        @Query("lang") lang: String
    ): GuardianResponse
}
