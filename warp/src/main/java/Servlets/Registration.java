package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import gr.csd.uoc.cs359.winter2018.warp.model.User;
import gr.csd.uoc.cs359.winter2018.warp.db.UserDB;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Registration extends HttpServlet {

    public UserDB userdb;
    public User user;

    @Override
    public void init() {
        userdb = new UserDB();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException, ClassNotFoundException {

        res.setContentType("text/html;charset=UTF-8");
        if ((!(req.getParameter("user_name").matches("[A-Za-z]{8,}+")))
                || (!(req.getParameter("email").matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$+")))
                || (!(req.getParameter("password").matches("(?=.*[0-9])(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+)[-\\S]{8,10}+")))
                || (!(req.getParameter("first_name").matches("[A-Za-z]{3,15}+")))
                || (!(req.getParameter("last_name").matches("[A-Za-z]{3,15}+")))
                || (!(req.getParameter("birth_date").matches("[0-9]{2}/[0-9]{2}/[0-9]{4}$+")))
                || (!(req.getParameter("occupation").matches("[A-Za-z]{3,15}+")))
                || (!(req.getParameter("town").matches("[A-Za-z]{2,20}+")))
                || (!(req.getParameter("interests").matches("[a-zA-Z0-9 ]{0,100}+")))
                || (!(req.getParameter("info").matches("[a-zA-Z0-9.: ]{0,500}+")))
                || (!(req.getParameter("address").matches("[a-zA-Z0-9 ]{2,20}+")))) {
            res.sendError(400);
            System.out.println((!(req.getParameter("user_name").matches("[A-Za-z]{8,}+"))) + " " + (!(req.getParameter("email").matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$+")))
                    + " " + (!(req.getParameter("password").matches("(?=.*[0-9])(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+)[-\\S]{8,10}+")))
                    + " " + (!(req.getParameter("first_name").matches("[A-Za-z]{3,15}+")))
                    + " " + (!(req.getParameter("last_name").matches("[A-Za-z]{3,15}+")))
                    + " " + (!(req.getParameter("birth_date").matches("[0-9]{2}/[0-9]{2}/[0-9]{4}$+")))
                    + " " + (!(req.getParameter("occupation").matches("[A-Za-z]{3,15}+")))
                    + " " + (!(req.getParameter("town").matches("[A-Za-z]{2,20}+")))
                    + " " + (!(req.getParameter("interests").matches("[A-Za-z]{0,100}+")))
                    + " " + (!(req.getParameter("info").matches("[A-Za-z]{0,500}+")))
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
                if (/*userdb.checkValidUserName(user.getUserName()) && userdb.checkValidEmail(user.getEmail())*/true) {
                    userdb.addUser(user);
                    out.println("<h1>");
                    out.println("Η εγγραφή πραγματοποιήθηκε επιτυχώς!</h1>");
                    out.println("<p>Τα στοιχεία της εγγραφής σας έχουν ως εξής : </p>");
                    out.println("<p>" + user.toString() + "</p>");
                    out.println("<button id='homepage'>Αρχική</button");
                } else {

                    out.println("<h1>Η εγγραφή δεν πραγματοποιήθηκε. To username η το email υπάρχει ήδη!</h1>");
                    out.println("<button id='homepage'>Αρχική</button");
                }
                /* TODO output your page here. You may use following sample code. */

                System.out.println("Username is : " + user.getUserName());
            } catch (ClassNotFoundException e) {
                System.out.println("\nClass not found @ Registration\n");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
        // </editor-fold>
    }
}
