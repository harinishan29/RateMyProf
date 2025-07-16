package com.saveetha.ratemyprof.api

data class RatingResponse(
    val Status: Boolean,
    val Message: String,
    val RatingID: Int? = null
)
