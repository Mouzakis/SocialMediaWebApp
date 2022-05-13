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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Petros
 */
public class UpdateUser extends HttpServlet {
    UserDB userdb;
    User user;

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
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        if ((!(req.getParameter("email").matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$+")))
                || (!(req.getParameter("password").matches("(?=.*[0-9])(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+)[-\\S]{8,10}+")))
                || (!(req.getParameter("first_name").matches("[A-Za-z]{3,15}+")))
                || (!(req.getParameter("last_name").matches("[A-Za-z]{3,15}+")))
                || (!(req.getParameter("birth_date").matches("[0-9]{2}/[0-9]{2}/[0-9]{4}$+")))
                || (!(req.getParameter("occupation").matches("[A-Za-z]{3,15}+")))
                || (!(req.getParameter("town").matches("[A-Za-z]{2,20}+")))
                || (!(req.getParameter("interests").matches("[a-zA-Z0-9\n ]{0,100}+")))
                || (!(req.getParameter("info").matches("[a-zA-Z0-9.:\n ]{0,500}+")))
                || (!(req.getParameter("address").matches("[a-zA-Z0-9 ]{2,20}+")))) {
            res.sendError(400);
            System.out.println((!(req.getParameter("user_name").matches("[A-Za-z]{8,}+"))) + " " + (!(req.getParameter("email").matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$+")))
                    + " " + (!(req.getParameter("password").matches("(?=.*[0-9])(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+)[-\\S]{8,10}+")))
                    + " " + (!(req.getParameter("first_name").matches("[A-Za-z]{3,15}+")))
                    + " " + (!(req.getParameter("last_name").matches("[A-Za-z]{3,15}+")))
                    + " " + (!(req.getParameter("birth_date").matches("[0-9]{2}/[0-9]{2}/[0-9]{4}$+")))
                    + " " + (!(req.getParameter("occupation").matches("[A-Za-z]{3,15}+")))
                    + " " + (!(req.getParameter("town").matches("[A-Za-z]{2,20}+")))
                    + " " + (!(req.getParameter("interests").matches("[a-zA-Z0-9\n ]{0,100}+")))
                    + " " + (!(req.getParameter("info").matches("[a-zA-Z0-9.:\n ]{0,500}+")))
                    + " " + (!(req.getParameter("address").matches("[A-Za-z]{2,20}+"))));
        } else {
            String pass = "";
            String oldp = req.getParameter("password");
            for (int i=0; i < oldp.length(  ); i++) {
                pass = pass + Character.toString((char)(oldp.charAt(i)+1));
            }
            System.out.println(pass);
            String ne = "";
            for (int i=0; i<pass.length( ); i++) {
                ne = ne + Character.toString(((char)(pass.charAt(i)-1)));
            }
            System.out.println(ne);
            user = new User(req.getParameter("user_name"), req.getParameter("email"), pass,
                    req.getParameter("first_name"), req.getParameter("last_name"), req.getParameter("birth_date"), req.getParameter("occupation"),
                    req.getParameter("country"), req.getParameter("town"));
            user.setAddress(req.getParameter("address"));
            user.setInterests(req.getParameter("interests"));
            user.setInfo(req.getParameter("info"));
            user.setGender(req.getParameter("gender"));

            try (PrintWriter out = res.getWriter()) {
                if (true) {
                    userdb.updateUser(user);
                    out.println("<h1>Succesfully Updated User</h1>");
                    out.println("<p>What else would you like to do?</p>");
                    out.println("<div class='button-container'>");
                    out.println("<button id='logout'>Logout</button>");
                    out.println("<button id='memberlist'>Peek at members</button>");
                    out.println("<button id='create-post'>Create Post</button>");
                    out.println("<button id='delete-post'>Delete a Post</button>");
                    out.println("<button id='review'>Review Data</button>");
                    out.println("</div>");
                } else {
                    out.println("<h1>Incorrect username entered</h1>");
                    out.println("<p>Do something else?</p>");
                    out.println("<div class='button-container'>");
                    out.println("<button id='logout'>Logout</button>");
                    out.println("<button id='memberlist'>Peek at members</button>");
                    out.println("<button id='review'>Review Data</button>");
                    out.println("<button id='create-post'>Create Post</button>");
                    out.println("<button id='delete-post'>Delete a Post</button>");
                    out.println("</div>");
                }
                /* TODO output your page here. You may use following sample code. */

                System.out.println("Username is : " + user.getUserName());
            } catch (ClassNotFoundException e) {
                System.out.println("\nClass not found @ Registration\n");
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
