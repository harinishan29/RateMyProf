package com.saveetha.ratemyprof.api

data class LoginResponse(
    val Status: Boolean,
    val Message: String,
    val Data: List<StudentData>
)

data class StudentData(
    val StudID: Int,
    val FirstName: String,
    val LastName: String,
    val RegNo: Int,
    val University: String,
    val Dept: String,
    val Email: String,
    val PhnNo: String,
    val Username: String,
    val isActive: Int
)