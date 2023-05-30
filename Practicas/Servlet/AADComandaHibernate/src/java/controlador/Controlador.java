package controlador;

    import hibernate.Carta;
    import hibernate.Detallepedido;
    import hibernate.HibernateUtil;
    import hibernate.Mesa;
    import hibernate.Pedido;
    import java.io.IOException;
    import java.math.BigDecimal;
    import java.util.Calendar;
    import java.util.Date;
    import java.util.List;
    import javax.servlet.RequestDispatcher;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import org.hibernate.Hibernate;
    import org.hibernate.Query;	
    import org.hibernate.Session;
    import org.hibernate.exception.ConstraintViolationException;

@WebServlet(name = "Controlador", urlPatterns = {"/controlador"})
public class Controlador extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String op = request.getParameter("op");
        RespuestaControlador rc = doOp(op, request);
        if (rc.isForward()) {
            RequestDispatcher rd = request.getRequestDispatcher(rc.getDestino());
            rd.forward(request, response);
        } else {
            response.sendRedirect(rc.getDestino());
        }
    }
    private RespuestaControlador doOp(String op, HttpServletRequest request) {
        RespuestaControlador rc = new RespuestaControlador();
        if (op == null) {
	//Punto de Inicio
	//Controlador de Mesa
        } else if (op.equals("vistaMesa")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/mesa/vista.jsp");
            Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Mesa";
	    Query q = session.createQuery(hql);
	    List<Mesa> mesas = q.list();
	    Hibernate.initialize(mesas);
	    request.setAttribute("mesas", mesas);
	    session.getTransaction().commit();
	    session.flush();
	    session.close();
        } else if (op.equals("vistaInsertarMesa")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/mesa/insertar.jsp");
        } else if (op.equals("opInsertarMesa")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/mesa/vista.jsp");
	    String mesa=request.getParameter("mesa");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Mesa m=new Mesa(mesa);
	    long r=0;
	    try{
		session.save(m);
		r=m.getId();
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaMesa&insert=&r="+r);
	    }
	    session.close();
        } else if (op.equals("opBorrarMesa")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/mesa/vista.jsp");
	    String id=request.getParameter("id");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Mesa m = (Mesa) session.load(Mesa.class, Integer.parseInt(id));
	    long r=1;
	    try{
		session.delete(m);
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaMesa&delete=&r="+r);
	    }
	    session.close();
        } else if (op.equals("vistaEditarMesa")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/mesa/editar.jsp");
	    String id = request.getParameter("id");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Mesa where id = ?";
	    Query q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(id));
	    Mesa m=(Mesa) q.uniqueResult();
	    Hibernate.initialize(m);
	    request.setAttribute("mesa", m);
        } else if (op.equals("opEditarMesa")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/mesa/vista.jsp");
	    String id=request.getParameter("id");
	    String mesa=request.getParameter("mesa");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Mesa m = (Mesa) session.load(Mesa.class, Integer.parseInt(id));
	    m.setNombre(mesa);
	    long r=0;
	    try{
		session.update(m);
		r=m.getId();
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaMesa&update=&r="+r);
	    }
	    session.close();
	//Fin de Controlador de Mesa
	//Punto de Inicio
	//Controlador de Carta
        } else if (op.equals("vistaCarta")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/carta/vista.jsp");
            Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Carta";
	    Query q = session.createQuery(hql);
	    List<Carta> cartas = q.list();
	    Hibernate.initialize(cartas);
	    request.setAttribute("cartas", cartas);
	    session.getTransaction().commit();
	    session.flush();
	    session.close();
        } else if (op.equals("vistaInsertarCarta")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/carta/insertar.jsp");
        } else if (op.equals("opInsertarCarta")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/carta/vista.jsp");
	    String carta = request.getParameter("carta");
	    String precio = request.getParameter("precio");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Carta c = new Carta(carta, new BigDecimal(precio));
	    long r=0;
	    try{
		session.save(c);
		r=c.getId();
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaCarta&insert=&r=" + r);
	    }
	    session.close();
        } else if (op.equals("opBorrarCarta")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/carta/vista.jsp");
	    String id=request.getParameter("id");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Carta c = (Carta) session.load(Carta.class, Integer.parseInt(id));
	    long r=1;
	    try{
		session.delete(c);
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaCarta&delete=&r="+r);
	    }
	    session.close();
        } else if (op.equals("vistaEditarCarta")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/carta/editar.jsp");
	    String id = request.getParameter("id");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Carta where id = ?";
	    Query q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(id));
	    Carta c=(Carta) q.uniqueResult();
	    Hibernate.initialize(c);
	    request.setAttribute("carta", c);
        } else if (op.equals("opEditarCarta")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/carta/vista.jsp");
	    String id = request.getParameter("id");
	    String carta = request.getParameter("carta");
	    String precio = request.getParameter("precio");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Carta c = (Carta) session.load(Carta.class, Integer.parseInt(id));
	    c.setNombre(carta);
	    c.setPrecio(new BigDecimal(precio));
	    long r=0;
	    try{
		session.update(c);
		r=c.getId();
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaCarta&update=&r="+r);
	    }
	    session.close();
	//Fin de Controlador de Carta
	//Punto de Inicio
	//Controlador de Pedido
        } else if (op.equals("vistaPedido")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/pedido/vista.jsp");
            Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Pedido";
	    Query q = session.createQuery(hql);
	    List<Pedido> pedidos = q.list();
	    Hibernate.initialize(pedidos);
	    for(int cont=0; cont<pedidos.size(); cont++){
		Hibernate.initialize(pedidos.get(cont).getMesa());
	    }
	    request.setAttribute("pedidos", pedidos);
	    session.getTransaction().commit();
	    session.flush();
	    session.close();
        } else if (op.equals("vistaInsertarPedido")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/pedido/insertar.jsp");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Mesa";
	    Query q = session.createQuery(hql);
	    List<Mesa> mesas = q.list();
	    Hibernate.initialize(mesas);
	    Mesa m;
	    String todo = "<select name=\"idMesa\" id=\"idMesa\" >"
                + "<option value=\"\">&nbsp;</option>";
	    for(int cont=0; cont<mesas.size(); cont++){
		m = mesas.get(cont);
		todo += "<option value=\"" + m.getId() + "\" " + ">"
			+ m.getNombre() + " " + "</option>";
	    }
	    todo+="</select>";
	    request.setAttribute("mesas", todo);
	    session.getTransaction().commit();
	    session.flush();
	    session.close();
        } else if (op.equals("opInsertarPedido")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/pedido/vista.jsp");
	    String idMesa = request.getParameter("idMesa");
	    String cerrado = request.getParameter("cerrado");
	    Date fechaHora = Calendar.getInstance().getTime();
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Mesa where id = ?";
	    Query q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(idMesa));
	    Mesa m=(Mesa) q.uniqueResult();
	    Pedido p = new Pedido(m, fechaHora, Byte.parseByte(cerrado));
	    long r=0;
	    try{
		session.save(p);
		r=p.getId();
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaPedido&insert=&r=" + r);
	    }
	    session.close();
        } else if (op.equals("opBorrarPedido")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/pedido/vista.jsp");
	    String id=request.getParameter("id");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Pedido p = (Pedido) session.load(Pedido.class, Integer.parseInt(id));
	    long r=1;
	    try{
		session.delete(p);
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaPedido&delete=&r="+r);
	    }
	    session.close();
        } else if (op.equals("vistaEditarPedido")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/pedido/editar.jsp");
	    String id = request.getParameter("id");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Pedido where id = ?";
	    Query q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(id));
	    Pedido p=(Pedido) q.uniqueResult();
	    Hibernate.initialize(p);
	    Hibernate.initialize(p.getMesa());
	    request.setAttribute("pedido", p);
	    hql = "from Mesa";
	    q = session.createQuery(hql);
	    List<Mesa> mesas = q.list();
	    Hibernate.initialize(mesas);
	    Mesa m;
	    String selected;
	    String todo = "<select name=\"idMesa\" id=\"idMesa\" >"
                + "<option value=\"\">&nbsp;</option>";
	    for(int cont=0; cont<mesas.size(); cont++){
		selected = "";
		if (p.getMesa().getId()==mesas.get(cont).getId()) {
		    selected = "selected=\"selected\"";
		}
		m = mesas.get(cont);
		todo += "<option value=\"" + m.getId() + "\" " + selected + ">"
			+ m.getNombre() + " " + "</option>";
	    }
	    todo+="</select>";
	    request.setAttribute("mesas", todo);
	    session.getTransaction().commit();
	    session.flush();
	    session.close();
        } else if (op.equals("opEditarPedido")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/pedido/vista.jsp");
	    String id = request.getParameter("id");
	    String idMesa = request.getParameter("idMesa");
	    String cerrado = request.getParameter("cerrado");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Mesa where id = ?";
	    Query q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(idMesa));
	    Mesa m=(Mesa) q.uniqueResult();
	    Pedido p = (Pedido) session.load(Pedido.class, Integer.parseInt(id));
	    p.setCerrado(Byte.parseByte(cerrado));
	    p.setFechahora(Calendar.getInstance().getTime());
	    p.setMesa(m);
	    long r=0;
	    try{
		session.update(p);
		r=p.getId();
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaPedido&update=&r="+r);
	    }
	    session.close();
	//Fin de Controlador de Pedido
	//Punto de Inicio
	//Controlador de Detalle
	} else if (op.equals("vistaDetalle")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/detalle/vista.jsp");
	    String id = request.getParameter("id");
            Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Detallepedido where idPedido = ?";
	    Query q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(id));
	    List<Detallepedido> detalles = q.list();
	    Hibernate.initialize(detalles);
	    for(int cont=0; cont<detalles.size(); cont++){
		Hibernate.initialize(detalles.get(cont).getCarta());
		Hibernate.initialize(detalles.get(cont).getPedido());
	    }
	    request.setAttribute("detalles", detalles);
	    Hibernate.initialize(id);
	    request.setAttribute("id", id);
	    session.getTransaction().commit();
	    session.flush();
	    session.close();
        } else if (op.equals("vistaInsertarDetalle")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/detalle/insertar.jsp");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Carta";
	    Query q = session.createQuery(hql);
	    List<Carta> cartas = q.list();
	    Hibernate.initialize(cartas);
	    Carta c;
	    String todo = "<select name=\"idCarta\" id=\"idCarta\" >"
                + "<option value=\"\">&nbsp;</option>";
	    for(int cont=0; cont<cartas.size(); cont++){
		c = cartas.get(cont);
		todo += "<option value=\"" + c.getId() + "\" " + ">"
			+ c.getNombre() + " " + "</option>";
	    }
	    todo+="</select>";
	    request.setAttribute("cartas", todo);
	    String id=request.getParameter("id");
	    Hibernate.initialize(id);
	    request.setAttribute("id", id);
	    session.getTransaction().commit();
	    session.flush();
	    session.close();
        } else if (op.equals("opInsertarDetalle")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/detalle/vista.jsp");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String idPedido = request.getParameter("idPedido");
	    String idCarta = request.getParameter("idCarta");
	    String cantidad = request.getParameter("cantidad");
	    String hql = "from Carta where id = ?";
	    Query q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(idCarta));
	    Carta c=(Carta) q.uniqueResult();
	    hql = "from Pedido where id = ?";
	    q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(idPedido));
	    Pedido p=(Pedido) q.uniqueResult();
	    Detallepedido d = new Detallepedido(c, p, Integer.parseInt(cantidad), c.getPrecio());
	    long r=0;
	    try{
		session.save(d);
		r=d.getId();
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaDetalle&id="+p.getId()+"&insert=&r=" + r);
	    }
	    session.close();
        } else if (op.equals("opBorrarDetalle")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/detalle/vista.jsp");
	    String id=request.getParameter("id");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    Detallepedido dp = (Detallepedido) session.load(Detallepedido.class, Integer.parseInt(id));
	    long r=1;
	    try{
		session.delete(dp);
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaDetalle&id="+dp.getPedido().getId()+"&delete=&r="+r);
	    }
	    session.close();
        } else if (op.equals("vistaEditarDetalle")) {
	    rc.setForward(true);
	    rc.setDestino("WEB-INF/detalle/editar.jsp");
	    String id = request.getParameter("id");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Detallepedido where id = ?";
	    Query q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(id));
	    Detallepedido dp=(Detallepedido) q.uniqueResult();
	    Hibernate.initialize(dp);
	    Hibernate.initialize(dp.getCarta());
	    Hibernate.initialize(dp.getPedido());
	    request.setAttribute("detalle", dp);
	    hql = "from Carta";
	    q = session.createQuery(hql);
	    List<Carta> cartas = q.list();
	    Hibernate.initialize(cartas);
	    Carta c;
	    String selected;
	    String todo = "<select name=\"idCarta\" id=\"idCarta\" >"
                + "<option value=\"\">&nbsp;</option>";
	    for(int cont=0; cont<cartas.size(); cont++){
		selected = "";
		if (dp.getCarta().getId()==cartas.get(cont).getId()) {
		    selected = "selected=\"selected\"";
		}
		c=cartas.get(cont);
		todo += "<option value=\"" + c.getId() + "\" " + selected + ">"
			+ c.getNombre() + " " + "</option>";
	    }
	    todo+="</select>";
	    request.setAttribute("cartas", todo);
	    session.getTransaction().commit();
	    session.flush();
	    session.close();
        } else if (op.equals("opEditarDetalle")) {
	    rc.setForward(false);
	    rc.setDestino("WEB-INF/detalle/vista.jsp");
	    String id = request.getParameter("id");
	    String idPedido = request.getParameter("idPedido");
	    String idCarta = request.getParameter("idCarta");
	    String cantidad = request.getParameter("cantidad");
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
	    String hql = "from Carta where id = ?";
	    Query q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(idCarta));
	    Carta c=(Carta) q.uniqueResult();
	    hql = "from Pedido where id = ?";
	    q = session.createQuery(hql);
	    q.setInteger(0, Integer.parseInt(idPedido));
	    Pedido p=(Pedido) q.uniqueResult();
	    Detallepedido d = (Detallepedido) session.load(Detallepedido.class, Integer.parseInt(id));
	    d.setCarta(c);
	    d.setPedido(p);
	    d.setCantidad(Integer.parseInt(cantidad));
	    d.setPrecio(c.getPrecio());
	    long r=0;
	    try{
		session.update(p);
		r=p.getId();
		session.getTransaction().commit();
		session.flush();
	    }catch(ConstraintViolationException e){
		r=-1;
		session.getTransaction().rollback();
	    }finally{
		rc.setDestino("controlador?op=vistaDetalle&id="+d.getPedido().getId()+"&update=&r="+r);
	    }
	    session.close();
	//Fin de Controlador de Detalle
	}else{
	    //Si op es null, Vuelves al Index
            rc = new RespuestaControlador();
            rc.setForward(false);
            rc.setDestino("index.jsp");
        }
        return rc;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
