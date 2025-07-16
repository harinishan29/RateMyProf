package com.saveetha.ratemyprof.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    
    private const val BASE_URL = "https://ttcjtx8n-80.inc1.devtunnels.ms/ratemyprof/API/" // Use actual IP if testing on a device

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
