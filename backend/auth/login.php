<?php
session_start();
include "../config/db.php";

$email = $_POST['email'];
$password = $_POST['password'];

// Clean input
$email = $conn->real_escape_string($email);

$sql = "SELECT * FROM users WHERE email='$email'";
$res = $conn->query($sql);

if($res->num_rows == 1){
    $user = $res->fetch_assoc();

    if(password_verify($password, $user['password'])){
        $_SESSION['user_id'] = $user['id'];
        $_SESSION['role'] = $user['role'];
        echo $user['role']; // this is read by JS
    } else {
        echo "Incorrect password";
    }

} else {
    echo "User not found";
}
?>