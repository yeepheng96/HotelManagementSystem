<?php
error_reporting(0);
include_once("connect.php");
$email = $_POST['email'];
$password = $_POST['password'];

$sql = "SELECT * FROM USER WHERE email = '$email' AND password = '$password'";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
    echo "success";
}else{
    echo "failed";
}
?>