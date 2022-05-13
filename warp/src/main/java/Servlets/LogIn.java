/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import gr.csd.uoc.cs359.winter2018.warp.db.UserDB;
import gr.csd.uoc.cs359.winter2018.warp.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Petros
 */
public class LogIn extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
            if (session != null) {
                
            }
            else {
            String username = request.getParameter("username");
            UserDB userdb = new UserDB();
            User user = userdb.getUser(username);
            String onpass = user.getPassword();
            String decpass = "";
            for (int i=0; i < onpass.length( ); i++) {
                decpass = decpass + Character.toString((char)(onpass.charAt(i)-1));
            }
            boolean flag = (request.getParameter("password").equals(decpass));
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                if (user == null) {
                    out.println("<div class='container'>");
                    out.println("<div id='login_form'>");
                    out.println("<div class='input-item'>");
                    out.println("<label for='username' lang='en'>*Username:</label>");
                    out.println("<input type='text' id='username' name='username' pattern='[A-Za-z]{8,}' title='8 or more latin characters' required>");
                    out.println("</div>");
                    out.println("<div class='input-item'>");
                    out.println("<label for='pw'>*Κωδικός χρήστη:</label>");
                    out.println("<input type ='password' name='pw' id='pw' pattern='(?=.*\\d)(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+){8,10}' title='From 8 to 10 characters and please include at least 1 latin character, 1 number and 1 symbol!' required>");
                    out.println("</div>");
                    out.println("<button class='submit active' onclick='loginFunc();' type='submit'>Log In</button>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("<h3>Login failed. Account not found</h3>");
                } else if (!(flag)) {
                    out.println("<div class='container'>");
                    out.println("<div id='login_form'>");
                    out.println("<div class='input-item'>");
                    out.println("<label for='username' lang='en'>*Username:</label>");
                    out.println("<input type='text' id='username' name='username' pattern='[A-Za-z]{8,}' title='8 or more latin characters' required>");
                    out.println("</div>");
                    out.println("<div class='input-item'>");
                    out.println("<label for='pw'>*Κωδικός χρήστη:</label>");
                    out.println("<input type ='password' name='pw' id='pw' pattern='(?=.*\\d)(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+){8,10}' title='From 8 to 10 characters and please include at least 1 latin character, 1 number and 1 symbol!' required>");
                    out.println("</div>");
                    out.println("<button class='submit active' onclick='loginFunc();' type='submit'>Log In</button>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("<h3>Login failed. Incorrect password</h3>");
                } else if ((request.getParameter("username").matches("[A-Za-z]{8,}+")) && (request.getParameter("password").matches("(?=.*[0-9])(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+)[-\\S]{8,10}+"))) {
                    Cookie loginCookie = new Cookie("user", request.getParameter("username"));
                    loginCookie.setMaxAge(10*60);
                    response.addCookie(loginCookie);
                    session = request.getSession(true);
                    session.setMaxInactiveInterval(10*60);
                    session.setAttribute("user", request.getParameter("username"));
                    out.println("<h1>Succesfully Logged in</h1>");
                    out.println("<p>What would you like to do?</p>");
                    out.println("<div class='button-container'>");
                    out.println("<button id='logout'>Logout</button>");
                    out.println("<button id='memberlist'>Members List</button>");
                    out.println("<button id='review'>Review Data</button>");
                    out.println("<button id='main-posts'>Posts Interface</button>");
                    out.println("<button id='create-post'>Create a Post</button>");
                    out.println("<button id='delete-post'>My Posts</button>");
                    out.println("<button id='delete-user' onclick=\"delete_user();\">Delele Profile</button>");
                    out.println("</div>");
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
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
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
