<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar-Mesas</title>
    </head>
    <body>
        <h1>Agregar Mesa</h1>
        <form action="controlador" method="POST">
            <input type="hidden" name="op" value="opInsertarMesa" />
            <input placeholder="mesa" type="text" name="mesa" value="" /><br/>
            <input type="submit" value="agregar" />
        </form>
        <h3><a href=".">Inicio</a></h3>
    </body>
</html>