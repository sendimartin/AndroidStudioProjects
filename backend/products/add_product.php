<?php
session_start();
include "../config/db.php";

if ($_SESSION['role'] !== 'seller') {
    die("Access denied");
}

$seller_id = $_SESSION['user_id'];

$name = $_POST['name'];
$description = $_POST['description'];
$price = $_POST['price'];
$stock = $_POST['stock'];
$image = $_POST['image'];

$sql = "INSERT INTO products (name, description, price, stock, image, seller_id, status)
        VALUES ('$name', '$description', '$price', '$stock', '$image', '$seller_id', 'pending')";

$conn->query($sql);

echo "Product submitted for approval";
?>