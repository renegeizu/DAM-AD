<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar-Cartas</title>
    </head>
    <body>
        <h1>Agregar Carta</h1>
        <form action="controlador" method="POST">
            <input type="hidden" name="op" value="opInsertarCarta" />
            <input placeholder="carta" type="text" name="carta" value="" /><br/>
	    <input placeholder="precio" type="text" name="precio" value="" /><br/>
            <input type="submit" value="agregar" />
        </form>
        <h3><a href=".">Inicio</a></h3>
    </body>
</html>