<?php
$servername = "localhost";
$username   = "id9069445_hoteladmin";
$password   = "hotel12345";
$dbname     = "id9069445_hotelmanagementsystem";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>