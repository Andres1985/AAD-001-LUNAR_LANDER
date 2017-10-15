/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(urlPatterns = {"/GuardarConf"})
public class GuardarConf extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
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
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dificultad = request.getParameter("dificultad");
            String modeloNave = request.getParameter("modeloNave");
            String modeloLuna = request.getParameter("modeloLuna");

            ServletContext context = getServletContext();
            String fullPath = context.getRealPath("/config.json");

            String filecontent = "{\"dificultad\":\"" + dificultad + "\",\"modeloNave\":\"" + modeloNave + "\",\"modeloLuna\":\""+modeloLuna+"\"}";

            //Create File object
            File f = new File(fullPath);
            FileWriter fw = new FileWriter(f);
            fw.write(filecontent);
            fw.close();

            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"mess\":\"El fichero ha sido guardado correctamente\"}");

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println("{\"error\":\"Ha sido imposible guardar los datos\"}");

        }

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
