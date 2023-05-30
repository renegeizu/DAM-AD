<%@page import="hibernate.Detallepedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar-DetallePedidos</title>
    </head>
    <body>
        <h1>Editar DetallePedido</h1>
        <form action="controlador" method="POST">
            <% Detallepedido m = (Detallepedido) request.getAttribute("detalle"); %>
            <input type="hidden" name="op" value="opEditarDetalle" />
            <input type="hidden" name="id" value="<%=m.getId()%>" /><br/>
            <input type="hidden" name="idPedido" value="<%=m.getPedido().getId()%>" readonly="readonly" /><br/>
	    <% String select=(String) request.getAttribute("cartas");
	       out.println(select); %>
	    <br/>
	    <input placeholder="cantidad" type="text" name="cantidad" value="<%=m.getCantidad()%>" /><br/>
            <input type="hidden" name="pk" value="<%=m.getId()%>" />
            <input type="submit" value="editar" />
        </form>
        <h3><a href=".">Inicio</a></h3>
    </body>
</html>