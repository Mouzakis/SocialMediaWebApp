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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Petros
 */
public class MembersList extends HttpServlet {

    List<User> users;

    public MembersList() throws ClassNotFoundException {
        this.users = UserDB.getUsers();
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<div class='button-container'>");
            out.println("<button id='logout'>Logout</button>");
            out.println("<button id='review'>Review Data</button>");
            out.println("<button id='main-posts'>Posts Interface</button>");
            out.println("<button id='create-post'>Create a Post</button>");
            out.println("<button id='delete-post'>Delete a Post</button>");
            out.println("</div>");
            out.println("<table class='members-table'>");
            out.println("<tr>");
            out.println("<th>Username</th>");
            out.println("<th>Email</th>");
            out.println("<th>Password</th>");
            out.println("<th>Name</th>");
            out.println("<th>Surname</th>");
            out.println("<th>Birth Date</th>");
            out.println("<th>Registered Since</th>");
            out.println("<th>Gender</th>");
            out.println("<th>Address</th>");
            out.println("<th>Country</th>");
            out.println("<th>Town</th>");
            out.println("<th>Occupation</th>");
            out.println("<th>Interests</th>");
            out.println("<th>Information</th>");
            out.println("</tr>");
            for (int i = 0; i < users.size(); i++) {
                out.println("<tr>");
                out.println("<th>" + users.get(i).getUserName() + "</th>");
                out.println("<td>" + users.get(i).getEmail() + "</td>");
                out.println("<td>" + users.get(i).getPassword() + "</td>");
                out.println("<td>" + users.get(i).getFirstName() + "</td>");
                out.println("<td>" + users.get(i).getLastName() + "</td>");
                out.println("<td>" + users.get(i).getBirthDate() + "</td>");
                out.println("<td>" + users.get(i).getRegisteredSince() + "</td>");
                out.println("<td>" + users.get(i).getGender() + "</td>");
                out.println("<td>" + users.get(i).getAddress() + "</td>");
                out.println("<td>" + users.get(i).getCountry() + "</td>");
                out.println("<td>" + users.get(i).getTown() + "</td>");
                out.println("<td>" + users.get(i).getOccupation() + "</td>");
                out.println("<td>" + users.get(i).getInterests() + "</td>");
                out.println("<td>" + users.get(i).getInfo() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
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
