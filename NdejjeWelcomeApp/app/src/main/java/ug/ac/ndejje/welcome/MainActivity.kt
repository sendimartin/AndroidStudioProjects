package ug.ac.ndejje.welcome

import ug.ac.ndejje.welcome.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column // Imported properly
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ug.ac.ndejje.welcome.ui.theme.NdejjeWelcomeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NdejjeWelcomeAppTheme {
                StudentIdCard()
            }
        }
    }
}

@Composable
fun StudentInfo() {

    val profileImage = painterResource(R.drawable.id_photo)

    val logoImage = painterResource(R.drawable.ndejje_university_logo)
    Column(



        modifier = Modifier.padding(10.dp),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Box(modifier = Modifier.padding(10.dp)) {
            //images
            Image(

                painter = profileImage,

                contentDescription = "Student Photo",

                contentScale = ContentScale.Crop

            )



            Image(

                painter = logoImage,

                contentDescription = null

            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        // 1. Student full name
        Text(
            text = stringResource(R.string.student_name),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // 2. Programme of study
        Text(
            text = stringResource(R.string.programme).uppercase(), // Added .uppercase() as per your comment
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        HorizontalDivider(

            modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp),

            thickness = 1.dp,

            color = MaterialTheme.colorScheme.outlineVariant

        )
        Row(modifier = Modifier) {
            // 3. Static label
            Text(
                text = "REG NO.:",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.ExtraBold
            )

            // 4. The actual registration number
            Text(
                text = stringResource(R.string.reg_number),
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

@Composable

fun StudentIdCard() {

    ElevatedCard(

        modifier = Modifier

            .fillMaxWidth()

            .padding(16.dp),

        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.elevatedCardColors(

            containerColor = MaterialTheme.colorScheme.surfaceVariant

                .copy(alpha = 0.3f)

        )

    ) {

// The card's content — call our StudentInfo() here

        StudentIdCard()

    }

}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    NdejjeWelcomeAppTheme {
        StudentIdCard()
    }
}