package com.example.pedestal.ui.screens.customer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pedestal.data.CartItem
import com.example.pedestal.data.Product
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartItems: List<CartItem>,
    products: List<Product>,
    onBackClick: () -> Unit,
    onCheckoutClick: () -> Unit,
    onRemoveItem: (String) -> Unit
) {
    val cartWithDetails = cartItems.mapNotNull { item ->
        val product = products.find { it.id == item.productId }
        if (product != null) Pair(item, product) else null
    }
    
    val totalAmount = cartWithDetails.sumOf { (item, product) -> item.quantity * product.price }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Cart") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (cartItems.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Your cart is empty")
            }
        } else {
            Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(cartWithDetails) { (item, product) ->
                        CartItemRow(item, product, onRemoveItem)
                    }
                }
                
                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Total:", style = MaterialTheme.typography.headlineSmall)
                            Text("$${String.format(Locale.getDefault(), "%.2f", totalAmount)}", style = MaterialTheme.typography.headlineSmall)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = onCheckoutClick,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Proceed to Checkout")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem, product: Product, onRemoveItem: (String) -> Unit) {
    ListItem(
        headlineContent = { Text(product.name) },
        supportingContent = { Text("Qty: ${item.quantity} | $${String.format(Locale.getDefault(), "%.2f", product.price * item.quantity)}") },
        trailingContent = {
            IconButton(onClick = { onRemoveItem(item.productId) }) {
                Text("Remove", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
            }
        }
    )
}
