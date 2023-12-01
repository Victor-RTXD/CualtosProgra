<?php

error_reporting(E_ALL);
ini_set('display_errors', '1');

include "config.php";

$idBuys = $_POST['idBuys'];
$suppliers = $_POST['provedor'];
$quantity = $_POST['cantidad'];
$payment_method = $_POST['metodo_de_pago'];
$buydate = $_POST['fecha_de_pago'];
$totalprice = $_POST['precio_total'];


$query = "UPDATE buys SET suppliers=?, quantity=?, payment_method=?, buydate=?, totalprice=?  WHERE idBuys=?";
$stmt = mysqli_prepare($link, $query);

// Vincular parámetros
mysqli_stmt_bind_param($stmt, "sssi", $suppliers, $quantity, $payment_method, $buydate, $totalprice, $idpeople);

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
