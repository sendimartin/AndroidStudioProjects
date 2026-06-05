package com.example.pedestal.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    onManageUsersClick: () -> Unit,
    onManageProductsClick: () -> Unit,
    onManageOrdersClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Admin Dashboard") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AdminCard(
                title = "Manage Users",
                description = "View and edit user accounts",
                onClick = onManageUsersClick
            )
            AdminCard(
                title = "Manage Products",
                description = "Oversee all product listings",
                onClick = onManageProductsClick
            )
            AdminCard(
                title = "Manage Orders",
                description = "Track and manage transactions",
                onClick = onManageOrdersClick
            )
        }
    }
}

@Composable
fun AdminCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onClick) {
                Text("View Details")
            }
        }
    }
}
