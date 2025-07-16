package com.saveetha.ratemyprof

import FormSubmittedScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import android.net.Uri
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.saveetha.ratemyprof.models.ProfessorData
import com.saveetha.ratemyprof.models.SelectionData
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString


@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.InitialPage.route) {
        composable(Screen.InitialPage.route) {
            InitialPage(
                onStudentClick = { navController.navigate(Screen.StudentLogin.route) },
                onProfessorClick = { navController.navigate(Screen.ProfessorLogin.route) },
                onAdminClick = { navController.navigate(Screen.AdminLogin.route) }
            ) { navController.navigate(Screen.NewCollegeForm.route) }
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
                    navController.navigate(Screen.SelectDepartmentUniversity.route)
                }
            )
        }

        val selectionKey = "selectionData"
        composable(Screen.SelectDepartmentUniversity.route) {
            SelectUniversityDepartmentScreen { selection ->
                val json = Json.encodeToString(selection)
                navController.currentBackStackEntry?.savedStateHandle?.set("selectionData", json)
                navController.navigate(Screen.ProfessorList.route)
            }
        }

        composable(Screen.ProfessorList.route) {
            val selectionKey = "selectionData"
            val json = navController.previousBackStackEntry
                ?.savedStateHandle?.get<String>(selectionKey)

            val selection = json?.let { Json.decodeFromString<SelectionData>(it) }

            if (selection != null) {
                ProfessorListScreen(
                    university = selection.university,
                    department = selection.department,
                    navController = navController
                )
            } else {
                Text("Missing selection data.")
            }
        }


        composable(
            route = Screen.ProfessorRating.route,
            arguments = listOf(
                navArgument("profId") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("rating") { type = NavType.FloatType },
                navArgument("imageRes") { type = NavType.IntType },
                navArgument("university") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val profId = backStackEntry.arguments?.getString("profId") ?: ""
            val name = Uri.decode(backStackEntry.arguments?.getString("name") ?: "")
            val title = Uri.decode(backStackEntry.arguments?.getString("title") ?: "")
            val rating = backStackEntry.arguments?.getFloat("rating") ?: 0f
            val imageRes = backStackEntry.arguments?.getInt("imageRes") ?: R.drawable.prof1
            val university = backStackEntry.arguments?.getString("university") ?: ""

            val professor = ProfessorData(name, title, rating, imageRes, university)

            ProfessorRatingScreen2(professor = professor, profId = profId)
        }


        composable(Screen.Feedback.route) {
            FeedbackUI(navController)
        }

        composable(Screen.FeedbackPosted.route) {
            FeedbackPostedScreen(
                onGoHomeClicked = {
                    navController.navigate(Screen.ProfessorHome.route) {
                        popUpTo(Screen.FeedbackPosted.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.NewCollegeForm.route) {
            NewCollegeForm(onFormSubmitted = { navController.navigate(Screen.FormSubmitted.route) })
        }

        composable(Screen.FormSubmitted.route) {
            FormSubmittedScreen(onGoHomeClicked = {
                navController.navigate(Screen.InitialPage.route) {
                    popUpTo(Screen.FormSubmitted.route) { inclusive = true }
                }
            })
        }



        composable(Screen.ProfessorLogin.route) {
            ProfessorLoginScreen(
                onLoginSuccess = { navController.navigate(Screen.ProfessorHome.route) }
            )
        }
        composable(Screen.ProfessorHome.route) {
            ProfessorDashboard(navController)
        }


        composable(Screen.ProfessorViewRating.route) {
            ProfessorRatingsScreen(
                professorName = "Dr. Rachel", // You can pass this dynamically later if needed
                averageRating = 4.6f,
                ratingPercentages = mapOf(
                    5 to 60,
                    4 to 25,
                    3 to 10,
                    2 to 3,
                    1 to 2
                ),
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.ProfessorViewReviews.route) {
            ProfessorReviewsScreen(
                professorName = "Dr. Rachel",
                reviews = sampleReviews,
                onBackClick = { navController.popBackStack() }
            )
        }


        // Admin Flow
        composable(Screen.AdminLogin.route) {
            AdminLoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.AdminDashboard.route) {
                        popUpTo(Screen.AdminLogin.route) { inclusive = true }
                    }
                }
            )
        }

        composable (Screen.AdminDashboard.route){
            AdminHomeScreen(navController)
        }

        composable(Screen.CreateProfessor.route) {
            CreateProfessorScreen()
        }

        composable(Screen.CreateStudent.route) {
            CreateStudentScreen()
        }


    }
}

