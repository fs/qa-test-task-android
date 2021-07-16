package com.flatstack.qatesttask.di

import com.flatstack.qatesttask.BuildConfig.API_KEY
import com.flatstack.qatesttask.data.guardiannews.retrofit.GuardianHttpService
import com.flatstack.qatesttask.repository.NewsRepository
import com.flatstack.qatesttask.repository.NewsRepositoryImpl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.android.AndroidSocketAdapter.Companion.factory
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

val guardianModule = module {
    single {
        GuardianHttpService(API_KEY)
    }
    single {
        get<GuardianHttpService>().run {
            getService(
                getBaseRetrofit(get(named("guardianOkHttpClient")))
            )
        }
    }
    single {
        NewsRepositoryImpl(get()) as NewsRepository
    }
    factory(named("thumbnailInterceptor")) {
        get<GuardianHttpService>().getThumbnailInterceptor()
    }
    factory(named("pageSizeInterceptor")) {
        get<GuardianHttpService>().getPageSizeInterceptor()
    }
    factory(named("formatInterceptor")) {
        get<GuardianHttpService>().getFormatInterceptor()
    }
    factory(named("bearerAuthorizationInterceptor")) {
        get<GuardianHttpService>().getBearerAuthorizationInterceptor()
    }
    factory(named("loggingInterceptor")) {
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
    }
    factory(named("guardianOkHttpClient")) {
        get<GuardianHttpService>().getClient(
            listOf(
                get<HttpLoggingInterceptor>(named("loggingInterceptor")),
                get(named("pageSizeInterceptor")),
                get(named("formatInterceptor")),
                get(named("bearerAuthorizationInterceptor")),
                get(named("thumbnailInterceptor"))
            )
        )
    }
}
