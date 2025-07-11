package com.saveetha.ratemyprof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme

class ProfessorRatingsScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMyProfTheme {
                ProfessorRatingsScreen(
                    professorName = "Dr. Rachel",
                    averageRating = 4.6f,
                    ratingPercentages = mapOf(
                        5 to 60,
                        4 to 25,
                        3 to 10,
                        2 to 3,
                        1 to 2
                    ),
                    onBackClick = { finish() }
                )
            }
        }
    }
}

@Composable
fun ProfessorRatingsScreen(
    professorName: String,
    averageRating: Float,
    ratingPercentages: Map<Int, Int>,
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
                    text = "Ratings Overview",
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

            // Overall Rating Card
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFCCE5B1)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Overall Rating", fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "★ $averageRating / 5",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4CAF50)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Star-wise Breakdown
            Column {
                ratingPercentages.toSortedMap(reverseOrder()).forEach { (stars, percent) ->
                    RatingBreakdownBar(stars = stars, percentage = percent)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.height(40.dp)) // bottom space for scroll comfort
        }
    }
}

@Composable
fun RatingBreakdownBar(stars: Int, percentage: Int) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$stars ★",
                modifier = Modifier.width(60.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            LinearProgressIndicator(
                progress = percentage / 100f,
                color = Color(0xFF8BC34A),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }
        Text(
            text = "$percentage%",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 60.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfessorRatingsScreenPreview() {
    RateMyProfTheme {
        ProfessorRatingsScreen(
            professorName = "Dr. Rachel",
            averageRating = 4.6f,
            ratingPercentages = mapOf(
                5 to 80,
                4 to 25,
                3 to 10,
                2 to 3,
                1 to 2
            ),
            onBackClick = {}
        )
    }
}
