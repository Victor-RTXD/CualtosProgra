<?php

error_reporting(E_ALL);
ini_set('display_errors', '1');

include "config.php";

//$idpeople = $_POST['id'];
$provedor = $_POST['provedor'];
$cantidad = $_POST['cantidad'];
$metodo_de_pago = $_POST['metodo_de_pago'];
$precio_total = $_POST['precio_total'];

$query = "INSERT INTO buys (suppliers, quantity, payment_method, totalprice) VALUES (?, ?, ?, ?)";
$stmt = mysqli_prepare($link, $query);

// Vincular parámetros
mysqli_stmt_bind_param($stmt, "sssssssssss", $provedor, $cantidad, $metodo_de_pago, $precio_total);

// Ejecutar la consulta
mysqli_stmt_execute($stmt);

// Verificar si la consulta fue exitosa
$affected_rows = mysqli_stmt_affected_rows($stmt);
if ($affected_rows > 0) {
    // La actualización fue exitosa
    header("Location: consulta.php");
} else {
    // No se pudo procesar la transacción
    die("No se puede procesar la transacción: " . mysqli_error($link));
}

// Cerrar la sentencia preparada
mysqli_stmt_close($stmt);
?>