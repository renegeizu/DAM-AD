<%@page import="hibernate.Mesa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar-Mesas</title>
    </head>
    <body>
        <h1>Editar Mesa</h1>
        <form action="controlador" method="POST">
            <% Mesa m = (Mesa) request.getAttribute("mesa"); %>
            <input type="hidden" name="op" value="opEditarMesa" />
            <input type="hidden" name="id" value="<%=m.getId()%>" /><br/>
            <input placeholder="mesa" type="text" name="mesa" value="<%=m.getNombre()%>" /><br/>
            <input type="hidden" name="pk" value="<%=m.getId()%>" />
            <input type="submit" value="editar" />
        </form>
        <h3><a href=".">Inicio</a></h3>
    </body>
</html>