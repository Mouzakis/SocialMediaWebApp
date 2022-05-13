/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import gr.csd.uoc.cs359.winter2018.warp.db.PostDB;
import gr.csd.uoc.cs359.winter2018.warp.model.Post;
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
public class DeletePost extends HttpServlet {

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
        String username = request.getParameter("username");
        PostDB.deletePost(Integer.parseInt(request.getParameter("id")));
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<h3>Deleted Post " + Integer.parseInt(request.getParameter("id")) + " succesfully</h3>");
            out.println("<div class='button-container'>");
            out.println("<button id='logout'>Logout</button>");
            out.println("<button id='memberlist'>Members List</button>");
            out.println("<button id='review'>Review Data</button>");
            out.println("<button id='main-posts'>Posts Interface</button>");
            out.println("<button id='create-post'>Create a Post</button>"); 
            out.println("</div>");
           
            List<Post> posts = PostDB.getTop10RecentPostsOfUser(username);
            for (Post post : posts) {
                out.println("<p> Post: " + post.getPostID() + "  ");
                out.println("posted at :" + post.getCreatedAt() + "</p>");
                out.println("<p>Comments : " + post.getComment() + "</p>");
                out.println("<button  class=\"view_post\" id='delete' onclick='deletepost(" + post.getPostID()+ "," +"\""+request.getParameter("username")+"\"" +")'>Delete Post</button>");
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
            Logger.getLogger(DeletePost.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeletePost.class.getName()).log(Level.SEVERE, null, ex);
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
