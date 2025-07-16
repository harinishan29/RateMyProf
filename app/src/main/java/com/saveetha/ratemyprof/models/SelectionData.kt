package com.saveetha.ratemyprof.models

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class SelectionData(
    val university: String,
    val department: String
)
