/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.ConnectionPoolDataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ICT02
 */
@WebServlet(name = "Conexion", urlPatterns = {"/Conexion"})
public class Conexion extends HttpServlet {
    @Resource(name ="jdbc/Inv")
    private DataSource ds; 
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Conexion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Conexion a Tabla de Usuarios " + "</h1>");
            // uso del try y catch
            try (Connection cnx = InitialContext.<DataSource>doLookup("jdbc/Inv").getConnection() 
                 //Connection cnx =ds.getConnection()
                    )
                {
                    String sql = "select * from inventario.usuarios";
                    Statement stmt =cnx.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                     out.println("<table border='2'>");
                    while (rs.next())
                    {
                        String nombres  = rs.getString("nombres");
                        String apellidos = rs.getString("apellidos");
                        String alias    = rs.getString("alias");
                      
                        
                       
                        out.println("<tr>");
                        
                        out.println("<td>"+nombres+" "+apellidos+"</td>"); 
                        out.println("<td>"+alias+"</td>");
                        
                        out.println("</tr>");
                        
                        
                    }
                    
                    out.println("</table>");
                }catch (NamingException |SQLException ex)
                    {
                        System.out.println("Error :"+ex.getMessage());
                        out.println(ex.getMessage());
                    }
                    
            out.println("</body>");
            out.println("</html>");
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

}
