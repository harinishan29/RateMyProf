package com.saveetha.ratemyprof.api

import com.saveetha.ratemyprof.api.ProfessorListResponse
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

    @FormUrlEncoded
    @POST("StudentRatingList.php")
    fun getProfessorsByUniversityAndDept(
        @Field("university") university: String,
        @Field("dept") dept: String
    ): Call<ProfessorListResponse>


    @GET("RatingPage1.php")
    fun submitProfessorRating(
        @Query("ProfID") profID: String,
        @Query("TeachingStyle") teachingStyle: Int,
        @Query("Encouraging") encouraging: Int,
        @Query("UseOfTechnology") useOfTechnology: Int,
        @Query("RespectForStudents") respectForStudents: Int,
        @Query("TeachingStyleOption") teachingStyleOption: String,
        @Query("EncouragingOption") encouragingOption: String,
        @Query("UseOfTechnologyOption") useOfTechnologyOption: String,
        @Query("RespectForStudentsOption") respectForStudentsOption: String,
        @Query("RegNo") regNo: String,
        @Query("University") university: String,
        @Query("Feedback") feedback: String
    ): Call<RatingResponse>


}


