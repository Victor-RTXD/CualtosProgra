<?php
error_reporting(E_ALL);
ini_set('display_errors', '1');

include "config.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $provedor = isset($_POST['provedor']) ? $_POST['provedor'] : '';
    $cantidad = isset($_POST['cantidad']) ? $_POST['cantidad'] : '';
    $metodo_de_pago = isset($_POST['metodo_de_pago']) ? $_POST['metodo_de_pago'] : '';
    $precio_total = isset($_POST['precio_total']) ? $_POST['precio_total'] : '';

    $query = "INSERT INTO buys (suppliers, quantity, payment_method, totalprice) VALUES (?, ?, ?, ?)";
    $stmt = mysqli_prepare($link, $query);

    if ($stmt) {
        mysqli_stmt_bind_param($stmt, "sssd", $provedor, $cantidad, $metodo_de_pago, $precio_total);

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
        die("Error en la preparación de la consulta: " . mysqli_error($link));
    }
}

mysqli_close($link);
?>
