<?php

error_reporting(E_ALL);
ini_set('display_errors', '1');

include "config.php";

// Verificar la conexión
if (!$link) {
    die("Ha fallado la conexión: " . mysqli_connect_error());
}

// Obtener el ID de la persona desde la URL
$idBuys = isset($_GET['id']) ? $_GET['id'] : null;

// Validar el ID
if (empty($idBuys)) {
    header("Location: consulta.php");
    exit(); // Añadido para evitar que el script continúe ejecutándose
}

// Utilizar sentencia preparada para prevenir la inyección de SQL
$stmt = mysqli_prepare($link, "DELETE FROM people WHERE idBuys = ?");
mysqli_stmt_bind_param($stmt, "s", $idBuys);

// Ejecutar la consulta
mysqli_stmt_execute($stmt);

// Verificar si la consulta fue exitosa
if (mysqli_stmt_affected_rows($stmt) > 0) {
    // La fila fue eliminada correctamente
    header("Location: consulta.php");
} else {
    // No se pudo procesar la transacción
    die("No se puede procesar la transacción: " . mysqli_error($link));
}

// Cerrar la conexión y la sentencia preparada
mysqli_stmt_close($stmt);
maysqli_close($link);
?>