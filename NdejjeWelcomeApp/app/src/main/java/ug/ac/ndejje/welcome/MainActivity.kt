package ug.ac.ndejje.welcome



import android.os.Bundle

import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

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

    Column(

        modifier = Modifier.padding(10.dp),

        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        val profileImage = painterResource(R.drawable.id_photo)

        val logoImage = painterResource(R.drawable.ndejje_university_logo)



        Box(modifier = Modifier.padding(10.dp)) {

            Image(

                painter = profileImage,

                contentDescription = "Student Photo",

                contentScale = ContentScale.Crop,

                modifier = Modifier.clip(RoundedCornerShape(percent = 10))

            )

            Image(

                painter = logoImage,

                contentDescription = null,

                modifier = Modifier

                    .size(80.dp)

                    .align(Alignment.BottomEnd)

                    .padding(4.dp)

            )

        }



        Spacer(modifier = Modifier.height(16.dp))



        Text(

            text = stringResource(R.string.student_name),

            style = MaterialTheme.typography.headlineSmall,

            fontWeight = FontWeight.Bold,

            color = MaterialTheme.colorScheme.primary

        )



        Text(

            text = stringResource(R.string.programme).uppercase(),

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

            Text(

                text = "REG NO.:",

                style = MaterialTheme.typography.labelLarge,

                fontWeight = FontWeight.ExtraBold

            )

            Spacer(modifier = Modifier.width(10.dp))

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

        StudentInfo()

    }

}



@Preview(showBackground = true)

@Composable

fun WelcomePreview() {

    NdejjeWelcomeAppTheme {

        StudentIdCard()

    }

}