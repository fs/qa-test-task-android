package com.flatstack.qatesttask.repository

import com.flatstack.qatesttask.data.guardiannews.model.GuardianInfo
import com.flatstack.qatesttask.data.guardiannews.model.Language

interface NewsRepository {
    suspend fun getNewsListPageInfo(
        page: Int,
        section: String,
        lang: Language
    ): GuardianInfo
}
