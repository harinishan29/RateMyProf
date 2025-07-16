package com.saveetha.ratemyprof.api

data class ProfessorListResponse(
    val Status: Boolean,
    val Message: String,
    val ProfileData: List<ProfessorProfile>
)
