<?php
include "config.php";
$resultado = mysqli_query($link, "SELECT * FROM buys");
?>
<!DOCTYPE html>
<html>
<head>
	<title>Pagina de compras</title>

	<style>
		body {
			background: lightgray;			
		}

		h1 {
			display: flex;
	    		justify-content: center;
	    		align-items: center;
		}	
	</style>
</head>
<body>
<h1>crud de compras</h1>
<button onclick="location.href='insertar.php'">Insertar Nuevo</button>
<table border="1">
	<tr>
		<td>Id de compra</td>
		<td>Provedores</td> 	
		<td>Cantidad</td>
		<td>Metodo de pago</td>
		<td>Fecha de pago</td>
		<td>Precio total</td>
		<td>Editar</td>
		<td>Borrar</td>
	</tr>
<?php

while ($filas = mysqli_fetch_array($resultado, MYSQLI_ASSOC)) {
    $id = $filas['idBuys'];
    echo "<tr>";
    echo "<td>" . $filas['idBuys'] . "</td>";
    echo "<td>" . $filas['suppliers'] . "</td>";
    echo "<td>" . $filas['quantity'] . "</td>";

    // Obtener el método de pago utilizando el ID almacenado en $filas['payment_method']
    $paymentMethodId = $filas['payment_method'];
    $queryPaymentMethod = mysqli_query($link, "SELECT method FROM payment_method WHERE idpayment_method = $paymentMethodId");
    
    if ($row = mysqli_fetch_assoc($queryPaymentMethod)) {
        $paymentMethod = $row['method'];
        echo "<td>" . $paymentMethod . "</td>";
    } else {
        echo "<td>No definido</td>";
    }

    echo "<td>" . $filas['buydate'] . "</td>";
    echo "<td>" . $filas['totalprice'] . "</td>";
    echo "<td><a href='editar.php?id=$id'>Editar</a></td>";
    echo "<td><a href='borrar.php?id=$id'>Borrar</a></td>";
    echo "</tr>";
}

?>
</table>
<script>console.log("realizado por victor")</script>
</body>
</html>
