<?php
session_start();
include "db.php";

$user_id = $_SESSION['user_id'];
$product_id = $_POST['product_id'];
$quantity = $_POST['quantity'];

$sql = "INSERT INTO cart (user_id, product_id, quantity)
        VALUES ('$user_id', '$product_id', '$quantity')";

$conn->query($sql);

echo "Added to cart";
?>