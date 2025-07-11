import android.os.Handler
import android.os.Looper
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.saveetha.ratemyprof.ui.theme.RateMyProfTheme
import com.saveetha.ratemyprof.R

@Composable
fun FormSubmittedScreen(onGoHomeClicked: () -> Unit) {
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            onGoHomeClicked()
        }, 5000)
    }

    BackHandler(enabled = true) {
        onGoHomeClicked()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "RateMyProf.",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        Icon(
            painter = painterResource(id = R.drawable.mailbox),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(bottom = 24.dp),
            tint = Color(0xFF4CAF50)
        )

        Text(
            text = "Thank you",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your message has been received.\nOne of our team members will be\nin touch with you shortly.",
            fontSize = 16.sp,
            color = Color.Gray,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 8.dp),
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormSubmittedScreenPreview() {
    RateMyProfTheme {
        FormSubmittedScreen(onGoHomeClicked = {})
    }
}
