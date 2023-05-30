<%@page import="com.izv.util.Util"%>
<%@page import="hibernate.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar-Pedidos</title>
    </head>
    <body>
        <h1>Editar Pedido</h1>
        <form action="controlador" method="POST">
            <% Pedido p = (Pedido) request.getAttribute("pedido"); %>
            <input type="hidden" name="op" value="opEditarPedido" />
            <input type="hidden" name="id" value="<%=p.getId()%>" /><br/>
	    <% String select=(String) request.getAttribute("mesas");
	       out.println(select); %>
	    <br/>
	    <% String[][] valores={{"0", "abierto"}, {"1", "cerrado"}};
	       out.println(Util.select("cerrado", valores, p.getCerrado()+"")); %>
	    <br />
            <input type="hidden" name="pk" value="<%=p.getId()%>" />
            <input type="submit" value="editar" />
        </form>
        <h3><a href=".">Inicio</a></h3>
    </body>
</html>