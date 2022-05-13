/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import gr.csd.uoc.cs359.winter2018.warp.db.RatingDB;
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
public class ChangeRate extends HttpServlet {

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
        int PostID = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        int rating = Integer.parseInt(request.getParameter("rating"));
        try (PrintWriter out = response.getWriter()) {
          for(Rating rate : RatingDB.getRatings()){
              if(rate.getPostID() == PostID && username.equals(rate.getUserName())){
                  rate.setPostID(PostID);
                  rate.setUserName(username);
                  rate.setRate(rating);
                  RatingDB.updateRating(rate);
                  
                  out.println("<div class=\"button-container\" id=\"rate_post\">"
                    +"Your Rating :"+ rate.getRate()
                    + "<button class=\"view_post\" onclick=\"change_rate("+PostID+");\">Change Rating</button>"
                            + "</div>");
                  break;
              }
          }  
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChangeRate.class.getName()).log(Level.SEVERE, null, ex);
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
