
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

@WebServlet("/SeeModules")
public class SeeModules extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // HTML structure with CSS
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Admin Dashboard - View Modules</title>");
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
        out.println("table, th, td { border: 1px solid darkblue; }");
        out.println("th, td { padding: 10px; text-align: left; }");
        out.println("th { background-color: darkblue; color: white; }");
        out.println("input[type='text'] { padding: 10px; width: 100%; margin-top: 20px; box-sizing: border-box; border: 1px solid darkblue; border-radius: 5px; }");
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
        out.println("<a href='SeeUsers'>Users</a>");
        out.println("<a href='#'>Trainer Reports</a>");
        out.println("</div></div>");
        out.println("<a href='#'>Student</a>");
        out.println("<a href='#' class='logout'>Logout</a>");
        out.println("</div>");
        out.println("<div class='content'>");
        out.println("<h2>Available Modules</h2>");
        out.println("<form method='get' action='SeeModules'>");
        out.println("<input type='text' name='search' placeholder='Search Modules...'>");
        out.println("</form>");
        out.println("<table>");
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>Module Code</th>");
        out.println("<th>Module Name</th>");
        out.println("<th>Department</th>");
        out.println("<th>Program</th>");
        out.println("<th>Level</th>");
        out.println("<th>Trainer ID</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");

        // Database connection and data retrieval
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String searchQuery = request.getParameter("search");
        String sql = "SELECT * FROM courses";
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            sql += " WHERE Module_code LIKE ? OR Module_name LIKE ? OR Department LIKE ? OR Program LIKE ? OR Level LIKE ? OR Trainer_id LIKE ?";
        }

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");
            pstmt = conn.prepareStatement(sql);

            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String search = "%" + searchQuery + "%";
                for (int i = 1; i <= 6; i++) {
                    pstmt.setString(i, search);
                }
            }

            rs = pstmt.executeQuery();

            // Fetching and displaying data
            while (rs.next()) {
                String moduleCode = rs.getString("Module_code");
                String moduleName = rs.getString("Module_name");
                String department = rs.getString("Department");
                String program = rs.getString("Program");
                String level = rs.getString("Level");
                String trainerId = rs.getString("Trainer_id");

                out.println("<tr>");
                out.println("<td>" + moduleCode + "</td>");
                out.println("<td>" + moduleName + "</td>");
                out.println("<td>" + department + "</td>");
                out.println("<td>" + program + "</td>");
                out.println("<td>" + level + "</td>");
                out.println("<td>" + trainerId + "</td>");
                out.println("</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace(out);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
