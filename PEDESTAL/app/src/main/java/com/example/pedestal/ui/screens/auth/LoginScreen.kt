package com.example.pedestal.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.pedestal.data.UserRole

@Composable
fun LoginScreen(
    onLoginSuccess: (UserRole) -> Unit,
    onNavigateToRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "PEDESTAL", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Version 1 Demo", style = MaterialTheme.typography.bodyMedium)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(text = "Quick Login (Demo Mode):", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))
        
        Button(
            onClick = { onLoginSuccess(UserRole.CUSTOMER) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login as Customer")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedButton(
            onClick = { onLoginSuccess(UserRole.SELLER) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login as Seller")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedButton(
            onClick = { onLoginSuccess(UserRole.ADMIN) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login as Admin")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onNavigateToRegister) {
            Text("Don't have an account? Register")
        }
    }
}
