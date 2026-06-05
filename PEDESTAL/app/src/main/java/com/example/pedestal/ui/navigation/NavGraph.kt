package com.example.pedestal.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pedestal.data.UserRole
import com.example.pedestal.ui.MainViewModel
import com.example.pedestal.ui.screens.admin.AdminDashboardScreen
import com.example.pedestal.ui.screens.admin.ManageOrdersScreen
import com.example.pedestal.ui.screens.admin.ManageProductsScreen
import com.example.pedestal.ui.screens.admin.ManageUsersScreen
import com.example.pedestal.ui.screens.auth.LoginScreen
import com.example.pedestal.ui.screens.auth.RegisterScreen
import com.example.pedestal.ui.screens.customer.CartScreen
import com.example.pedestal.ui.screens.customer.OrdersScreen
import com.example.pedestal.ui.screens.customer.ProductDetailsScreen
import com.example.pedestal.ui.screens.customer.ProductListScreen
import com.example.pedestal.ui.screens.seller.AddProductScreen
import com.example.pedestal.ui.screens.seller.EditProductScreen
import com.example.pedestal.ui.screens.seller.SellerDashboardScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object CustomerHome : Screen("customer_home")
    object ProductDetails : Screen("product_details/{productId}") {
        fun createRoute(productId: String) = "product_details/$productId"
    }
    object Cart : Screen("cart")
    object Orders : Screen("orders")
    object SellerDashboard : Screen("seller_dashboard")
    object AddProduct : Screen("add_product")
    object EditProduct : Screen("edit_product/{productId}") {
        fun createRoute(productId: String) = "edit_product/$productId"
    }
    object AdminDashboard : Screen("admin_dashboard")
    object ManageUsers : Screen("manage_users")
    object ManageProducts : Screen("manage_products")
    object ManageOrders : Screen("manage_orders")
}

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = modifier
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { role ->
                    viewModel.login(role)
                    val destination = when (role) {
                        UserRole.CUSTOMER -> Screen.CustomerHome.route
                        UserRole.SELLER -> Screen.SellerDashboard.route
                        UserRole.ADMIN -> Screen.AdminDashboard.route
                    }
                    navController.navigate(destination) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(
                onRegister = { name, email, role ->
                    viewModel.register(name, email, role)
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }
        composable(route = Screen.CustomerHome.route) {
            val products by viewModel.products.collectAsState()
            ProductListScreen(
                products = products,
                onProductClick = { product ->
                    navController.navigate(Screen.ProductDetails.createRoute(product.id))
                },
                onNavigateToCart = {
                    navController.navigate(Screen.Cart.route)
                },
                onLogout = {
                    viewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable(route = Screen.ProductDetails.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val products by viewModel.products.collectAsState()
            val product = products.find { it.id == productId }
            
            ProductDetailsScreen(
                product = product,
                onBackClick = { navController.popBackStack() },
                onAddToCart = { prod ->
                    viewModel.addToCart(prod)
                    navController.navigate(Screen.Cart.route)
                }
            )
        }
        composable(route = Screen.Cart.route) {
            val cartItems by viewModel.cart.collectAsState()
            val products by viewModel.products.collectAsState()
            CartScreen(
                cartItems = cartItems,
                products = products,
                onBackClick = { navController.popBackStack() },
                onCheckoutClick = {
                    viewModel.checkout()
                    navController.navigate(Screen.Orders.route)
                },
                onRemoveItem = { productId ->
                    viewModel.removeFromCart(productId)
                }
            )
        }
        composable(route = Screen.Orders.route) {
            val orders by viewModel.orders.collectAsState()
            OrdersScreen(
                orders = orders,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(route = Screen.SellerDashboard.route) {
            val products by viewModel.products.collectAsState()
            val user by viewModel.currentUser.collectAsState()
            val sellerProducts = products.filter { it.sellerId == user?.id }
            
            SellerDashboardScreen(
                products = sellerProducts,
                onAddProductClick = {
                    navController.navigate(Screen.AddProduct.route)
                },
                onEditProductClick = { product ->
                    navController.navigate(Screen.EditProduct.createRoute(product.id))
                },
                onDeleteProductClick = { product ->
                    viewModel.deleteProduct(product.id)
                },
                onLogout = {
                    viewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable(route = Screen.AddProduct.route) {
            val user by viewModel.currentUser.collectAsState()
            AddProductScreen(
                onBackClick = { navController.popBackStack() },
                onProductAdded = { product ->
                    viewModel.addProduct(product.copy(sellerId = user?.id ?: ""))
                    navController.popBackStack()
                }
            )
        }
        composable(route = Screen.EditProduct.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val products by viewModel.products.collectAsState()
            val product = products.find { it.id == productId }

            EditProductScreen(
                product = product,
                onBackClick = { navController.popBackStack() },
                onProductUpdated = { updatedProduct ->
                    viewModel.updateProduct(updatedProduct)
                    navController.popBackStack()
                }
            )
        }
        composable(route = Screen.AdminDashboard.route) {
            AdminDashboardScreen(
                onManageUsersClick = { navController.navigate(Screen.ManageUsers.route) },
                onManageProductsClick = { navController.navigate(Screen.ManageProducts.route) },
                onManageOrdersClick = { navController.navigate(Screen.ManageOrders.route) },
                onLogout = {
                    viewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable(route = Screen.ManageUsers.route) {
            val users by viewModel.users.collectAsState()
            ManageUsersScreen(
                users = users,
                onDeleteUser = { userId -> viewModel.deleteUser(userId) },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(route = Screen.ManageProducts.route) {
            val products by viewModel.products.collectAsState()
            ManageProductsScreen(
                products = products,
                onDeleteProduct = { productId -> viewModel.deleteProduct(productId) },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(route = Screen.ManageOrders.route) {
            val orders by viewModel.orders.collectAsState()
            ManageOrdersScreen(
                orders = orders,
                onUpdateStatus = { id, status -> viewModel.updateOrderStatus(id, status) },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun PlaceholderScreen(name: String) {
    Text(text = "Welcome to $name")
}
