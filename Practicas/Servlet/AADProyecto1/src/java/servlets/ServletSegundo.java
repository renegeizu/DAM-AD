package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletSegundo extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	try {
	    out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Servlet ServletSegundo</title>");	    
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>Servlet ServletSegundo at " + request.getContextPath() + "</h1>");
	    //En que carpeta se esta ejecutando el servlet
	    out.println("<h1>"+request.getMethod()+"</h1>");
	    //Devuelve si ha llegado por GET o por POST
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
