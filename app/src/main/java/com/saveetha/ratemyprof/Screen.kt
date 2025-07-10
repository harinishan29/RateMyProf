package com.saveetha.ratemyprof

sealed class Screen(val route: String) {

    object InitialPage : Screen("InitialPage")

    // Login Screens
    object StudentLogin : Screen("StudentLoginScreen")
    object ProfessorLogin : Screen("ProfessorLoginScreen")
    object AdminLogin : Screen("AdminLoginScreen")
    object NewCollegeForm : Screen("NewCollegeForm")

    object FormSubmitted : Screen("FormSubmittedScreen")

    // After Login - Home/Dashboard Screens
    object StudentHome : Screen("StudentHomeScreen")

        object ProfessorList : Screen("ProfessorListScreen")

        object ProfessorRating : Screen("ProfessorRatingScreen/{name}/{title}/{rating}/{imageRes}") {
            fun passData(name: String, title: String, rating: Float, imageRes: Int): String {
                return "ProfessorRatingScreen/${name}/${title}/${rating}/${imageRes}"
            }
        }

    object Feedback : Screen("FeedbackUI")
    object FeedbackPosted : Screen("FeedbackPostedScreen")


    object ProfessorHome : Screen("ProfessorDashboardScreen")
    object AdminDashboard : Screen("AdminDashboardScreen")
}
