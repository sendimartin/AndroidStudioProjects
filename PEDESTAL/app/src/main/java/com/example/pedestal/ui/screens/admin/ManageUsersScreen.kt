package com.example.pedestal.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pedestal.data.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageUsersScreen(
    users: List<User>,
    onDeleteUser: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Users") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(users) { user ->
                UserItem(user = user, onDelete = { onDeleteUser(user.id) })
            }
        }
    }
}

@Composable
fun UserItem(user: User, onDelete: () -> Unit) {
    ListItem(
        headlineContent = { Text(user.name) },
        supportingContent = { Text("${user.email} | Role: ${user.role}") },
        trailingContent = {
            IconButton(onClick = onDelete) {
                Text("Delete", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
            }
        }
    )
}
