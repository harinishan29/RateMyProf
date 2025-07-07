 package com.saveetha.ratemyprof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme

class ProfessorViewReviews : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMyProfTheme {
                ProfessorReviewsScreen(
                    professorName = "Dr. Rachel",
                    reviews = sampleReviews,
                    onBackClick = { finish() }
                )
            }
        }
    }
}

// Reuse sample data
val sampleReviews = listOf(
    Review("2025-06-01", "RegNo: 12345", "Great teaching style and very motivating."),
    Review("2025-06-15", "RegNo: 67890", "Explains concepts clearly with good examples."),
    Review("2025-06-20", "RegNo: 11223", "Very respectful and approachable."),
    Review("2025-06-25", "RegNo: 44556", "Encourages students to think critically."),
)

@Composable
fun ProfessorReviewsScreen(
    professorName: String,
    reviews: List<Review>,
    onBackClick: () -> Unit
) {
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
                }
                Text(
                    text = "Student Reviews",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.Black)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Hello, $professorName!",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))


            reviews.forEach { review ->
                ReviewCard(review)
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfessorReviewsScreenPreview() {
    RateMyProfTheme {
        ProfessorReviewsScreen(
            professorName = "Dr. Rachel",
            reviews = sampleReviews,
            onBackClick = {}
        )
    }
}


