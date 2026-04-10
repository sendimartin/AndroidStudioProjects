<?php
include "../config/db.php";

$name = $_POST['name'];
$email = $_POST['email'];
$phone = $_POST['phone'];
$password = password_hash($_POST['password'], PASSWORD_DEFAULT);
$role = $_POST['role'];

$email = $conn->real_escape_string($email);

// Check if email exists
$sql = "SELECT * FROM users WHERE email='$email'";
$res = $conn->query($sql);
if($res->num_rows > 0){
    echo "Email already exists";
    exit;
}

// Insert user (created_at uses default timestamp)
$sql = "INSERT INTO users (name,email,phone,password,role) 
        VALUES ('$name','$email','$phone','$password','$role')";

if($conn->query($sql)){
    echo "success";  // <-- this triggers the JS alert
}else{
    echo "Error inserting user: " . $conn->error;
}
?>