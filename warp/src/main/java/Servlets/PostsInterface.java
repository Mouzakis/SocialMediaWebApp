/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import gr.csd.uoc.cs359.winter2018.warp.db.PostDB;
import gr.csd.uoc.cs359.winter2018.warp.db.UserDB;
import gr.csd.uoc.cs359.winter2018.warp.model.Post;
import gr.csd.uoc.cs359.winter2018.warp.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class PostsInterface extends HttpServlet {
    List<User> users;

    public PostsInterface() throws ClassNotFoundException {
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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //System.out.println("HOORAY");
            out.println("<div class='button-container'>");
            out.println("<button id='logout'>Logout</button>");
            out.println("<button id='memberlist'>Members List</button>");
            out.println("<button id='review'>Review Data</button>");
            out.println("<button id='create-post'>Create Post</button>");
            out.println("<button id='delete-post'>My Posts</button>");
            out.println("</div>");
            
            //out.println(PostDB.getTop10RecentPosts());
            for (Post top10RecentPost : PostDB.getTop10RecentPosts()) {
                out.println("<p><strong id=\"profiles\">" +top10RecentPost.getUserName()+ "</strong>");
                out.println("posted at :" + top10RecentPost.getCreatedAt() + "</p><button class=\"view_post\" onclick=\"view_post(" + top10RecentPost.getPostID() + ")\">View Post</button>");
                //out.println("<button class=\"view_post\" onclick=\"rate_post("+top10RecentPost.getPostID()+");\">Rate</button>");
                
                
                //out.println(top10RecentPost.toString());
                
            }
            out.println("<h3>Visit the profiles of other users</h3>");
            out.println("<div class='username-list'>");
            for (int i = 0; i < users.size(); i++) {
                
                out.println("<button onclick=\"gotouser(\'" + users.get(i).getUserName() + "\')\" id=\'" + users.get(i).getUserName() + "\'>" + users.get(i).getUserName() + "</button>");
            }
            out.println("</div>");
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
            Logger.getLogger(PostsInterface.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostsInterface.class.getName()).log(Level.SEVERE, null, ex);
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
