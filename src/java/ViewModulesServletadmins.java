import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewModules")
public class ViewModulesServletadmins extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // SQL query to retrieve data from the table
            String sql = "SELECT * FROM newmodule";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // Set response content type
            response.setContentType("text/html");

            // Start HTML output with embedded CSS
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'><head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Trainer Evaluation Management System</title>");
            out.println("<link rel='stylesheet' type='text/css' href='fontawesome-free-6.5.2-web/css/all.css'>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
            out.println("header { background-color: darkblue; color: white; padding: 10px; text-align: center; font-size: 30px; font-weight: bold; }");
            out.println("nav { background-color: darkblue; width: 200px; height: 100vh; position: fixed; top: 0; left: 0; padding-top: 20px; }");
            out.println("nav ul { list-style-type: none; padding: 0; }");
            out.println("nav ul li { margin-bottom: 20px; position: relative; }");
            out.println("nav ul li a { color: white; text-decoration: none; padding: 10px; display: block; }");
            out.println("nav ul li a:hover { background-color: white; color: black; }");
            out.println("nav ul li ul { display: none; list-style-type: none; padding: 0; position: absolute; left: 0; top: 100%; background-color: darkblue; width: 100%; }");
            out.println("nav ul li:hover ul { display: block; }");
            out.println("nav ul li ul li { margin-bottom: 0; }");
            out.println("nav ul li ul li a { padding: 10px; }");
            out.println("main { margin-left: 220px; padding: 20px; text-align: center; }");
            out.println(".module-table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
            out.println(".module-table th, .module-table td { border: 1px solid #ddd; padding: 10px; text-align: left; }");
            out.println(".module-table th { background-color: darkblue; color: white; }");
            out.println("footer { background-color: darkblue; color: white; text-align: center; padding: 10px 0; position: fixed; bottom: 0; width: 100%; }");
            out.println("</style>");
            out.println("</head><body>");

            // Header and Navigation
            out.println("<header>");
            out.println("<h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>");
            out.println("</header>");
            out.println("<nav>");
            out.println("<ul>");
            out.println("<li><a href='new_user.jsp'>Create User</a></li>");
            out.println("<li><a href='#'>Evaluations</a>");
            out.println("<ul>");
            out.println("<li><a href='viewModules'>View Modules</a></li>");
            out.println("<li><a href='FeedbackServletadmins'>Evaluated Modules</a></li>");
            out.println("</ul>");
            out.println("</li>");
            out.println("<li><a href='TrainerProfileServlet'>Trainers</a></li>");
            out.println("<li><a href='#'>Reports</a>");
            out.println("<ul>");
            out.println("<li><a href='RetrieveUsersServlet'>Users</a></li>");
            out.println("<li><a href='trainerreport'>Trainer Reports</a></li>");
            out.println("</ul>");
            out.println("</li>");
            out.println("<li><a href='student_list'>Student</a></li>");
            out.println("<li><a href='login.jsp' style='color:red;'>Logout</a></li>");
            out.println("</ul>");
            out.println("</nav>");

            // Main content
            out.println("<main>");
            out.println("<h1>Module Details</h1>");
            out.println("<table class='module-table'><tr><th>Semester</th><th>Academic</th><th>M_code</th><th>M_name</th><th>Department</th><th>Trainer</th><th>Program</th><th>Level</th></tr>");

            // Iterate through the result set and display data in an HTML table
            while (rs.next()) {
                int semester = rs.getInt("Semester");
                String academic = rs.getString("Academic");
                String m_code = rs.getString("M_code");
                String m_name = rs.getString("M_name");
                String department = rs.getString("Department");
                String trainer = rs.getString("Trainer");
                String program = rs.getString("Program");
                String level = rs.getString("Level");

                out.println("<tr><td>" + semester + "</td><td>" + academic + "</td><td>" + m_code + "</td><td>" + m_name + "</td><td>" + department + "</td><td>" + trainer + "</td><td>" + program + "</td><td>" + level + "</td></tr>");
            }

            // End HTML output
            out.println("</table>");
            out.println("</main>");

            // Footer
            out.println("<footer>");
            out.println("Trainer Evaluation Management System &copy; All rights reserved");
            out.println("</footer>");

            out.println("</body></html>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
