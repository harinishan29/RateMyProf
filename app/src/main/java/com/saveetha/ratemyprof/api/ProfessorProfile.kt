package com.saveetha.ratemyprof.api

data class ProfessorProfile(
    val ProfID: String,
    val FirstName: String,
    val LastName: String,
    val Title: String,
    val University: String,
    val Dept: String,
    val Email: String,
    val PhnNo: String,
    val Username: String,
    val Password: String,
    val ProfilePic: String,
    val Bio: String
)
