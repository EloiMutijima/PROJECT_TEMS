import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DisplayAndDeleteUsersServlet")
public class DisplayAndDeleteUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Setting up response type and output stream
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // Check if there is a deletion request
            String deleteRegNo = request.getParameter("deleteRegNo");
            if (deleteRegNo != null) {
                String deleteSQL = "DELETE FROM users WHERE RegNo = ?";
                stmt = conn.prepareStatement(deleteSQL);
                stmt.setString(1, deleteRegNo);
                stmt.executeUpdate();
                stmt.close(); // Close the statement to reuse it
            }

            // SQL query to fetch users with Rol_id = 4
            String sql = "SELECT RegNo, Username FROM users WHERE Rol_id = 4";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // HTML output
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>User List - Trainer Evaluation Management System</title>");
            out.println("<link href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; display: flex; height: 100vh; }");
            out.println("header { background-color: darkblue; color: white; padding: 10px; text-align: center; font-size: 30px; font-weight: bold; width: 100%; position: fixed; top: 0; left: 0; z-index: 1000; }");
            out.println(".container { display: flex; flex: 1; margin-top: 60px; }");
            out.println(".sidebar { background-color: darkblue; color: white; width: 200px; padding: 20px; box-sizing: border-box; height: calc(100vh - 60px); position: fixed; top: 60px; left: 0; }");
            out.println(".sidebar a { display: block; color: white; padding: 10px 0; text-decoration: none; position: relative; }");
            out.println(".sidebar a:hover { background-color: white; color: black; }");
            out.println(".dropdown { position: relative; }");
            out.println(".dropdown-content { display: none; position: absolute; background-color: darkblue; min-width: 160px; z-index: 1; top: 100%; left: 0; border-radius: 5px; }");
            out.println(".dropdown-content a { color: white; padding: 10px; text-decoration: none; display: block; }");
            out.println(".dropdown-content a:hover { background-color: white; color: black; }");
            out.println(".dropdown:hover .dropdown-content { display: block; }");
            out.println(".content { flex: 1; padding: 20px; margin-left: 200px; box-sizing: border-box; }");
            out.println(".content h2 { color: darkblue; }");
            out.println(".alert-custom { background-color: skyblue; color: darkblue; border: 1px solid darkblue; border-radius: 5px; margin-bottom: 15px; }");
            out.println(".alert-custom .btn-danger { background-color: red; border: none; color: white; }");
            out.println(".alert-custom .row { display: flex; align-items: center; }");
            out.println(".alert-custom .col { padding: 10px; }");
            out.println("footer { background-color: darkblue; color: white; text-align: center; padding: 10px 0; position: fixed; bottom: 0; width: calc(100% - 200px); margin-left: 200px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            // Header
            out.println("<header>TRAINER EVALUATION MANAGEMENT SYSTEM</header>");

            // Main container
            out.println("<div class='container'>");

            // Sidebar
            out.println("<div class='sidebar'>");
            out.println("<a href='new_user.jsp'>Create User</a>");
            out.println("<div class='dropdown'>");
            out.println("<a href='#'>Evaluations</a>");
            out.println("<div class='dropdown-content'>");
            out.println("<a href='SeeModules'>View Modules</a>");
            out.println("<a href='ViewEvaluationServlet'>Evaluated Modules</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='dropdown'>");
            out.println("<a href='#'>Reports</a>");
            out.println("<div class='dropdown-content'>");
            out.println("<a href='SeeUsers'>Users</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("<a href='#'>Student</a>");
            out.println("<a href='loginpage.jsp' class='logout'>Logout</a>");
            out.println("</div>");

            // Content
            out.println("<div class='content'>");
            out.println("<h2>User List</h2>");

            // Display users with delete option
            while (rs.next()) {
                String regNo = rs.getString("RegNo");
                String username = rs.getString("Username");

                out.println("<div class='alert alert-custom' role='alert'>");
                out.println("<div class='row'>");
                out.println("<div class='col'><strong>REG NO:</strong> " + regNo + "</div>");
                out.println("<div class='col'><strong>Names:</strong> " + username + "</div>");
                out.println("<div class='col text-right'>");
                out.println("<a href='DisplayAndDeleteUsersServlet?deleteRegNo=" + regNo + "' class='btn btn-danger'>Delete</a>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
            }

            out.println("</div>"); // End of content
            out.println("</div>"); // End of container

            // Footer
            out.println("<footer>Trainer Evaluation Management System &copy; All rights reserved</footer>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
