<%@page import="com.izv.util.Util"%>
<%@page import="hibernate.Detallepedido"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Detallepedido> detalle=(List) request.getAttribute("detalles");
   long id=Long.parseLong(request.getAttribute("id").toString()); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista-DetallePedidos</title>
        <script type="text/javascript" src="scripts/script.js"></script>
    </head>
    <body>
        <h1>DetallePedido</h1>
        <% String mensaje = Util.getMensaje(request);
           out.println(mensaje); %>
        <h3><a href="controlador?op=vistaInsertarDetalle&id=<%=id%>">Agregar DetallePedido</a></h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
		    <th>IdPedido</th>
		    <th>Carta</th>
		    <th>Cantidad</th>
                    <th>Precio</th>
                    <th colspan="2">Operaciones</th>
                </tr>
            </thead>
            <tbody>
                <% for(Detallepedido d : detalle) {
		    float precio=Float.parseFloat(d.getCarta().getPrecio().toString()); %>
                    <tr>
                        <td><%=d.getId()%></td>
                        <td><%=d.getPedido().getId() %></td>
			<td><%=d.getCarta().getNombre()%></td>
			<td><%=d.getCantidad()%></td>
			<td><%=precio*d.getCantidad()%></td>
                        <td><a href="?op=vistaEditarDetalle&id=<%=d.getId()%>">Editar</a></td>
			<td><a href="?op=opBorrarDetalle&id=<%=d.getId()%>">Borrar</a></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <h3><a href=".">Inicio</a></h3>
    </body>
</html>