package com.flatstack.qatesttask.repository

import com.flatstack.qatesttask.data.guardiannews.model.GuardianInfo
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.data.guardiannews.retrofit.GuardianService

class NewsRepositoryImpl(private val service: GuardianService) : NewsRepository {
    override suspend fun getNewsListPageInfo(
        page: Int,
        section: String,
        lang: Language
    ): GuardianInfo =
        service.getSectionNewsList(page, section, lang.langName).info
}
