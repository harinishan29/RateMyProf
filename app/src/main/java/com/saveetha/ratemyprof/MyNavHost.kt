package com.saveetha.ratemyprof

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.InitialPage.route) {
        composable(Screen.InitialPage.route) {
            InitialPage(
                onStudentClick = { navController.navigate(Screen.StudentLogin.route) },
                onProfessorClick = { navController.navigate(Screen.ProfessorLogin.route) },
                onAdminClick = { navController.navigate(Screen.AdminLogin.route) }
            )
        }


        composable(Screen.StudentLogin.route) {
            StudentLoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.StudentHome.route) {
                        popUpTo(Screen.StudentLogin.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.StudentHome.route) {
            StudentDashboardScreenNav(
                onClickStartRate = {
                    navController.navigate(Screen.ProfessorList.route)
                }
            )
        }

        composable(Screen.ProfessorList.route) {
            ProfessorListScreen(navController)
        }

        composable(
            route = Screen.ProfessorRating.route,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("rating") { type = NavType.FloatType },
                navArgument("imageRes") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val rating = backStackEntry.arguments?.getFloat("rating") ?: 0f
            val imageRes = backStackEntry.arguments?.getInt("imageRes") ?: R.drawable.prof1

            val professor = ProfessorData(name, title, rating, imageRes)

            ProfessorRatingScreen2(professor)
        }

        // Professor Flow
        composable(Screen.ProfessorLogin.route) {
            ProfessorLoginScreen(
                onLoginSuccess = { navController.navigate(Screen.ProfessorHome.route) }
            )
        }
        composable(Screen.ProfessorHome.route) {
            ProfessorDashboard()
        }

        // Admin Flow
        composable(Screen.AdminLogin.route) {
            AdminLoginScreen(
                onLoginSuccess = { navController.navigate(Screen.AdminDashboard.route) }
            )
        }

    }
}

