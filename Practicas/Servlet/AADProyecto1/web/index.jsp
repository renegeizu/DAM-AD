<%-- 
    Document   : index
    Created on : 16-dic-2013, 11:01:25
    Author     : RAFA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
	<%
	    //scriptlets
	    out.println("<h2>Hola</h2>");
	    String op=request.getParameter("op");
	    if(op!=null){
		out.println(op);
	    }
	%>
	<%="hola"%>
    </body>
</html>
