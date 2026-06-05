package com.example.pedestal.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pedestal.data.Order
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageOrdersScreen(
    orders: List<Order>,
    onUpdateStatus: (String, String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Orders") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (orders.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("No orders found.")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(orders) { order ->
                    AdminOrderRow(order = order, onUpdateStatus = onUpdateStatus)
                }
            }
        }
    }
}

@Composable
fun AdminOrderRow(order: Order, onUpdateStatus: (String, String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val statuses = listOf("Pending", "Shipped", "Delivered", "Cancelled")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Order #${order.id}", style = MaterialTheme.typography.titleMedium)
            Text(text = "User: ${order.userId} | Total: $${order.totalAmount}")
            Text(text = "Status: ${order.status}", color = MaterialTheme.colorScheme.primary)
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Box {
                Button(onClick = { expanded = true }) {
                    Text("Change Status")
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    statuses.forEach { status ->
                        DropdownMenuItem(
                            text = { Text(status) },
                            onClick = {
                                onUpdateStatus(order.id, status)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}
