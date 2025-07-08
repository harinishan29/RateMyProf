package com.saveetha.ratemyprof

sealed class Screen(val route: String) {

    object InitialPage : Screen("InitialPage")

    // Login Screens
    object StudentLogin : Screen("StudentLoginScreen")
    object ProfessorLogin : Screen("ProfessorLoginScreen")
    object AdminLogin : Screen("AdminLoginScreen")

    // After Login - Home/Dashboard Screens
    object StudentHome : Screen("StudentHomeScreen")

        object ProfessorList : Screen("ProfessorListScreen")

    object ProfessorHome : Screen("ProfessorDashboardScreen")
    object AdminDashboard : Screen("AdminDashboardScreen")
}
