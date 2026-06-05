package com.example.pedestal.ui

import androidx.lifecycle.ViewModel
import com.example.pedestal.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _users = MutableStateFlow<List<User>>(
        listOf(
            User("u1", "Admin User", "admin@pedestal.com", UserRole.ADMIN),
            User("s1", "Seller One", "seller@pedestal.com", UserRole.SELLER)
        )
    )
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val _products = MutableStateFlow<List<Product>>(
        listOf(
            Product(id = "1", name = "Premium Pedestal", description = "Elegant wood finish", price = 150.0, stock = 5, sellerId = "s1"),
            Product(id = "2", name = "Modern Stand", description = "Sleek metal design", price = 85.0, stock = 12, sellerId = "s1"),
            Product(id = "3", name = "Classic Column", description = "Marble style pedestal", price = 300.0, stock = 2, sellerId = "s2")
        )
    )
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _cart = MutableStateFlow<List<CartItem>>(emptyList())
    val cart: StateFlow<List<CartItem>> = _cart.asStateFlow()

    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    fun login(role: UserRole) {
        // Mock login: find first user with that role or create a temporary one
        val user = _users.value.find { it.role == role } ?: User("tmp", "Demo ${role.name}", "demo@test.com", role)
        _currentUser.value = user
    }

    fun register(name: String, email: String, role: UserRole) {
        val newUser = User(
            id = "u${System.currentTimeMillis()}",
            name = name,
            email = email,
            role = role
        )
        _users.update { it + newUser }
    }

    fun logout() {
        _currentUser.value = null
        _cart.value = emptyList()
    }

    // User Management (Admin)
    fun deleteUser(userId: String) {
        _users.update { it.filter { user -> user.id != userId } }
    }

    // Product Management
    fun addToCart(product: Product) {
        _cart.update { currentCart ->
            val existing = currentCart.find { it.productId == product.id }
            if (existing != null) {
                currentCart.map {
                    if (it.productId == product.id) it.copy(quantity = it.quantity + 1) else it
                }
            } else {
                currentCart + CartItem(productId = product.id, quantity = 1)
            }
        }
    }

    fun removeFromCart(productId: String) {
        _cart.update { currentCart ->
            currentCart.filter { it.productId != productId }
        }
    }

    fun checkout() {
        val currentCart = _cart.value
        if (currentCart.isEmpty()) return

        val total = currentCart.sumOf { item ->
            val product = _products.value.find { it.id == item.productId }
            (product?.price ?: 0.0) * item.quantity
        }

        val newOrder = Order(
            id = "ORD-${System.currentTimeMillis()}",
            userId = _currentUser.value?.id ?: "guest",
            items = currentCart,
            totalAmount = total,
            status = "Pending",
            timestamp = System.currentTimeMillis()
        )

        _orders.update { it + newOrder }
        _cart.value = emptyList()
    }

    fun addProduct(product: Product) {
        _products.update { it + product }
    }

    fun updateProduct(updatedProduct: Product) {
        _products.update { currentList ->
            currentList.map { if (it.id == updatedProduct.id) updatedProduct else it }
        }
    }

    fun deleteProduct(productId: String) {
        _products.update { currentList ->
            currentList.filter { it.id != productId }
        }
    }

    // Order Management (Admin)
    fun updateOrderStatus(orderId: String, newStatus: String) {
        _orders.update { currentOrders ->
            currentOrders.map { if (it.id == orderId) it.copy(status = newStatus) else it }
        }
    }
}
