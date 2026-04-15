<?php
session_start();
include "db.php";

$user_id = $_SESSION['user_id'];

$sql = "SELECT products.name, products.price, cart.quantity
        FROM cart
        JOIN products ON cart.product_id = products.id
        WHERE cart.user_id = '$user_id'";

$result = $conn->query($sql);

$cart = [];

while ($row = $result->fetch_assoc()) {
    $cart[] = $row;
}

echo json_encode($cart);
?>