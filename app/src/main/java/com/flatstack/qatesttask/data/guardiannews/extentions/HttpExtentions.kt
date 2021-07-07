package com.flatstack.qatesttask.data.guardiannews.extentions

import okhttp3.Interceptor
import okhttp3.Response

fun Interceptor.Chain.addQueriesToInterceptor(vararg queryPairs: Pair<String, String>): Response {
    val requestBuilder = request().url.newBuilder()
    queryPairs.forEach {
        requestBuilder.addQueryParameter(it.first, it.second)
    }
    return requestBuilder.build().let { url ->
        this.proceed(request().newBuilder().url(url).build())
    }
}
