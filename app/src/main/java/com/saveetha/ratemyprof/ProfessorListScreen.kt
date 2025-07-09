package com.saveetha.ratemyprof

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import android.net.Uri
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme

data class ProfessorData(val name: String, val title: String, val rating: Float, val imageRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessorListScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val professors = listOf(
        ProfessorData("Mrs. Phoebe", "Assistant Professor", 4f, R.drawable.prof1),
        ProfessorData("Mr. Ross", "Assistant Professor", 3.5f, R.drawable.prof1),
        ProfessorData("Mrs. Monica", "Associate Professor", 2.5f, R.drawable.prof1),
        ProfessorData("Mr. Joey", "Assistant Professor", 2f, R.drawable.prof1)
    )

    val filteredProfessors = professors.filter {
        it.name.contains(searchQuery.text, ignoreCase = true)
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

        filteredProfessors.forEach {
            ProfessorReviewCard(
                name = it.name,
                title = it.title,
                rating = it.rating,
                imageRes = it.imageRes,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable {
                        navController.navigate(
                            Screen.ProfessorRating.passData(
                                Uri.encode(it.name),
                                Uri.encode(it.title),
                                it.rating,
                                it.imageRes
                            )
                        )
                    }
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfessorListScreenPreview() {
    RateMyProfTheme {
        ProfessorListScreen(navController = rememberNavController())
    }
}
