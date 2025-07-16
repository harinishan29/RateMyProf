package com.saveetha.ratemyprof

import android.net.Uri

sealed class Screen(val route: String) {

    object InitialPage : Screen("InitialPage")

    // Login Screens
    object StudentLogin : Screen("StudentLoginScreen")
    object ProfessorLogin : Screen("ProfessorLoginScreen")
    object AdminLogin : Screen("AdminLoginScreen")
    object NewCollegeForm : Screen("NewCollegeForm")

    object FormSubmitted : Screen("FormSubmittedScreen")

    // After Login - Home/Dashboard Screens123
    object StudentHome : Screen("StudentHomeScreen")

    object ProfessorList : Screen("ProfessorListScreen")

    object ProfessorRating : Screen("ProfessorRatingScreen/{profId}/{name}/{title}/{rating}/{imageRes}/{university}/") {
        fun passData(profId: String, name: String, title: String, rating: Float, imageRes: Int,university: String): String {
            return "ProfessorRatingScreen/$profId/${Uri.encode(name)}/${Uri.encode(title)}/$rating/$imageRes/${Uri.encode(university)}/"
        }
    }


    object ProfessorViewRating : Screen("ProfessorViewRating")
    object ProfessorViewReviews : Screen("ProfessorViewReviews")


    object Feedback : Screen("FeedbackUI")
    object FeedbackPosted : Screen("FeedbackPostedScreen")


    object ProfessorHome : Screen("ProfessorDashboardScreen")
    object AdminDashboard : Screen("AdminHomeScreen")
    object CreateProfessor : Screen("CreateProfessorScreen")
    object CreateStudent : Screen("CreateStudentScreen")

    object SelectDepartmentUniversity : Screen("SelectUniversityDepartmentScreen")


}
