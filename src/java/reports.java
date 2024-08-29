/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class reports extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet reports</title>");            
            out.println("<!DOCTYPE html>\n" +
"<html lang=\"en\">\n" +
"<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <title>Student Registration Form</title>\n" +
"<style>\n" +
"    body {\n" +
"    font-family: Arial, sans-serif;\n" +
"    margin: 0;\n" +
"    padding: 0;\n" +
"}\n" +
"\n" +
"header {\n" +
"    background-color: #002D72;\n" +
"    color: white;\n" +
"    padding: 10px 0;\n" +
"    text-align: center;\n" +
"}\n" +
"\n" +
".card {\n" +
"    background-color: white;\n" +
"    border: 1px solid #ccc;\n" +
"    border-radius: 8px;\n" +
"    padding: 20px;\n" +
"    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
"    text-align: left;\n" +
"}\n" +
"\n" +
".card h2 {\n" +
"    margin-top: 0;\n" +
"}\n" +
"\n" +
".input{\n" +
"    height:30px;\n" +
"    width:100%;\n" +
"    border: solid #002D72;\n" +
"}\n" +
".column{\n" +
"    float: left;\n" +
"    width: 33.33%;\n" +
"}\n" +
".row:after{\n" +
"    content: \"\";\n" +
"    display: table;\n" +
"    clear: both;\n" +
"}\n" +
".button{\n" +
"background:rgba(59,79,149,1);\n" +
"height:40px;\n" +
"width:52%;\n" +
"margin-left:-100px;\n" +
"color: white;\n" +
"font-weight: bold;  \n" +
"border: none;\n" +
"margin-left:220px;\n" +
"font-size:15px;\n" +
"}\n" +
"</style>\n" +
"</head>\n" +
"<body>\n" +
"    <header>\n" +
"        <h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>");
            out.println("<body>");
            out.println("<h1></h1>");
            out.println("</body>");
            out.println("</html>");
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
