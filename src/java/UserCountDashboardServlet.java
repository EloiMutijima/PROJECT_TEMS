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

@WebServlet("/DashboardServlet")
public class UserCountDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalUsers = 0;
        int trainerCount = 0;
        int moduleCount = 0;
        int studentCount = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Database connection details
            String jdbcURL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
            String dbUser = "root"; // Change to your DB username
            String dbPassword = ""; // Change to your DB password

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            if (conn != null) {
                // Count total users
                String sqlTotalUsers = "SELECT COUNT(*) AS count FROM users";
                stmt = conn.prepareStatement(sqlTotalUsers);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    totalUsers = rs.getInt("count");
                }

                // Count trainers
                String sqlTrainers = "SELECT COUNT(*) AS count FROM users WHERE Rol_id = 3";
                stmt = conn.prepareStatement(sqlTrainers);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    trainerCount = rs.getInt("count");
                }

                // Count modules
                String sqlModules = "SELECT COUNT(*) AS count FROM evaluations";
                stmt = conn.prepareStatement(sqlModules);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    moduleCount = rs.getInt("count");
                }

                // Count students
                String sqlStudents = "SELECT COUNT(*) AS count FROM users WHERE Rol_id = 4";
                stmt = conn.prepareStatement(sqlStudents);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    studentCount = rs.getInt("count");
                }
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Generate HTML response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Admin Dashboard - Trainer Evaluation Management System</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
        out.println(".dashboard-cards { display: flex; flex-wrap: wrap; justify-content: space-around; margin: 20px; }");
        out.println(".card { background-color: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); flex: 1; margin: 10px; text-align: center; max-width: 200px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='dashboard-cards'>");

        // Card 1: Total Users
        out.println("<div class='card'>");
        out.println("<h3>Total Users</h3>");
        out.println("<p>" + totalUsers + "</p>");
        out.println("</div>");

        // Card 2: Trainers Information
        out.println("<div class='card'>");
        out.println("<h3>Total Trainers</h3>");
        out.println("<p>" + trainerCount + "</p>");
        out.println("</div>");

        // Card 3: Modules Information
        out.println("<div class='card'>");
        out.println("<h3>Evaluations</h3>");
        out.println("<p>" + moduleCount + "</p>");
        out.println("</div>");

        // Card 4: Students Count
        out.println("<div class='card'>");
        out.println("<h3>Total Students</h3>");
        out.println("<p>" + studentCount + "</p>");
        out.println("</div>");

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
