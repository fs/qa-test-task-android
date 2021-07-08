package com.flatstack.qatesttask.di

import com.flatstack.qatesttask.BuildConfig.API_KEY
import com.flatstack.qatesttask.data.guardiannews.retrofit.GuardianHttpService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

val guardianModule = module {
    single {
        GuardianHttpService(API_KEY)
    }
    single {
        get<GuardianHttpService>().getService(
            get<GuardianHttpService>().getBaseRetrofit(
                get(named("guardianOkHttpClient")))
        )
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
