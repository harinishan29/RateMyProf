package com.saveetha.ratemyprof.api

data class TotalCountsResponse(
    val Status: Boolean,
    val Message: String,
    val Counts: Counts
)

data class Counts(
    val TotalRatings: String,
    val TotalStudents: String,
    val TotalProfessors: String
)
