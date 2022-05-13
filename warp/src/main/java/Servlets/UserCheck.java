/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import gr.csd.uoc.cs359.winter2018.warp.db.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Petros
 */
public class UserCheck extends HttpServlet {
    UserDB userdb;

    @Override
    public void init() {
        userdb = new UserDB();
    }
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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        boolean valid = false;
        if (request.getParameter("mode").equals("username")) {
            if (userdb.checkValidUserName(request.getParameter("username"))) {
                valid = true;
            }
        } else {
            if (userdb.checkValidEmail(request.getParameter("email"))) {
                valid = true;
            }
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (request.getParameter("mode").equals("username")) {
                if (valid) {
                    out.println("<label for=\"username\" lang=\"en\">*Username:</label>"
                            + "<input type=\"text\" id=\"username\" name=\"username\" pattern=\"[A-Za-z]{8,}\" title=\"8 or more latin characters\" onchange=\"userCheck('username')\" value=\"" + request.getParameter("username") + "\" required>");
                } else {
                    out.println("<label for=\"username\" lang=\"en\">*Username:</label>"
                            + "<input type=\"text\" id=\"username\" name=\"username\" pattern=\"[A-Za-z]{8,}\" title=\"8 or more latin characters\" onchange=\"userCheck('username')\" required>");
                }
            } else {
                if (valid) {
                    out.println("<label for=\"email\" lang=\"en\">*E-mail:</label>\n"
                            + "<input id=\"email\" type=\"email\" pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$\" name=\"email\" title=\"'text'@'text'.'2-3 characters'\" onchange=\"userCheck('email')\" value=\"" + request.getParameter("email") + "\" required>");
                } else {
                    out.println("<label for=\"email\" lang=\"en\">*E-mail:</label>\n"
                            + "<input id=\"email\" type=\"email\" pattern=\"[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$\" name=\"email\" title=\"'text'@'text'.'2-3 characters'\" onchange=\"userCheck('email')\" required>");
                }
            }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserCheck.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserCheck.class.getName()).log(Level.SEVERE, null, ex);
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
