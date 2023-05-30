<%@page import="com.izv.util.Util"%>
<%@page import="hibernate.Mesa"%>
<%@page import="java.util.List"%>
<%@page import="hibernate.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Pedido> pedidos=(List) request.getAttribute("pedidos"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista-Pedidos</title>
        <script type="text/javascript" src="scripts/script.js"></script>
    </head>
    <body>
        <h1>Pedidos</h1>
	<% String mensaje = Util.getMensaje(request);
           out.println(mensaje); %>
        <h3><a href="controlador?op=vistaInsertarPedido">Agregar Pedidos</a></h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>FechaHora</th>
		    <th>IdMesa</th>
		    <th>Cerrado</th>
                    <th colspan="3">Operaciones</th>
                </tr>
            </thead>
            <tbody>
                <% for(Pedido p : pedidos) { %>
                    <tr>
                        <td><%=p.getId()%></td>
                        <td><%=p.getFechahora()%></td>
			<td><%=p.getMesa().getNombre()%></td>
		<% if(p.getCerrado()==0){ %>
			<td>Abierto</td>
		<% }else{ %>
			<td>Cerrado</td>
		<% } %>
                        <td><a href="?op=vistaEditarPedido&id=<%=p.getId()%>">Editar</a></td>
                        <td><a class="borrarmesa" href="?op=opBorrarPedido&id=<%=p.getId()%>">Borrar</a></td>
			<td><a href="?op=vistaDetalle&id=<%=p.getId()%>">Detalle</a></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <h3><a href=".">Inicio</a></h3>
    </body>
</html>