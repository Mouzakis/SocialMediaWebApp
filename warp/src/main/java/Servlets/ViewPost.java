/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import gr.csd.uoc.cs359.winter2018.warp.db.CommentDB;
import gr.csd.uoc.cs359.winter2018.warp.db.PostDB;
import gr.csd.uoc.cs359.winter2018.warp.db.RatingDB;
import gr.csd.uoc.cs359.winter2018.warp.model.Comment;
import gr.csd.uoc.cs359.winter2018.warp.model.Post;
import gr.csd.uoc.cs359.winter2018.warp.model.Rating;
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
public class ViewPost extends HttpServlet {

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
        
        Integer PostID = Integer.valueOf((request.getParameter("PostID")));
        String username = request.getParameter("username");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Post post = PostDB.getPost(PostID);
            out.println("<div class='button-container'>");
            out.println("<button id='logout'>Logout</button>");
            out.println("<button id='memberlist'>Members List</button>");
            out.println("<button id='review'>Review Data</button>");
            out.println("<button id='main-posts'>Posts Interface</button>");
            out.println("<button id='create-post'>Create a Post</button>");
            out.println("<button id='delete-post'>My Posts</button>");
            out.println("</div>");
            out.println("<p><strong id=\"profiles\">" +post.getUserName()+ "</strong>");
            out.println("posted at :" + post.getCreatedAt() + "</p>");
            out.println("<p>Comments : "+post.getComment()+"</p>");
            if (!(post.getImageURL().equals(""))) {
                out.println("<img src=" + post.getImageURL() + ">");
            }
            if (!(post.getImageURL().equals(""))) {
                out.println("<img src=" + post.getResourceURL() + ">");
            }
            if (!(post.getImageURL().equals(""))) {
                out.println("<img src=" + post.getImageBase64() + ">");
            }
            if (!((post.getLongitude().equals("")) || (post.getLatitude().equals("")))) {
                out.println("<div id='user_pos'>");
                out.println("<input id='long' value=" + post.getLongitude() + ">");
                out.println("<input id='lat' value=" + post.getLatitude() + "></div>");
                out.println("<div class='post-map' id='map'></div>");
            }
            boolean f = false;
           // out.println(username);
            for(Rating rate : RatingDB.getRatings()){
                if(username.equals(rate.getUserName()) && rate.getPostID() == PostID){
                 out.println("<div class=\"button-container\" id=\"rate_post\">"
                    +"Your Rating :"+ rate.getRate()
                    + "<button class=\"view_post\" onclick=\"change_rate("+PostID+");\">Change Rating</button>"
                            + "</div>");
                 f = true;
                 break;
                }
                //out.println(rate.toString());
                
            }
            if(f == false){
            out.println("<div class=\"button-container\" id=\"rate_post\">"
                    
                    + "<button class=\"view_post\" onclick=\"rate_post("+PostID+");\">Rate Post</button>"
                            + "</div>");
            out.println("<div class=\"button-container\" id=\"hdp\"></div>");
            }
            out.println("<div  class=\"button-container\" id=\"comments\">");
            for(Comment comm : CommentDB.getComments()){
                if(PostID == comm.getPostID()){
                    out.println(comm.getUserName()+ "  commented at  "+comm.getCreatedAt()+"<br>");
                    out.println(comm.getComment()+"<br>");
                }
            }
            
            out.println("<div id=\"my_comment\">"
                    +"<button class=\"view_post\" onclick=\"addcomment("+PostID+");\">Add comment</button>"
                    + "</div>");
            
            out.println("</div>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewPost.class.getName()).log(Level.SEVERE, null, ex);
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
