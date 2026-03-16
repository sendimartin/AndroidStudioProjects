package ug.ac.ndejje.ndejjeuniversityidcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ug.ac.ndejje.ndejjeuniversityidcard.ui.theme.NdejjeUniversityIdCardTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NdejjeUniversityIdCardTheme {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    StudentInfo()
                }

            }
        }
    }
}

@Composable
fun StudentInfo() {

    ElevatedCard(
        modifier = Modifier
            .width(340.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {

        Box {

            // for ndejje logo watermarks
            Image(
                painter = painterResource(id = R.drawable.ndejje_logo_watermark),
                contentDescription = "watermark",
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.CenterStart)
                    .alpha(0.08f)
            )

            Image(
                painter = painterResource(id = R.drawable.ndejje_logo_watermark),
                contentDescription = "watermark",
                modifier = Modifier
                    .size(90.dp)
                    .align(Alignment.CenterEnd)
                    .alpha(0.08f)
            )

            Column {

                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color(0xFF8B1E1E))
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        //for the logo
                        Image(
                            painter = painterResource(R.drawable.ndejje_university_logo),
                            contentDescription = "Ndejje University logo",
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .background(color = Color.White, CircleShape)
                                .size(40.dp)
                                .clip(CircleShape)

                        )

                        //for the flag
                        Image(
                            painter = painterResource(R.drawable.uganda_flag),
                            contentDescription = "Uganda National Flag",
                            modifier = Modifier.size(55.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                // details for the card
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //student name
                    Text(
                        text = stringResource(R.string.student_name).uppercase(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 12.sp
                    )

                    //programme
                    Text(
                        text = stringResource(R.string.programme).uppercase(),
                        fontSize = 11.sp,
                        lineHeight = 12.sp
                    )


                    //Reg number
                    Text(
                        text = "Registration Number: ${stringResource(R.string.reg_number)}"
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        //Date of issue
                        Text(
                            text = "Date of issue: ${stringResource(R.string.date_of_issue)}",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.ExtraBold
                        )

                        //Expiry date
                        Text(
                            text = "Expiry date: ${stringResource(R.string.expiry_date)}",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }


                    // Barcode
                    Row(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .padding(bottom = 4.dp)
                            .height(20.dp)
                            .fillMaxWidth(0.95f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        //to replicate a barcode
                        repeat(85) { index ->
                            Box(
                                modifier = Modifier
                                    .width( 1.dp)
                                    .fillMaxHeight()
                                    .background(Color.Black)
                            )
                            Spacer(modifier = Modifier.width(1.dp))
                        }
                    }

                }


                // For red footer color
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(15.dp)
                        .background(Color(0xFF8B1E1E))
                )

            }

            // profile photo
            Image(
                painter = painterResource(id = R.drawable.sipi),
                contentDescription = "photo",
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color(0xFF8B1E1E), CircleShape)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)

@Composable
fun WelcomePreview(){
    NdejjeUniversityIdCardTheme{
        StudentInfo()
    }
}