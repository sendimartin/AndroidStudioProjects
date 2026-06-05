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
import com.example.pedestal.data.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageProductsScreen(
    products: List<Product>,
    onDeleteProduct: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Products") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (products.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("No products found.")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(products) { product ->
                    ListItem(
                        headlineContent = { Text(product.name) },
                        supportingContent = { Text("Seller: ${product.sellerId} | Price: $${product.price}") },
                        trailingContent = {
                            IconButton(onClick = { onDeleteProduct(product.id) }) {
                                Text("Delete", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    )
                }
            }
        }
    }
}
