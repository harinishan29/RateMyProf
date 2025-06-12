package com.saveetha.ratemyprof

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessorListScreen() {
    val professors = listOf(
        Professor("Mrs. Phoebe", "Assistant Professor", 4f, R.drawable.prof1),
        Professor("Mr. Ross", "Assistant Professor", 3.5f, R.drawable.prof1),
        Professor("Mrs. Monica", "Associate Professor", 2.5f, R.drawable.prof1),
        Professor("Mr. Joey", "Assistant Professor", 2f, R.drawable.prof1)
    )

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

        professors.forEach {
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
