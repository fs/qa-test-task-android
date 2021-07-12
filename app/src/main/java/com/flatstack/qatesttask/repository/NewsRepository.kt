package com.flatstack.qatesttask.repository

import com.flatstack.qatesttask.data.guardiannews.model.GuardianResponse

interface NewsRepository {
    suspend fun getSectionNewsList(
        page: Int,
        section: String,
        lang: String
    ): GuardianResponse
}
