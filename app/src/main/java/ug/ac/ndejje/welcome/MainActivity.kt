package ug.ac.ndejje.welcome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ug.ac.ndejje.welcome.ui.theme.NdejjeWelcomeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NdejjeWelcomeAppTheme {
                StudentDirectory()
            }
        }
    }
}

@Composable
fun StudentInfo(student: Student) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = student.profileImageId),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(bottom = 8.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = student.name, style = MaterialTheme.typography.headlineSmall)
        Text(text = student.regNumber, color = Color.Gray)
        if (student.isVerified) {
            Text("Verified Student", color = Color(0xFF4CAF50))
        }
    }
}

@Composable
fun StudentIdCard(student: Student) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StudentInfo(student)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* TODO: Add action */ }) {
                Text("View Profile")
            }
        }
    }
}

@Composable
fun StudentDirectory() {
    var searchQuery by remember { mutableStateOf("") }

    val filteredStudents = StudentProvider.studentList.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.programme.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text(stringResource(R.string.search_placeholder)) },
            leadingIcon = {
                Icon(Icons.Filled.Search, contentDescription = "Search Icon")
            },
            singleLine = true
        )

        if (filteredStudents.isEmpty()) {
            Text(
                text = "No students found",
                modifier = Modifier.padding(16.dp),
                color = Color.Gray
            )
        } else {
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(filteredStudents, key = { it.id }) { student ->
                    StudentIdCard(student)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    NdejjeWelcomeAppTheme {
        val sampleStudent = StudentProvider.studentList.first()
        StudentIdCard(student = sampleStudent)
    }
}