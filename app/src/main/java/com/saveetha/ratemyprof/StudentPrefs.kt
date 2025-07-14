package com.saveetha.ratemyprof

import android.content.Context
import android.content.SharedPreferences

class StudentPrefs(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("student_prefs", Context.MODE_PRIVATE)

    fun saveStudentData(
        id: Int,
        firstName: String,
        lastName: String,
        regNo: Int,
        university: String,
        dept: String,
        email: String,
        phnNo: String,
        username: String
    ) {
        prefs.edit().apply {
            putInt("StudID", id)
            putString("FirstName", firstName)
            putString("LastName", lastName)
            putInt("RegNo", regNo)
            putString("University", university)
            putString("Dept", dept)
            putString("Email", email)
            putString("PhnNo", phnNo)
            putString("Username", username)
            apply()
        }
    }

    fun getStudentData(): Map<String, String?> {
        return mapOf(
            "StudID" to prefs.getInt("StudID", -1).toString(),
            "FirstName" to prefs.getString("FirstName", ""),
            "LastName" to prefs.getString("LastName", ""),
            "RegNo" to prefs.getInt("RegNo", 0).toString(),
            "University" to prefs.getString("University", ""),
            "Dept" to prefs.getString("Dept", ""),
            "Email" to prefs.getString("Email", ""),
            "PhnNo" to prefs.getString("PhnNo", ""),
            "Username" to prefs.getString("Username", "")
        )
    }

    fun clearData() {
        prefs.edit().clear().apply()
    }
}
