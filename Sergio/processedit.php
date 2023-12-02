<?php
error_reporting(E_ALL);
ini_set('display_errors', '1');

include "config.php";

if (
    isset($_POST['idBuys']) &&
    isset($_POST['provedor']) &&
    isset($_POST['cantidad']) &&
    isset($_POST['metodo_de_pago']) &&
    isset($_POST['fecha_de_pago']) &&
    isset($_POST['precio_total'])
) {
    $idBuys = $_POST['idBuys'];
    $suppliers = $_POST['provedor'];
    $quantity = $_POST['cantidad'];
    $payment_method = $_POST['metodo_de_pago'];
    $buydate = $_POST['fecha_de_pago'];
    $totalprice = $_POST['precio_total'];

    $query = "UPDATE buys SET suppliers=?, quantity=?, payment_method=?, buydate=?, totalprice=? WHERE idBuys=?";
    $stmt = mysqli_prepare($link, $query);

    mysqli_stmt_bind_param($stmt, "ssssii", $suppliers, $quantity, $payment_method, $buydate, $totalprice, $idBuys);

    mysqli_stmt_execute($stmt);

    $affected_rows = mysqli_stmt_affected_rows($stmt);
    if ($affected_rows > 0) {
        header("Location: consulta.php");
        exit(); 
    } else {
        die("No se puede procesar la transacción: " . mysqli_error($link));
    }

    mysqli_stmt_close($stmt);
} else {
    die("No se han recibido todos los datos necesarios para la actualización.");
}
?>
