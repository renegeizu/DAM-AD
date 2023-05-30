<%@page import="com.izv.util.Util"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar-DetallePedidos</title>
    </head>
    <body>
        <h1>Agregar DetallePedido</h1>
        <form action="controlador" method="POST">
            <input type="hidden" name="op" value="opInsertarDetalle" />
	    <input type="hidden" name="idPedido" value="<%=request.getAttribute("id")%>" />
	    <% String select=(String) request.getAttribute("cartas");
	       out.println(select); %>
	    <br/>
	    <input placeholder="cantidad" type="text" name="cantidad" value="" /><br/>
            <input type="submit" value="agregar" />
        </form>
        <h3><a href=".">Inicio</a></h3>
    </body>
</html>