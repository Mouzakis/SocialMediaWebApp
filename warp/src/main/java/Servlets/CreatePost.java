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
public class CreatePost extends HttpServlet {

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
        Post post = new Post();
        post.setUserName(request.getParameter("username"));
        post.setComment(request.getParameter("comment"));
        post.setResourceURL(request.getParameter("resource"));
        post.setImageURL(request.getParameter("img"));
        post.setImageBase64(request.getParameter("base64"));
        post.setLatitude(request.getParameter("lat"));
        post.setLongitude(request.getParameter("long"));
        //System.out.println("\n\n" + request.getParameter("base64").replaceAll("\\s+", "") + "\n");
        PostDB.addPost(post);
        try (PrintWriter out = response.getWriter()) {
            out.println("Post uploaded succesfully!");
            out.println("<div class='button-container'>");
            out.println("<button id='logout'>Logout</button>");
            out.println("<button id='memberlist'>Members List</button>");
            out.println("<button id='review'>Review Data</button>");
            out.println("<button id='main-posts'>Posts Interface</button>");
            out.println("<button id='create-post'>Create a Post</button>");
            out.println("<button id='delete-post'>My Posts</button>");
            //out.println("<img src='"+request.getParameter("base64").replaceAll("\\s+","")+"'>");
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
            Logger.getLogger(CreatePost.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CreatePost.class.getName()).log(Level.SEVERE, null, ex);
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
