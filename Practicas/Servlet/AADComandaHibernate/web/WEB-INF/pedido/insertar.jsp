<%@page import="com.izv.util.Util"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar-Pedidos</title>
    </head>
    <body>
        <h1>Agregar Pedido</h1>
        <form action="controlador" method="POST">
            <input type="hidden" name="op" value="opInsertarPedido" />
	    <% String select=(String) request.getAttribute("mesas");
	       out.println(select); %>
	    <br />
	    <% String[][] valores={{"0", "abierto"}, {"1", "cerrado"}};
	       out.println(Util.select("cerrado", valores, "")); %>
	    <br />
            <input type="submit" value="agregar" />
        </form>
        <h3><a href=".">Inicio</a></h3>
    </body>
</html>