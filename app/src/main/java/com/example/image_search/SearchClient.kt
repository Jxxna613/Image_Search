package com.example.image_search

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object SearchClient {

    private const val SEARCH_BASE_URL = "https://dapi.kakao.com/v2/search/"

    private fun creatOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    private val ImageRetrofit = Retrofit.Builder()
        .baseUrl(SEARCH_BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(
            creatOkHttpClient()
        ).build()
}
