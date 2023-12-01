<?php
include "config.php";
$resultado = mysqli_query($link, "SELECT * FROM buys");
?>
<!DOCTYPE html>
<html>
<head>
	<title>Mi primer pagina</title>
</head>
<body>
<h1>Mi primer pagina, consulta a la base de datos y colocarla en una tabla</h1>
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
// while($filas = mysqli_fetch_array($resultado, MYSQLI_ASSOC)){
// 	$id = $filas['idpeople'];
// 	echo	"<tr>";
// 	echo		"<td>".$filas['idpeople']."</td>";
// 	echo		"<td>".$filas['names']."</td>";
// 	echo		"<td>".$filas['lastname']."</td>";
// 	echo		"<td>".$filas['country']."</td>";
// 	echo		"<td><a href='editar.php?id=$id'>Editar</a></td>";
// 	echo		"<td><a href='borrar.php?id=$id'>Borrar</a></td>";
// 	echo	"</tr>";

// }

while($filas = mysqli_fetch_array($resultado, MYSQLI_ASSOC)){
	$id = $filas['idBuys'];
	echo	"<tr>";
	echo		"<td>".$filas['idBuys']."</td>";
	echo		"<td>".$filas['suppliers']."</td>";
	echo		"<td>".$filas['quantity']."</td>";
	echo		"<td>".$filas['payment_methody']."</td>";
	echo		"<td>".$filas['buydate']."</td>";
	echo		"<td>".$filas['totalprice']."</td>";
	echo		"<td><a href='editar.php?id=$id'>Editar</a></td>";
	echo		"<td><a href='borrar.php?id=$id'>Borrar</a></td>";
	echo	"</tr>";

}
?>
</table>
</body>
</html>
