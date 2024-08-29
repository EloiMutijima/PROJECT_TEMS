import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SeeUsers extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<p>Error: JDBC Driver not found.</p>");
            return;
        }

        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>View Users - Trainer Evaluation Management System</title>");
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
            out.println(".table-container { overflow-y: auto; max-height: 500px; }");
            out.println("table { width: 100%; border-collapse: collapse; }");
            out.println("table, th, td { border: 1px solid #ddd; }");
            out.println("th, td { padding: 10px; text-align: left; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("th { background-color: darkblue; color: white; }");
            out.println(".update-form { margin-top: 20px; }");
            out.println(".update-form input { display: block; width: 100%; padding: 10px; margin: 5px 0; }");
            out.println(".update-form button { background-color: darkblue; color: white; border: none; padding: 10px; cursor: pointer; }");
            out.println(".update-form button:hover { background-color: navy; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>TRAINER EVALUATION MANAGEMENT SYSTEM</header>");
            out.println("<div class='container'>");
            out.println("<div class='sidebar'>");
            out.println("<a href='adminpage.jsp'>Go Home</a>");
            out.println("<a href='new_user.jsp'>Create User</a>");
            out.println("<div class='dropdown'>");
            out.println("<a href='#'>Evaluations</a>");
            out.println("<div class='dropdown-content'>");
            out.println("<a href='SeeModules'>View Modules</a>");
            out.println("<a href='ViewEvaluationServlet'>Evaluated Modules</a>");
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
            out.println("<h2>View Users</h2>");
            out.println("<div class='table-container'>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Rol_id</th>");
            out.println("<th>RegNo</th>");
            out.println("<th>Username</th>");
            out.println("<th>Password</th>");
            out.println("<th>Trainer_id</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            while (rs.next()) {
                String rolId = rs.getString("Rol_id");
                String regNo = rs.getString("RegNo");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String trainerId = rs.getString("Trainer_id");

                out.println("<tr>");
                out.println("<td>" + rolId + "</td>");
                out.println("<td>" + regNo + "</td>");
                out.println("<td>" + username + "</td>");
                out.println("<td>" + (rolId.equals("1") ? password : "[Hidden]") + "</td>");
                out.println("<td>" + trainerId + "</td>");
                if (rolId.equals("1")) {
                    out.println("<td><a href='#' class='update' data-regno='" + regNo + "' data-username='" + username + "'>Update</a></td>");
                } else {
                    out.println("<td>N/A</td>");
                }
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

            out.println("<div class='update-form' id='updateForm' style='display:none;'>");
            out.println("<h3>Update User</h3>");
            out.println("<form action='UpdateUser' method='post'>");
            out.println("<input type='hidden' name='regNo' id='regNo'>");
            out.println("<label for='newUsername'>New Username:</label>");
            out.println("<input type='text' name='newUsername' id='newUsername' required>");
            out.println("<label for='newPassword'>New Password:</label>");
            out.println("<input type='password' name='newPassword' id='newPassword' required>");
            out.println("<button type='submit'>Update</button>");
            out.println("</form>");
            out.println("</div>");

            // JavaScript for handling update actions
            out.println("<script>");
            out.println("document.querySelectorAll('.update').forEach(function(element) {");
            out.println("    element.addEventListener('click', function(e) {");
            out.println("        e.preventDefault();");
            out.println("        var regNo = this.getAttribute('data-regno');");
            out.println("        var username = this.getAttribute('data-username');");
            out.println("        document.getElementById('regNo').value = regNo;");
            out.println("        document.getElementById('newUsername').value = username;");
            out.println("        document.getElementById('updateForm').style.display = 'block';");
            out.println("    });");
            out.println("});");
            out.println("</script>");

            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p>Error: Unable to retrieve user data.</p>");
        }
    }
}
