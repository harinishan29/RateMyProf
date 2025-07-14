package com.saveetha.ratemyprof.api

import retrofit2.http.*
import retrofit2.Call


interface ApiService {
    @FormUrlEncoded
    @POST("LoginStudent.php")
    fun loginStudent(
        @Field("RegNo") regNo: String,
        @Field("Password") password: String
    ): Call<LoginResponse>

    @GET("TotalCounts.php")
    fun getTotalCounts(): Call<TotalCountsResponse>

}


