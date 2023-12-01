<!DOCTYPE html>
<html>
<head>
        <title>Insertar</title>
</head>
<body>
<h1>Registrar una persona</h1>

<form name="Formulario" action="procesar_insertar.php" method="POST">
        <TABLE border="1">
        <tr>
                <th>Dato</th>
                <th>Campos</th>
        </tr>
        <tr>
                <td>Provedor</td>
                <td><input type="text" name="provedor" value="<?php echo $dato['suppliers']; ?>"></td>
        </tr>
        <tr>
                <td>Cantidad</td>
                <td><input type="text" name="cantidad" value="<?php echo $dato['quantity']; ?>"></td>
        </tr>
        <tr>
                <td>Metodo de pago</td>
                <td><input type="text" name="metodo_de_pago" value="<?php echo $dato['payment_method']; ?>"></td>
        </tr>
	<tr>
                <td>precio total</td>
                <td><input type="text" name="precio_total" value="<?php echo $dato['totalprice']; ?>"></td>
        </tr>
	
        <tr>
                <!-- se unen las dos columnas y se coloca un boton centrado para mandar el dato-->
                <td colspan="2" align="center"><input type="submit" value="Submit"></td>
        </tr>

        </TABLE>
</form>

</body>
</html>

<!-- 
        <form name="Formulario" action="procesar_insertar.php" method="POST">
        <TABLE border="1">
        <tr>
                <th>Dato</th>
                <th>Campos</th>
        </tr>
        <tr>
                <td>Nombre</td>
                <td><input type="text" name="nombres" value="<?php echo $dato['names']; ?>"></td>
        </tr>
        <tr>
                <td>Apellidos</td>
                <td><input type="text" name="apellidos" value="<?php echo $dato['lastname']; ?>"></td>
        </tr>
        <tr>
                <td>Pais</td>
                <td><input type="text" name="pais" value="<?php echo $dato['country']; ?>"></td>
	</td>
	<tr>
                <td>Estado</td>
                <td><input type="text" name="estado" value="<?php echo $dato['state']; ?>"></td>
	</td>
	<tr>
                <td>Direccion</td>
                <td><input type="text" name="direccion" value="<?php echo $dato['address']; ?>"></td>
	</td>
	<tr>
                <td>Ciudad</td>
                <td><input type="text" name="ciudad" value="<?php echo $dato['city']; ?>"></td>
	</td>
	<tr>
                <td>Delegacion</td>
                <td><input type="text" name="delegacion" value="<?php echo $dato['delegation']; ?>"></td>
	</td>
	<tr>
                <td>Codigo postal</td>
                <td><input type="text" name="codigoPostal" value="<?php echo $dato['zipcode']; ?>"></td>
	</td>
	<tr>
                <td>Numero Exterior</td>
                <td><input type="text" name="numeroExterior" value="<?php echo $dato['outdoorNum']; ?>"></td>
	</td>
	<tr>
                <td>Numero de unidad</td>
                <td><input type="text" name="numeroDeUnidad" value="<?php echo $dato['unitNum']; ?>"></td>
	</td>
	<tr>
                <td>RFC</td>
                <td><input type="text" name="RFC" value="<?php echo $dato['RFC']; ?>"></td>
	</td>

        <tr>
                <!-- se unen las dos columnas y se coloca un boton centrado para mandar el dato-->
                <td colspan="2" align="center"><input type="submit" value="Submit"></td>
        </tr>

        </TABLE>
</form>
 -->