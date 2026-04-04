<?php
session_start();
include "../config/db.php";

// Only admin allowed
if (!isset($_SESSION['role']) || $_SESSION['role'] !== 'admin') {
    die("Access denied");
}

// Check if ID is provided
if (!isset($_POST['id'])) {
    die("No product ID");
}

$id = intval($_POST['id']); // safer

$sql = "UPDATE products SET status = 'approved' WHERE id = $id";

if ($conn->query($sql)) {
    echo "Product approved";
} else {
    echo "Error: " . $conn->error;
}
?>