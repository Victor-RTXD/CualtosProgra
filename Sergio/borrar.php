<?php

error_reporting(E_ALL);
ini_set('display_errors', '1');

include "config.php";

if (!$link) {
    die("Ha fallado la conexión: " . mysqli_connect_error());
}

$idBuys = isset($_GET['id']) ? $_GET['id'] : null;


if (empty($idBuys)) {
    header("Location: consulta.php");
    exit();
}


$stmt = mysqli_prepare($link, "DELETE FROM buys WHERE idBuys = ?");
mysqli_stmt_bind_param($stmt, "s", $idBuys);

mysqli_stmt_execute($stmt);


if (mysqli_stmt_affected_rows($stmt) > 0) {

    header("Location: consulta.php");
} else {
    die("No se puede procesar la transacción: " . mysqli_error($link));
}

mysqli_stmt_close($stmt);
maysqli_close($link);
?>