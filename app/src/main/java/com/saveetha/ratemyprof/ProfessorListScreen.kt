package com.saveetha.ratemyprof

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.saveetha.ratemyprof.api.ProfessorListResponse
import com.saveetha.ratemyprof.api.ProfessorProfile
import com.saveetha.ratemyprof.api.RetrofitClient
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessorListScreen(navController: NavHostController, university: String, department: String) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var professors by remember { mutableStateOf<List<ProfessorProfile>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Fetch from API
    LaunchedEffect(university, department) {
        RetrofitClient.instance.getProfessorsByUniversityAndDept(university, department)
            .enqueue(object : Callback<ProfessorListResponse> {
                override fun onResponse(
                    call: Call<ProfessorListResponse>,
                    response: Response<ProfessorListResponse>
                ) {
                    if (response.isSuccessful && response.body()?.Status == true) {
                        professors = response.body()?.ProfileData ?: emptyList()
                    } else {
                        errorMessage = response.body()?.Message ?: "Failed to load data."
                    }
                    isLoading = false
                }

                override fun onFailure(call: Call<ProfessorListResponse>, t: Throwable) {
                    errorMessage = t.message
                    isLoading = false
                }
            })
    }

    val filteredProfessors = professors.filter {
        val fullName = "${it.FirstName} ${it.LastName}"
        fullName.contains(searchQuery.text, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        TopAppBar(
            title = { Text("RateMyProf") },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.Person, contentDescription = "Profile")
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Professor") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        when {
            isLoading -> {
                Text("Loading...", modifier = Modifier.padding(16.dp))
            }
            errorMessage != null -> {
                Text("Error: $errorMessage", modifier = Modifier.padding(16.dp))
            }
            filteredProfessors.isEmpty() -> {
                Text("No professors found.", modifier = Modifier.padding(16.dp))
            }
            else -> {
                filteredProfessors.forEach { prof ->
                    val fullName = "${prof.FirstName} ${prof.LastName}"

                    ProfessorReviewCard(
                        name = fullName,
                        title = prof.Title,
                        rating = 4.0f, // Placeholder
                        imageRes = R.drawable.prof1, // Replace with Coil if needed
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                navController.navigate(
                                    Screen.ProfessorRating.passData(
                                        prof.ProfID, // new field
                                        fullName,
                                        prof.Title,
                                        4.0f, // Placeholder rating
                                        R.drawable.prof1,
                                        university
                                    )
                                )

                            }
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfessorListScreenPreview() {
    RateMyProfTheme {
        ProfessorListScreen(
            navController = rememberNavController(),
            university = "SCLAS",
            department = "MCA"
        )
    }
}
