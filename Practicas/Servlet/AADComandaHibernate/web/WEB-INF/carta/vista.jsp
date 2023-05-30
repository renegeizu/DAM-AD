<%@page import="com.izv.util.Util"%>
<%@page import="hibernate.Carta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Carta> cartas = (List) request.getAttribute("cartas"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista-Cartas</title>
        <script type="text/javascript" src="scripts/script.js"></script>
    </head>
    <body>
        <h1>Cartas</h1>
	<% String mensaje = Util.getMensaje(request);
           out.println(mensaje); %>
        <h3><a href="controlador?op=vistaInsertarCarta">Agregar Mesas</a></h3>
        <table border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Carta</th>
		    <th>Precio</th>
                    <th colspan="2">Operaciones</th>
                </tr>
            </thead>
            <tbody>
                <% for (Carta c : cartas) { %>
                    <tr>
                        <td><%=c.getId()%></td>
                        <td><%=c.getNombre()%></td>
			<td><%=c.getPrecio()%></td>
                        <td><a href="?op=vistaEditarCarta&id=<%=c.getId()%>">Editar</a></td>
			<td><a href="?op=opBorrarCarta&id=<%=c.getId()%>">Borrar</a></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <h3><a href=".">Inicio</a></h3>
    </body>
</html>