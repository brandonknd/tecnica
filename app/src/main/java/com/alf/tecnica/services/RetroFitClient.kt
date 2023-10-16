package com.alf.tecnica.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroFitClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL="https://mocki.io/"

    private val logger: OkHttpClient
        get(){
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level =
                         HttpLoggingInterceptor.Level.BODY
                    })
            //if (buildConfig) httpClient.interceptors().add(logging)
            return httpClient.build()
        }

    val getClient: Retrofit?
        get(){
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(logger)
                    .build()
            }
            return retrofit
        }
}