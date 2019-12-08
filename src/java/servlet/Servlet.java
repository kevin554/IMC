package servlet;

import com.google.gson.Gson;
import dao.FactoryDAO;
import dao.EstadoFisicoDAO;
import dao.UsuariosDAO;
import dto.EstadoFisico;
import dto.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String evento = request.getParameter("evento");
            switch (evento) {
                case "insertar_usuario":
                    insertarUsuario(request, out);
                    break;

                case "seleccionar_todos_los_usuarios":
                    seleccionarTodosUsuarios(out);
                    break;

                case "insertar_estado_fisico":
                    insertarEstadoFisico(request, out);
                    break;
                    
                case "ultima_actualizacion_estado_fisico":
                    ultimaActualizacionEstadoFisico(request, out);
                    break;
                    
                case "todos_fecha_ascendente_estado_fisico":
                    historialEstadoFisicoFechaAscendente(request, out);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

    private void insertarUsuario(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();

        String objString = request.getParameter("obj");
        Usuarios obj = gson.fromJson(objString, Usuarios.class);

        FactoryDAO factory = FactoryDAO.getOrCreate();
        UsuariosDAO dao = factory.newUsuariosDAO();

        try {
            dao.insertar(obj);
            out.print(obj.getCodigoID());
        } catch (Exception ex) {
        }
    }

    private void seleccionarTodosUsuarios(PrintWriter out) {
        FactoryDAO factory = FactoryDAO.getOrCreate();
        UsuariosDAO dao = factory.newUsuariosDAO();

        Gson gson = new Gson();

        try {
            ArrayList<Usuarios> lista = dao.seleccionarTodos();

            out.print(gson.toJson(lista));
        } catch (Exception ex) {

        }
    }

    private void insertarEstadoFisico(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();

        String objString = request.getParameter("obj");
        EstadoFisico obj = gson.fromJson(objString,
                EstadoFisico.class);

        FactoryDAO factory = FactoryDAO.getOrCreate();
        EstadoFisicoDAO dao = factory.newEstadoFisicoDAO();

        try {
            dao.insertar(obj);
            out.print(obj.getCodigoID());
        } catch (Exception ex) {
        }
    }

    private void ultimaActualizacionEstadoFisico(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String usuarioIDStr = request.getParameter("usuario_id");
        int usuarioID = Integer.parseInt(usuarioIDStr);
        
        FactoryDAO factory = FactoryDAO.getOrCreate();
        EstadoFisicoDAO dao = factory.newEstadoFisicoDAO();
        
        try {
            EstadoFisico obj = dao.ultimaActualizacion(usuarioID);
            out.print(gson.toJson(obj));
        } catch (Exception ex) {
            
        }
    }

    private void historialEstadoFisicoFechaAscendente(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String usuarioIDStr = request.getParameter("usuario_id");
        int usuarioID = Integer.parseInt(usuarioIDStr);
        
        FactoryDAO factory = FactoryDAO.getOrCreate();
        EstadoFisicoDAO dao = factory.newEstadoFisicoDAO();
        
        try {
            ArrayList<EstadoFisico> lista = dao.todosPorFechaAscendente(usuarioID);
            out.print(gson.toJson(lista));
        } catch (Exception ex) {
            
        }
    }

}
