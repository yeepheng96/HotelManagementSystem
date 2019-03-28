<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
	$name = $_POST['name'];
	$password = $_POST['password'];
	$email = $_POST['email'];
	$phone = $_POST['phone'];

	require_once 'connect.php';

	$sql = "INSERT INTO hoteluser (name,password,email,phone) VALUES ('$name','$password','$email','$phone')";

	if ($conn->query($sql) === TRUE){
       echo "success";
    }else {
        echo "failed";
    }
}

?>