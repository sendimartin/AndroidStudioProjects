<?php
include "../config/db.php";

$sql = "SELECT * FROM products WHERE status='approved'";
$result = $conn->query($sql);

$products = [];
while ($row = $result->fetch_assoc()) {
    $products[] = $row;
}

echo json_encode($products);
?>