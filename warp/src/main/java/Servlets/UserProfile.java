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
public class UserProfile extends HttpServlet {
    User user;
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
        user = UserDB.getUser(request.getParameter("username"));
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<div class='button-container'>");
            out.println("<button id='logout'>Logout</button>");
            out.println("<button id='memberlist'>Members List</button>");
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
            out.println("<tr>");
            out.println("<th>" + user.getUserName() + "</th>");
            out.println("<td>" + user.getEmail() + "</td>");
            out.println("<td>" + user.getPassword() + "</td>");
            out.println("<td>" + user.getFirstName() + "</td>");
            out.println("<td>" + user.getLastName() + "</td>");
            out.println("<td>" + user.getBirthDate() + "</td>");
            out.println("<td>" + user.getRegisteredSince() + "</td>");
            out.println("<td>" + user.getGender() + "</td>");
            out.println("<td>" + user.getAddress() + "</td>");
            out.println("<td>" + user.getCountry() + "</td>");
            out.println("<td>" + user.getTown() + "</td>");
            out.println("<td>" + user.getOccupation() + "</td>");
            out.println("<td>" + user.getInterests() + "</td>");
            out.println("<td>" + user.getInfo() + "</td>");
            out.println("</tr>");
            out.println("</table>");
            //out.println(PostDB.getTop10RecentPostsOfUser(user.getUserName()));
            
            List<Post> posts = PostDB.getTop10RecentPostsOfUser(user.getUserName());
            for(Post post : posts){
                out.println("<p> Post: " + post.getPostID() + "  ");
                out.println("posted at :" + post.getCreatedAt() + "</p>");
                out.println("<p>Comments : "+post.getComment()+"</p>");
                if(!(post.getImageURL().equals(""))){
                    out.println("<img src="+post.getImageURL()+">");
                }
                if (!(post.getResourceURL().equals(""))) {
                    out.println("<img src="+post.getResourceURL()+">");
                }
                if (!(post.getImageBase64().equals(""))) {
                    out.println("<img src=" + post.getImageBase64() + ">");
                }
                out.println("<button class='auto-align view_post' onclick='view_post(" + post.getPostID() + ")'>Go to Post</button>");
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
            Logger.getLogger(UserProfile.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserProfile.class.getName()).log(Level.SEVERE, null, ex);
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
