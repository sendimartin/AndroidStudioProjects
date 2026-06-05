package com.example.pedestal.ui.screens.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pedestal.data.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    product: Product?,
    onBackClick: () -> Unit,
    onAddToCart: (Product) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (product == null) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Product not found")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(text = product.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "$${product.price}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = product.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { onAddToCart(product) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = product.stock > 0
                ) {
                    Text(if (product.stock > 0) "Add to Cart" else "Out of Stock")
                }
            }
        }
    }
}
