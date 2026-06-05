package com.example.pedestal.data

enum class UserRole {
    CUSTOMER, SELLER, ADMIN
}

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val role: UserRole = UserRole.CUSTOMER
)

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val stock: Int = 0,
    val sellerId: String = "",
    val imageUrl: String = ""
)

data class Order(
    val id: String = "",
    val userId: String = "",
    val items: List<CartItem> = emptyList(),
    val totalAmount: Double = 0.0,
    val status: String = "Pending",
    val timestamp: Long = System.currentTimeMillis()
)

data class CartItem(
    val productId: String = "",
    val quantity: Int = 0
)
