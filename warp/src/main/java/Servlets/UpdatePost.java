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
 * @author ioann
 */
public class UpdatePost extends HttpServlet {

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
        String comment = request.getParameter("comment");
        String resource = request.getParameter("resource");
        String img = request.getParameter("img");
        String base64 = request.getParameter("base64");
        String lon = request.getParameter("long");
        String lat = request.getParameter("lat");
        Integer PostID = Integer.valueOf((request.getParameter("PostID")));
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           Post post = PostDB.getPost(PostID);
           if(!("".equals(comment))){
               post.setComment(comment);
           }
           if(!("".equals(img))){
               post.setImageURL(img);
           }
           if(!("".equals(resource))){
               post.setResourceURL(resource);
           }
           if(!("".equals(base64))){
              post.setImageBase64(base64);
           }
           if(!("".equals(lat))){
               post.setLatitude(lat);
           }
           if(!("".equals(lon))){
               post.setLongitude(lon);
           }
           PostDB.updatePost(post);
           out.print("<strong>Post Updated Successfully !!</strong>");
           out.print(comment);
           out.println("<p>What would you like to do?</p>");
                    out.println("<div class='button-container'>");
                    out.println("<button id='logout'>Logout</button>");
                    out.println("<button id='memberlist'>Members List</button>");
                    out.println("<button id='review'>Review Data</button>");
                    out.println("<button id='main-posts'>Posts Interface</button>");
                    out.println("<button id='create-post'>Create a Post</button>");
                    out.println("<button id='delete-post'>My Posts</button>");
                    out.println("</div>");
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdatePost.class.getName()).log(Level.SEVERE, null, ex);
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
