package com.example.pedestal.ui.screens.customer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pedestal.data.Order
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(
    orders: List<Order>,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Orders") },
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
                Text("You have no orders yet.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(orders) { order ->
                    OrderItemRow(order)
                }
            }
        }
    }
}

@Composable
fun OrderItemRow(order: Order) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Order #${order.id}", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = order.status,
                    style = MaterialTheme.typography.bodyMedium,
                    color = when (order.status) {
                        "Delivered" -> MaterialTheme.colorScheme.primary
                        "Pending" -> MaterialTheme.colorScheme.secondary
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Total: $${String.format(Locale.getDefault(), "%.2f", order.totalAmount)}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Date: ${Date(order.timestamp)}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
