<!DOCTYPE html>
<html>
<head>
        <title>Insertar</title>
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
                <td>Método de pago</td>
                <td>
                    <select name="metodo_de_pago">
                        <option value="1">Efectivo</option>
                        <option value="2">Tarjeta de crédito</option>
                        <option value="3">Tarjeta de débito</option>
                        <option value="4">Transferencia bancaria</option>
                        <option value="5">Cheque</option>
                    </select>
                </td>
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
<script>console.log("realizado por victor")</script>
</body>
</html>
