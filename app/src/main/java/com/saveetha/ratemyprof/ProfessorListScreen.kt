package com.saveetha.ratemyprof

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme

data class Professor(val name: String, val title: String, val rating: Float, val imageRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessorListScreen() {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val professors = listOf(
        Professor("Mrs. Phoebe", "Assistant Professor", 4f, R.drawable.prof1),
        Professor("Mr. Ross", "Assistant Professor", 3.5f, R.drawable.prof1),
        Professor("Mrs. Monica", "Associate Professor", 2.5f, R.drawable.prof1),
        Professor("Mr. Joey", "Assistant Professor", 2f, R.drawable.prof1)
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
                imageRes = it.imageRes
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
        ProfessorListScreen()
    }
}
