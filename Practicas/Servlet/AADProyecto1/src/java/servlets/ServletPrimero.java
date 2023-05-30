package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "servletprimero", urlPatterns = {"/servletprimero"})
public class ServletPrimero extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	//Manda una Cabecera
	//Significa que mandara una web html codificada en UTF8
	response.setContentType("text/html;charset=UTF-8");
	//Obtiene el Objeto Out, donde puedo escribir para generar la 
	//respuesta html
	PrintWriter out = response.getWriter();
	try {
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Servlet ServletPrimero</title>");	    
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>Servlet ServletPrimero at " + request.getContextPath() + "</h1>");
	    //Obtener parametros que llegan
	    String parametro=request.getParameter("login");
	    if(parametro==null){
		out.println("<h1>Sin Parametro</h1>");
	    }else{
		out.println("<h1>Con Parametro: "+parametro+"</h1>");
	    }
	    out.println("</body>");
	    out.println("</html>");
	} finally {
	    out.close();
	}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
	return "Short description";
    }

}
