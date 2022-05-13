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
public class GetUser extends HttpServlet {
    UserDB userdb;
    User user;

    @Override
    public void init() {
        userdb = new UserDB();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>"+
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
        user = userdb.getUser(request.getParameter("user_name"));
        String gender = user.getGender().toString().toLowerCase();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.print("<div class='container'>"
                    + "<button id='logout'>Logout</button>"
                    + "<button id='memberlist'>Peek at members</button>"
                    +"<button id='main-posts'>Posts Interface</button>"
                    +"<button id='create-post'>Create Post</button>"
                    +"<button id='delete-post'>Delete a Post</button>"
                    + "<div id='register_form' action='#'>"
                    + "<h1 lang='en'>Warp Drive Register</h1>"
                    + "<span class='reversegeo' id='reversegeo'>Find my location</span>"
                    + "<div class='input-item'>"
                    + "<label for='email' lang='en'>*E-mail:</label>"
                    + "<input id='email' type='email' pattern='[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,3}$' name='email' title='text@text2-3 characters' onchange=\"userCheck('email')\" value=" + user.getEmail() + " required>"
                    + "</div>"
                    + "<div class='input-item'>"
                    + "<label for='pw'>*Κωδικός χρήστη:</label>"
                    + "<input type='password' name='pw' id='pw' pattern='(?=.*[0-9])(?=.*[A-Za-z])(?=.*[^A-Za-z0-9]+)[-\\S]{8,10}' title='From 8 to 10 characters and please include at least 1 latin character, 1 number and 1 symbol!' value=" + user.getPassword() + " required>"
                    + "</div>"
                    + "<div class='input-item'>"
                    + "<label for='name'>*Όνομα:</label>"
                    + "<input type='text' name='name' id='name' pattern='[A-Za-z]{3,15}' title='3 - 15 latin characters' value=" + user.getFirstName() + " required>"
                    + "</div>"
                    + "<div class='input-item'>"
                    + "<label for='lastname'>*Επώνυμο:</label>"
                    + "<input type='text' id='lastname' name='surname' pattern='[A-Za-z]{3,15}' title='3 - 15 latin characters' value=" + user.getLastName() + " required>"
                    + "</div>"
                    + "<div class='input-item'>"
                    + "<label for='birth'>*Ημερομηνία γέννησης:</label>"
                    + "<input id='birth' type='text' name='date' pattern='[0-9]{2}/[0-9]{2}/[0-9]{4}$' title='ΗΗ/ΜΜ/ΧΧΧΧ' value=" + user.getBirthDate() + " required>"
                    + "</div>"
                    + "<div class='input-item'>"
                    + "<fieldset>"
                    + "<legend>Φύλο:</legend>");
            switch (gender) {
                case "male":
                    out.println("<label for='gender_boy'>Αγόρι<input id='gender_boy' name='gender' type='radio' value='male' checked></label>"
                            + "<label for='gender_girl'>Κορίτσι<input id='gender_girl' name='gender' type='radio' value='female'></label>"
                            + "<label for='gender_what'>Μη Εφαρμόσιμο<input id='gender_what' name='gender' type='radio' value='other'></label>");
                    break;
                case "female":
                    out.println("<label for='gender_boy'>Αγόρι<input id='gender_boy' name='gender' type='radio' value='male'></label>"
                            + "<label for='gender_girl'>Κορίτσι<input id='gender_girl' name='gender' type='radio' value='female' checked></label>"
                            + "<label for='gender_what'>Μη Εφαρμόσιμο<input id='gender_what' name='gender' type='radio' value='other'></label>");
                    break;
                default:
                    out.println("<label for='gender_boy'>Αγόρι<input id='gender_boy' name='gender' type='radio' value='male'></label>"
                            + "<label for='gender_girl'>Κορίτσι<input id='gender_girl' name='gender' type='radio' value='female'></label>"
                            + "<label for='gender_what'>Μη Εφαρμόσιμο<input id='gender_what' name='gender' type='radio' value='other' checked></label>");
                    break;
            }
            out.println("</fieldset>"                            + "</div>"
                    + "<div class='input-item'>"
                    + "<label for='address'>Διεύθυνση:</label>"
                    + "<input type='text' id='address' name='address' pattern='.{2,20}' value=" + user.getAddress() + " required>"
                    + "</div>"
                    + "<div class='input-item'>"
                    + "<label for='country'>*Χώρα:</label>"
                    + "<select id='country'>"
                    + "<option value='AF'>Afghanistan</option>"
                    + "<option value='AX'>Åland Islands</option>"
                    + "<option value='AL'>Albania</option>"
                    + "<option value='DZ'>Algeria</option>"
                    + "<option value='AS'>American Samoa</option>"
                    + "<option value='AD'>Andorra</option>"
                    + "<option value='AO'>Angola</option>"
                    + "<option value='AI'>Anguilla</option>"
                    + "<option value='AQ'>Antarctica</option>"
                    + "<option value='AG'>Antigua and Barbuda</option>"
                    + "<option value='AR'>Argentina</option>"
                    + "<option value='AM'>Armenia</option>"
                    + "<option value='AW'>Aruba</option>"
                    + "<option value='AU'>Australia</option>"
                    + "<option value='AT'>Austria</option>"
                    + "<option value='AZ'>Azerbaijan</option>"
                    + "<option value='BS'>Bahamas</option>"
                    + "<option value='BH'>Bahrain</option>"
                    + "<option value='BD'>Bangladesh</option>"
                    + "<option value='BB'>Barbados</option>"
                    + "<option value='BY'>Belarus</option>"
                    + "<option value='BE'>Belgium</option>"
                    + "<option value='BZ'>Belize</option>"
                    + "<option value='BJ'>Benin</option>"
                    + "<option value='BM'>Bermuda</option>"
                    + "<option value='BT'>Bhutan</option>"
                    + "<option value='BO'>Bolivia, Plurinational State of</option>"
                    + "<option value='BQ'>Bonaire, Sint Eustatius and Saba</option>"
                    + "<option value='BA'>Bosnia and Herzegovina</option>"
                    + "<option value='BW'>Botswana</option>"
                    + "<option value='BV'>Bouvet Island</option>"
                    + "<option value='BR'>Brazil</option>"
                    + "<option value='IO'>British Indian Ocean Territory</option>"
                    + "<option value='BN'>Brunei Darussalam</option>"
                    + "<option value='BG'>Bulgaria</option>"
                    + "<option value='BF'>Burkina Faso</option>"
                    + "<option value='BI'>Burundi</option>"
                    + "<option value='KH'>Cambodia</option>"
                    + "<option value='CM'>Cameroon</option>"
                    + "<option value='CA'>Canada</option>"
                    + "<option value='CV'>Cape Verde</option>"
                    + "<option value='KY'>Cayman Islands</option>"
                    + "<option value='CF'>Central African Republic</option>"
                    + "<option value='TD'>Chad</option>"
                    + "<option value='CL'>Chile</option>"
                    + "<option value='CN'>China</option>"
                    + "<option value='CX'>Christmas Island</option>"
                    + "<option value='CC'>Cocos (Keeling) Islands</option>"
                    + "<option value='CO'>Colombia</option>"
                    + "<option value='KM'>Comoros</option>"
                    + "<option value='CG'>Congo</option>"
                    + "<option value='CD'>Congo, the Democratic Republic of the</option>"
                    + "<option value='CK'>Cook Islands</option>"
                    + "<option value='CR'>Costa Rica</option>"
                    + "<option value='CI'>Côte d'Ivoire</option>"
                    + "<option value='HR'>Croatia</option>"
                    + "<option value='CU'>Cuba</option>"
                    + "<option value='CW'>Curaçao</option>"
                    + "<option value='CY'>Cyprus</option>"
                    + "<option value='CZ'>Czech Republic</option>"
                    + "<option value='DK'>Denmark</option>"
                    + "<option value='DJ'>Djibouti</option>"
                    + "<option value='DM'>Dominica</option>"
                    + "<option value='DO'>Dominican Republic</option>"
                    + "<option value='EC'>Ecuador</option>"
                    + "<option value='EG'>Egypt</option>"
                    + "<option value='SV'>El Salvador</option>"
                    + "<option value='GQ'>Equatorial Guinea</option>"
                    + "<option value='ER'>Eritrea</option>"
                    + "<option value='EE'>Estonia</option>"
                    + "<option value='ET'>Ethiopia</option>"
                    + "<option value='FK'>Falkland Islands (Malvinas)</option>"
                    + "<option value='FO'>Faroe Islands</option>"
                    + "<option value='FJ'>Fiji</option>"
                    + "<option value='FI'>Finland</option>"
                    + "<option value='FR'>France</option>"
                    + "<option value='GF'>French Guiana</option>"
                    + "<option value='PF'>French Polynesia</option>"
                    + "<option value='TF'>French Southern Territories</option>"
                    + "<option value='GA'>Gabon</option>"
                    + "<option value='GM'>Gambia</option>"
                    + "<option value='GE'>Georgia</option>"
                    + "<option value='DE'>Germany</option>"
                    + "<option value='GH'>Ghana</option>"
                    + "<option value='GI'>Gibraltar</option>"
                    + "<option selected='selected' value='GR'>Greece</option>"
                    + "<option value='GL'>Greenland</option>"
                    + "<option value='GD'>Grenada</option>"
                    + "<option value='GP'>Guadeloupe</option>"
                    + "<option value='GU'>Guam</option>"
                    + "<option value='GT'>Guatemala</option>"
                    + "<option value='GG'>Guernsey</option>"
                    + "<option value='GN'>Guinea</option>"
                    + "<option value='GW'>Guinea-Bissau</option>"
                    + "<option value='GY'>Guyana</option>"
                    + "<option value='HT'>Haiti</option>"
                    + "<option value='HM'>Heard Island and McDonald Islands</option>"
                    + "<option value='VA'>Holy See (Vatican City State)</option>"
                    + "<option value='HN'>Honduras</option>"
                    + "<option value='HK'>Hong Kong</option>"
                    + "<option value='HU'>Hungary</option>"
                    + "<option value='IS'>Iceland</option>"
                    + "<option value='IN'>India</option>"
                    + "<option value='ID'>Indonesia</option>"
                    + "<option value='IR'>Iran, Islamic Republic of</option>"
                    + "<option value='IQ'>Iraq</option>"
                    + "<option value='IE'>Ireland</option>"
                    + "<option value='IM'>Isle of Man</option>"
                    + "<option value='IL'>Israel</option>"
                    + "<option value='IT'>Italy</option>"
                    + "<option value='JM'>Jamaica</option>"
                    + "<option value='JP'>Japan</option>"
                    + "<option value='JE'>Jersey</option>"
                    + "<option value='JO'>Jordan</option>"
                    + "<option value='KZ'>Kazakhstan</option>"
                    + "<option value='KE'>Kenya</option>"
                    + "<option value='KI'>Kiribati</option>"
                    + "<option value='KP'>Korea, Democratic People's Republic of</option>"
                    + "<option value='KR'>Korea, Republic of</option>"
                    + "<option value='KW'>Kuwait</option>"
                    + "<option value='KG'>Kyrgyzstan</option>"
                    + "<option value='LA'>Lao People's Democratic Republic</option>"
                    + "<option value='LV'>Latvia</option>"
                    + "<option value='LB'>Lebanon</option>"
                    + "<option value='LS'>Lesotho</option>"
                    + "<option value='LR'>Liberia</option>"
                    + "<option value='LY'>Libya</option>"
                    + "<option value='LI'>Liechtenstein</option>"
                    + "<option value='LT'>Lithuania</option>"
                    + "<option value='LU'>Luxembourg</option>"
                    + "<option value='MO'>Macao</option>"
                    + "<option value='MK'>Macedonia, the former Yugoslav Republic of</option>"
                    + "<option value='MG'>Madagascar</option>"
                    + "<option value='MW'>Malawi</option>"
                    + "<option value='MY'>Malaysia</option>"
                    + "<option value='MV'>Maldives</option>"
                    + "<option value='ML'>Mali</option>"
                    + "<option value='MT'>Malta</option>"
                    + "<option value='MH'>Marshall Islands</option>"
                    + "<option value='MQ'>Martinique</option>"
                    + "<option value='MR'>Mauritania</option>"
                    + "<option value='MU'>Mauritius</option>"
                    + "<option value='YT'>Mayotte</option>"
                    + "<option value='MX'>Mexico</option>"
                    + "<option value='FM'>Micronesia, Federated States of</option>"
                    + "<option value='MD'>Moldova, Republic of</option>"
                    + "<option value='MC'>Monaco</option>"
                    + "<option value='MN'>Mongolia</option>"
                    + "<option value='ME'>Montenegro</option>"
                    + "<option value='MS'>Montserrat</option>"
                    + "<option value='MA'>Morocco</option>"
                    + "<option value='MZ'>Mozambique</option>"
                    + "<option value='MM'>Myanmar</option>"
                    + "<option value='NA'>Namibia</option>"
                    + "<option value='NR'>Nauru</option>"
                    + "<option value='NP'>Nepal</option>"
                    + "<option value='NL'>Netherlands</option>"
                    + "<option value='NC'>New Caledonia</option>"
                    + "<option value='NZ'>New Zealand</option>"
                    + "<option value='NI'>Nicaragua</option>"
                    + "<option value='NE'>Niger</option>"
                    + "<option value='NG'>Nigeria</option>"
                    + "<option value='NU'>Niue</option>"
                    + "<option value='NF'>Norfolk Island</option>"
                    + "<option value='MP'>Northern Mariana Islands</option>"
                    + "<option value='NO'>Norway</option>"
                    + "<option value='OM'>Oman</option>"
                    + "<option value='PK'>Pakistan</option>"
                    + "<option value='PW'>Palau</option>"
                    + "<option value='PS'>Palestinian Territory, Occupied</option>"
                    + "<option value='PA'>Panama</option>"
                    + "<option value='PG'>Papua New Guinea</option>"
                    + "<option value='PY'>Paraguay</option>"
                    + "<option value='PE'>Peru</option>"
                    + "<option value='PH'>Philippines</option>"
                    + "<option value='PN'>Pitcairn</option>"
                    + "<option value='PL'>Poland</option>"
                    + "<option value='PT'>Portugal</option>"
                    + "<option value='PR'>Puerto Rico</option>"
                    + "<option value='QA'>Qatar</option>"
                    + "<option value='RE'>Réunion</option>"
                    + "<option value='RO'>Romania</option>"
                    + "<option value='RU'>Russian Federation</option>"
                    + "<option value='RW'>Rwanda</option>"
                    + "<option value='BL'>Saint Barthélemy</option>"
                    + "<option value='SH'>Saint Helena, Ascension and Tristan da Cunha</option>"
                    + "<option value='KN'>Saint Kitts and Nevis</option>"
                    + "<option value='LC'>Saint Lucia</option>"
                    + "<option value='MF'>Saint Martin (French part)</option>"
                    + "<option value='PM'>Saint Pierre and Miquelon</option>"
                    + "<option value='VC'>Saint Vincent and the Grenadines</option>"
                    + "<option value='WS'>Samoa</option>"
                    + "<option value='SM'>San Marino</option>"
                    + "<option value='ST'>Sao Tome and Principe</option>"
                    + "<option value='SA'>Saudi Arabia</option>"
                    + "<option value='SN'>Senegal</option>"
                    + "<option value='RS'>Serbia</option>"
                    + "<option value='SC'>Seychelles</option>"
                    + "<option value='SL'>Sierra Leone</option>"
                    + "<option value='SG'>Singapore</option>"
                    + "<option value='SX'>Sint Maarten (Dutch part)</option>"
                    + "<option value='SK'>Slovakia</option>"
                    + "<option value='SI'>Slovenia</option>"
                    + "<option value='SB'>Solomon Islands</option>"
                    + "<option value='SO'>Somalia</option>"
                    + "<option value='ZA'>South Africa</option>"
                    + "<option value='GS'>South Georgia and the South Sandwich Islands</option>"
                    + "<option value='SS'>South Sudan</option>"
                    + "<option value='ES'>Spain</option>"
                    + "<option value='LK'>Sri Lanka</option>"
                    + "<option value='SD'>Sudan</option>"
                    + "<option value='SR'>Suriname</option>"
                    + "<option value='SJ'>Svalbard and Jan Mayen</option>"
                    + "<option value='SZ'>Swaziland</option>"
                    + "<option value='SE'>Sweden</option>"
                    + "<option value='CH'>Switzerland</option>"
                    + "<option value='SY'>Syrian Arab Republic</option>"
                    + "<option value='TW'>Taiwan, Province of China</option>"
                    + "<option value='TJ'>Tajikistan</option>"
                    + "<option value='TZ'>Tanzania, United Republic of</option>"
                    + "<option value='TH'>Thailand</option>"
                    + "<option value='TL'>Timor-Leste</option>"
                    + "<option value='TG'>Togo</option>"
                    + "<option value='TK'>Tokelau</option>"
                    + "<option value='TO'>Tonga</option>"
                    + "<option value='TT'>Trinidad and Tobago</option>"
                    + "<option value='TN'>Tunisia</option>"
                    + "<option value='TR'>Turkey</option>"
                    + "<option value='TM'>Turkmenistan</option>"
                    + "<option value='TC'>Turks and Caicos Islands</option>"
                    + "<option value='TV'>Tuvalu</option>"
                    + "<option value='UG'>Uganda</option>"
                    + "<option value='UA'>Ukraine</option>"
                    + "<option value='AE'>United Arab Emirates</option>"
                    + "<option value='GB'>United Kingdom</option>"
                    + "<option value='US'>United States</option>"
                    + "<option value='UM'>United States Minor Outlying Islands</option>"
                    + "<option value='UY'>Uruguay</option>"
                    + "<option value='UZ'>Uzbekistan</option>"
                    + "<option value='VU'>Vanuatu</option>"
                    + "<option value='VE'>Venezuela, Bolivarian Republic of</option>"
                    + "<option value='VN'>Viet Nam</option>"
                    + "<option value='VG'>Virgin Islands, British</option>"
                    + "<option value='VI'>Virgin Islands, U.S.</option>"
                    + "<option value='WF'>Wallis and Futuna</option>"
                    + "<option value='EH'>Western Sahara</option>"
                    + "<option value='YE'>Yemen</option>"
                    + "<option value='ZM'>Zambia</option>"
                    + "<option value='ZW'>Zimbabwe</option>"
                    + "</select>"
                    + "</div>"
                    + "<div class='input-item'>"
                    + "<label for='city'>*Πόλη:</label>"
                    + "<input type='text' id='city' name='city' pattern='.{2,20}' value=" + user.getTown() + " required>"
                    + "<span id='address_error' class='verification_error'></span>"
                    + "<span class='map-btn' id='map-btn'>Show place on map</span>"
                    + "</div>"
                    + "<div class='input-item'>"
                    + "<label for='profession'>*Επάγγελμα:</label>"
                    + "<input type='text' id='profession' name='profession' pattern='.{3,15}' value=" + user.getOccupation() + " required>"
                    + "</div>"
                    + "<div class='input-item'>"
                    + "<label for='interests'>Ενδιαφέροντα:</label>"
                    + "<textarea id='interests' name='interests' maxlength='100'>" + user.getInterests() + "</textarea>"
                    + "</div>"
                    + "<div class='input-item'>"
                    + "<label for='more_info'>Γενικές Πληροφορίες:</label>"
                    + "<textarea id='more_info' name='more_info' maxlength='500'>" + user.getInfo() + "</textarea>"
                    + "</div>"
                    + "<button class='update' id='update'>Ενημέρωση</button>"
                    + "</div>");
        }
    }

    // <editor-fold defaultstate='collapsed' desc='HttpServlet methods. Click on the + sign on the left to edit the code.'>"+
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
            Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetUser.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>"+

}
