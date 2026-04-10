<?php
session_start();

// If user already logged in → redirect based on role
if (isset($_SESSION['role'])) {

    if ($_SESSION['role'] === 'admin') {
        header("Location: frontend/admin_dashboard.html");
    } elseif ($_SESSION['role'] === 'seller') {
        header("Location: frontend/seller_dashboard.html");
    } else {
        header("Location: frontend/index.html");
    }

} else {
    // Not logged in → go to login page
    header("Location: frontend/login.html");
}
exit();
?>