<?php
session_start();
include "db.php";

$user_id = $_SESSION['user_id'];

$sql = "SELECT * FROM cart WHERE user_id='$user_id'";
$result = $conn->query($sql);

$total = 0;

while ($row = $result->fetch_assoc()) {
    $product_id = $row['product_id'];
    $quantity = $row['quantity'];

    $product = $conn->query("SELECT price FROM products WHERE id='$product_id'")->fetch_assoc();

    $total += $product['price'] * $quantity;
}

$conn->query("INSERT INTO orders (user_id, total) VALUES ('$user_id', '$total')");
$order_id = $conn->insert_id;

$result = $conn->query("SELECT * FROM cart WHERE user_id='$user_id'");

while ($row = $result->fetch_assoc()) {
    $product_id = $row['product_id'];
    $quantity = $row['quantity'];

    $product = $conn->query("SELECT price FROM products WHERE id='$product_id'")->fetch_assoc();

    $conn->query("INSERT INTO order_items (order_id, product_id, quantity, price)
                  VALUES ('$order_id', '$product_id', '$quantity', '{$product['price']}')");
}

$conn->query("DELETE FROM cart WHERE user_id='$user_id'");

echo "Order placed successfully";
?>