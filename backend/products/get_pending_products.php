<?php
session_start();
include "../config/db.php";

// Only admin allowed
if (!isset($_SESSION['role']) || $_SESSION['role'] !== 'admin') {
    die("Access denied");
}

$sql = "SELECT id, name, price FROM products WHERE status = 'pending'";
$result = $conn->query($sql);

$products = [];

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $products[] = $row;
    }
}

// Return JSON
header('Content-Type: application/json');
echo json_encode($products);
?>