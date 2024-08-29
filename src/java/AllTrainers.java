import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllTrainers")
public class AllTrainers extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Update with your DB password

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Retrieve and display data
                String sqlSelect = "SELECT * FROM trainers";
                try (PreparedStatement statement = connection.prepareStatement(sqlSelect)) {
                    ResultSet resultSet = statement.executeQuery();

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<meta charset='UTF-8'>");
                    out.println("<title>Trainer Profiles</title>");
                    out.println("<style>");
                    out.println("body {");
                    out.println("    font-family: Arial, sans-serif;");
                    out.println("    margin: 0;");
                    out.println("    padding: 0;");
                    out.println("    background-color: #f4f4f4;");
                    out.println("    display: flex;");
                    out.println("    flex-direction: column;");
                    out.println("    min-height: 100vh;");
                    out.println("}");
                    out.println("header {");
                    out.println("    background-color: darkblue;");
                    out.println("    color: white;");
                    out.println("    padding: 10px;");
                    out.println("    text-align: center;");
                    out.println("    font-size: 24px;");
                    out.println("    font-weight: bold;");
                    out.println("}");
                    out.println(".container {");
                    out.println("    display: flex;");
                    out.println("    flex: 1;");
                    out.println("    overflow: hidden;");
                    out.println("}");
                    out.println(".sidebar {");
                    out.println("    background-color: darkblue;");
                    out.println("    color: white;");
                    out.println("    width: 250px;");
                    out.println("    padding: 20px;");
                    out.println("    box-sizing: border-box;");
                    out.println("    flex-shrink: 0;");
                    out.println("    overflow-y: auto;");
                    out.println("}");
                    out.println(".sidebar a {");
                    out.println("    display: block;");
                    out.println("    color: white;");
                    out.println("    padding: 10px 0;");
                    out.println("    text-decoration: none;");
                    out.println("    position: relative;");
                    out.println("}");
                    out.println(".sidebar a:hover {");
                    out.println("    background-color: white;");
                    out.println("    color: black;");
                    out.println("}");
                    out.println(".dropdown {");
                    out.println("    position: relative;");
                    out.println("}");
                    out.println(".dropdown-content {");
                    out.println("    display: none;");
                    out.println("    position: absolute;");
                    out.println("    background-color: darkblue;");
                    out.println("    min-width: 160px;");
                    out.println("    z-index: 1;");
                    out.println("    top: 100%;");
                    out.println("    left: 0;");
                    out.println("    border-radius: 5px;");
                    out.println("}");
                    out.println(".dropdown-content a {");
                    out.println("    color: white;");
                    out.println("    padding: 10px;");
                    out.println("    text-decoration: none;");
                    out.println("    display: block;");
                    out.println("}");
                    out.println(".dropdown-content a:hover {");
                    out.println("    background-color: white;");
                    out.println("    color: black;");
                    out.println("}");
                    out.println(".dropdown:hover .dropdown-content {");
                    out.println("    display: block;");
                    out.println("}");
                    out.println(".content {");
                    out.println("    flex: 1;");
                    out.println("    padding: 20px;");
                    out.println("    box-sizing: border-box;");
                    out.println("    overflow-y: auto;");
                    out.println("    margin-left: 250px;");
                    out.println("}");
                    out.println(".content h2 {");
                    out.println("    color: darkblue;");
                    out.println("    margin-top: 0;");
                    out.println("}");
                    out.println(".table-container {");
                    out.println("    overflow-x: auto;");
                    out.println("}");
                    out.println("table {");
                    out.println("    width: 100%;");
                    out.println("    border-collapse: collapse;");
                    out.println("    background-color: white;");
                    out.println("    margin-top: 20px;");
                    out.println("}");
                    out.println("th, td {");
                    out.println("    padding: 10px;");
                    out.println("    text-align: left;");
                    out.println("    border-bottom: 1px solid #ddd;");
                    out.println("}");
                    out.println("th {");
                    out.println("    background-color: darkblue;");
                    out.println("    color: white;");
                    out.println("}");
                    out.println("tr:hover {");
                    out.println("    background-color: #f1f1f1;");
                    out.println("}");
                    out.println(".logout {");
                    out.println("    color: red;");
                    out.println("    text-decoration: none;");
                    out.println("}");
                    out.println("footer {");
                    out.println("    background-color: darkblue;");
                    out.println("    color: white;");
                    out.println("    text-align: center;");
                    out.println("    padding: 10px;");
                    out.println("    margin-top: auto;");
                    out.println("}");
                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");

                    // Header and Sidebar
                    out.println("<header>");
                    out.println("TRAINER EVALUATION MANAGEMENT SYSTEM");
                    out.println("</header>");
                    out.println("<div class='container'>");
                    out.println("<div class='sidebar'>");
                    out.println("<a href='evaluators.jsp'>Home</a>");
                    out.println("<a href='AllTrainers'>Trainer</a>");
                    out.println("<a href='new_module.jsp'>Create Module</a>");
                    out.println("<a href='RepliesServlet'>Trainer Feedback</a>");
                    out.println("<a href='EvaluationsServlet'>View Reports</a>");
                    out.println("<a href='loginpage.jsp' class='logout'>Logout</a>");
                    out.println("</div>");

                    // Main content
                    out.println("<div class='content'>");
                    out.println("<h2>Trainer Profiles:</h2>");
                    out.println("<div class='table-container'>");
                    out.println("<table>");
                    out.println("<thead>");
                    out.println("<tr><th>ID</th><th>Names</th><th>Phone</th><th>Department</th><th>Options</th><th>Module</th><th>Email</th><th>Actions</th></tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    while (resultSet.next()) {
                        out.println("<tr>");
                        out.println("<td>" + resultSet.getString("Trainer_id") + "</td>");
                        out.println("<td>" + resultSet.getString("Trainer") + "</td>");
                        out.println("<td>" + resultSet.getString("Tel") + "</td>");
                        out.println("<td>" + resultSet.getString("Departments") + "</td>");
                        out.println("<td>" + resultSet.getString("Options") + "</td>");
                        out.println("<td>" + resultSet.getString("Modules") + "</td>");
                        out.println("<td>" + resultSet.getString("email") + "</td>");
                        out.println("<td><a href='EditTrainerServlet?Trainer_id=" + resultSet.getString("Trainer_id") + "'>Edit</a></td>");
                        out.println("</tr>");
                    }
                    out.println("</tbody>");
                    out.println("</table>");
                    out.println("</div>");
                    out.println("</div>");

                    // Footer
                    out.println("</div>");
                    out.println("<footer>");
                    out.println("Trainer Evaluation Management System all rights reserved");
                    out.println("</footer>");

                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h2>MySQL JDBC Driver not found.</h2>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            out.close();
        }
    }
}
