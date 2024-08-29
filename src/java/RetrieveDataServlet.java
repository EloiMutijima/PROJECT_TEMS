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

@WebServlet("/retrieveData")
public class RetrieveDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
        String username = "root";
        String password = "";

        String query = "SELECT id, department, option_field, academic_year, module_name, trainer, strengths, improvement_area FROM evaluatedmodule";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Check for feedback message in session
            String feedbackMessage = (String) request.getSession().getAttribute("feedbackMessage");
            if (feedbackMessage != null) {
                out.println("<script>alert('" + feedbackMessage + "');</script>");
                request.getSession().removeAttribute("feedbackMessage"); // Clear message after displaying
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Evaluated Modules</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; }");
            out.println("header { background-color: #002D72; color: white; padding: 10px 0; text-align: center; }");
            out.println("nav { background-color: grey; }");
            out.println("nav ul { list-style-type: none; margin: 0; padding: 0; display: flex; justify-content: center; }");
            out.println("nav ul li { margin: 0 15px; position: relative; }");
            out.println("nav ul li a { color: white; text-decoration: none; padding: 10px 15px; display: block; }");
            out.println("nav ul li ul { display: none; position: absolute; background-color: grey; top: 100%; left: 0; padding: 0; list-style-type: none; }");
            out.println("nav ul li:hover ul { display: block; }");
            out.println("nav ul li ul li a { padding: 10px; }");
            out.println(".logout { color: red; }");
            out.println("main { padding: 20px; text-align: center; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }");
            out.println("th { background-color: #002D72; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("form { margin-top: 20px; }");
            out.println("input[type='text'], textarea { padding: 8px; border: 1px solid #ddd; border-radius: 4px; width: 100%; box-sizing: border-box; }");
            out.println("input[type='submit'] { background-color: #002D72; color: white; border: none; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin-top: 10px; cursor: pointer; border-radius: 4px; }");
            out.println("input[type='submit']:hover { background-color: #0056b3; }");
            out.println("footer { background-color: #002D72; color: white; text-align: center; height: 100px; padding: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            // Header and Navigation
            out.println("<header>");
            out.println("<h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>");
            out.println("</header>");
            out.println("<nav>");
            out.println("<ul>");
            out.println("<li><a href='trainers.jsp'>Home</a></li>");
            out.println("<li><a href='trainer_profile.jsp'>Profile</a></li>");
            out.println("<li><a href='ViewModulesServlet'>Module</a></li>");
            out.println("<li><a href='RetrieveDataServlet'>View Reports</a></li>");
            out.println("<li><a href='login.jsp' class='logout'>Logout</a></li>");
            out.println("</ul>");
            out.println("</nav>");

            // Main content
            out.println("<main>");
            out.println("<h2>Evaluated Modules</h2>");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Department</th><th>Option Field</th><th>Academic Year</th><th>Module Name</th><th>Trainer</th><th>Strengths</th><th>Improvement Area</th></tr>");

            while (resultSet.next()) {
                out.println("<tr>");
                out.println("<td>" + resultSet.getInt("id") + "</td>");
                out.println("<td>" + resultSet.getString("department") + "</td>");
                out.println("<td>" + resultSet.getString("option_field") + "</td>");
                out.println("<td>" + resultSet.getString("academic_year") + "</td>");
                out.println("<td>" + resultSet.getString("module_name") + "</td>");
                out.println("<td>" + resultSet.getString("trainer") + "</td>");
                out.println("<td>" + resultSet.getString("strengths") + "</td>");
                out.println("<td>" + resultSet.getString("improvement_area") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

            // Feedback form
            out.println("<h3>Add Feedback</h3>");
            out.println("<form method='post' action='SubmitFeedbacks'>");
            out.println("Module ID: <input type='text' name='moduleId' required><br><br>");
            out.println("Feedback: <textarea name='feedback' rows='4' cols='50' required></textarea><br><br>");
            out.println("<input type='submit' value='Submit Feedback'>");
            out.println("</form>");
            
            out.println("</main>");

            // Footer
            out.println("<footer>");
            out.println("Trainer Evaluation Management System all rights reserved");
            out.println("</footer>");
            
            out.println("</body>");
            out.println("</html>");

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h2>An error occurred: " + e.getMessage() + "</h2>");
        } finally {
            out.close();
        }
    }
}
