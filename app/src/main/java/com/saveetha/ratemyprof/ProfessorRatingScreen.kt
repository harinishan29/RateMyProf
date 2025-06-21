package com.saveetha.ratemyprof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme

class ProfessorRatingScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMyProfTheme {
                Surface {
                    ProfessorRatingScreen2()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessorRatingScreen2() {
    val scrollState = rememberScrollState()
    val ratingCategories = listOf(
        "Teaching Style",
        "Encouraging",
        "Use of Technology",
        "Respect for Students"
    )

    val ratings = remember { mutableStateListOf(0, 0, 0, 0) }
    val options = listOf(
        listOf("Boring", "Monotonous", "Poor explanations", "Hard to follow"),
        listOf("No support", "Unapproachable", "Discouraging", "Silent"),
        listOf("No tech used", "Old methods", "Not interactive", "Minimal tools"),
        listOf("Rude", "Unfair", "Doesn’t listen", "Disrespectful")
    )

    val selectedOptions = remember { mutableStateListOf("", "", "", "") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "RateMyProf",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF4CAF50)
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            Image(
                painter = painterResource(id = R.drawable.prof1),
                contentDescription = "Professor",
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text("Mrs. Phoebe", fontWeight = FontWeight.Bold, fontSize = 25.sp)

            Spacer(modifier = Modifier.height(35.dp))

            // Ratings
            ratingCategories.forEachIndexed { index, category ->
                Text(
                    text = category,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )

                StarRatingBar(selectedStars = ratings[index]) {
                    ratings[index] = it
                }

                options[index].forEach { option ->
                    val isSelected = selectedOptions[index] == option

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isSelected) Color(0xFF4CAF50) else Color.Transparent)
                            .border(
                                width = 1.dp,
                                color = if (isSelected) Color(0xFF4CAF50) else Color.Gray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                selectedOptions[index] = option
                            }
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                    ) {
                        Text(
                            text = option,
                            color = if (isSelected) Color.White else Color.Black,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))
            }

            // Submit button
            Button(
                onClick = { /* Submit Action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Submit", color = Color.White,fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun StarRatingBar(
    selectedStars: Int,
    maxStars: Int = 5,
    onRatingSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..maxStars) {
            IconButton(onClick = { onRatingSelected(i) }) {
                Icon(
                    painter = painterResource(
                        id = if (i <= selectedStars)
                            R.drawable.filledstar
                        else
                            R.drawable.unfilledstar
                    ),
                    contentDescription = "Star $i",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfessorRatingScreenPreview() {
    RateMyProfTheme {
        ProfessorRatingScreen()
    }
}
