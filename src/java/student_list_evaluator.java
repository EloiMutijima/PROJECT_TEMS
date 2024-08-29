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

@WebServlet("/student_list_evaluator")
public class student_list_evaluator extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Update with your DB password

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Retrieve and display data
                String sqlSelect = "SELECT * FROM students";
                try (PreparedStatement statement = connection.prepareStatement(sqlSelect)) {
                    ResultSet resultSet = statement.executeQuery();

                    out.println("<!DOCTYPE html>");
                    out.println("<html lang='en'>");
                    out.println("<head>");
                    out.println("<meta charset='UTF-8'>");
                    out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                    out.println("<title>Student Profiles - Trainer Evaluation Management System</title>");
                    out.println("<style>");
                    out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
                    out.println("header { background-color: darkblue; color: white; padding: 10px; text-align: center; font-size: 30px; font-weight: bold; }");
                    out.println(".container { display: flex; height: 100vh; }");
                    out.println(".sidebar { background-color: darkblue; color: white; width: 200px; padding: 20px; box-sizing: border-box; }");
                    out.println(".sidebar a { display: block; color: white; padding: 10px 0; text-decoration: none; position: relative; }");
                    out.println(".sidebar a:hover { background-color: white; color: black; }");
                    out.println(".dropdown { position: relative; }");
                    out.println(".dropdown-content { display: none; position: absolute; background-color: darkblue; min-width: 160px; z-index: 1; top: 100%; left: 0; border-radius: 5px; }");
                    out.println(".dropdown-content a { color: white; padding: 10px; text-decoration: none; display: block; }");
                    out.println(".dropdown-content a:hover { background-color: white; color: black; }");
                    out.println(".dropdown:hover .dropdown-content { display: block; }");
                    out.println(".content { flex: 1; padding: 20px; box-sizing: border-box; }");
                    out.println(".content h2 { color: darkblue; }");
                    out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
                    out.println("table, th, td { border: 1px solid #ddd; }");
                    out.println("th, td { padding: 12px; text-align: left; }");
                    out.println("th { background-color: darkblue; color: white; }");
                    out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
                    out.println("tr:hover { background-color: #ddd; }");
                    out.println(".actions a { margin-right: 10px; text-decoration: none; color: darkblue; }");
                    out.println(".actions a:hover { text-decoration: underline; }");
                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");
                    
                    // Header and Sidebar
                    out.println("<header>TRAINER EVALUATION MANAGEMENT SYSTEM</header>");
                    out.println("<div class='container'>");
                    out.println("<div class='sidebar'>");
                    out.println("<a href='new_user.jsp'>Create User</a>");
                    out.println("<div class='dropdown'>");
                    out.println("<a href='#'>Evaluations</a>");
                    out.println("<div class='dropdown-content'>");
                    out.println("<a href='SeeModules'>View Modules</a>");
                    out.println("<a href='#'>Evaluated Modules</a>");
                    out.println("</div></div>");
                    out.println("<div class='dropdown'>");
                    out.println("<a href='#'>Reports</a>");
                    out.println("<div class='dropdown-content'>");
                    out.println("<a href='#'>Users</a>");
                    out.println("<a href='#'>Trainer Reports</a>");
                    out.println("</div></div>");
                    out.println("<a href='#'>Student</a>");
                    out.println("<a href='#' class='logout'>Logout</a>");
                    out.println("</div>");
                    out.println("<div class='content'>");
                    out.println("<h2>Student Profiles</h2>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>Reg No</th>");
                    out.println("<th>Name</th>");
                    out.println("<th>Email</th>");
                    out.println("<th>Tel</th>");
                    out.println("<th>Department</th>");
                    out.println("<th>Options</th>");
                    out.println("<th>Level</th>");
                    out.println("<th>Actions</th>");
                    out.println("</tr>");
                    
                    // Display student profiles in table rows
                    while (resultSet.next()) {
                        out.println("<tr>");
                        out.println("<td>" + resultSet.getString("Regno") + "</td>");
                        out.println("<td>" + resultSet.getString("stud_name") + "</td>");
                        out.println("<td>" + resultSet.getString("email") + "</td>");
                        out.println("<td>" + resultSet.getString("Tel") + "</td>");
                        out.println("<td>" + resultSet.getString("Department") + "</td>");
                        out.println("<td>" + resultSet.getString("Options") + "</td>");
                        out.println("<td>" + resultSet.getString("Level") + "</td>");
                        out.println("<td class='actions'><a href='EditProfileServlet?regNo=" + resultSet.getString("Regno") + "'>Edit</a> | <a href='DeleteProfileServlet?regNo=" + resultSet.getString("Regno") + "'>Delete</a></td>");
                        out.println("</tr>");
                    }
                    
                    out.println("</table>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Error: Unable to connect to the database.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("Error: Unable to load database driver.");
        }
    }
}