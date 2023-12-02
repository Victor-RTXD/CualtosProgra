<?php
$host = "localhost";
$db_username = "root";
$db_password = "Victor4061";
$db_name = "lab3";

$link = mysqli_connect($host,$db_username,$db_password,$db_name) or
	die ("Ha fallado la conexion".mysqli_connect_error());
?>
