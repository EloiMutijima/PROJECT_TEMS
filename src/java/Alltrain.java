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

@WebServlet("/Alltrain")
public class Alltrain extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Replace with your actual DB password

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Trainer Profiles</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; }");
        out.println("header { background-color: #002D72; color: white; padding: 10px 0; text-align: center; }");
        out.println("nav { background-color: grey; }");
        out.println("nav ul { list-style-type: none; margin: 0; padding: 0; display: flex; justify-content: center; }");
        out.println("nav ul li { margin: 0 15px; position: relative; }");
        out.println("nav ul li a { color: white; text-decoration: none; padding: 10px 15px; display: block; }");
        out.println("nav ul li ul { display: none; position: absolute; background-color: grey; top: 100%; left: 0; padding: 0; list-style-type: none; z-index: 1000; }");
        out.println("nav ul li:hover ul { display: block; }");
        out.println("nav ul li ul li { width: 150px; }");
        out.println("nav ul li ul li a { padding: 10px; }");
        out.println(".logout { color: red; }");
        out.println("main { padding: 20px; text-align: center; }");
        out.println("main img { max-width: 100%; height: auto; }");
        out.println("footer { background-color: #002D72; color: white; text-align: center; height: 100px; padding: 20px; }");
        out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
        out.println("th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }");
        out.println("th { background-color: #002D72; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
        out.println("a { color: #1E90FF; text-decoration: none; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        // Header
        out.println("<header>");
        out.println("<h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>");
        out.println("</header>");

        // Navigation
        out.println("<nav>");
            out.println("<ul>");
            out.println("<li><a href='evaluators.jsp'>Home</a></li>");
            out.println("<li><a href='student_list'>Student List</a></li>");
            out.println("<li><a href='Alltrain'>Trainer</a></li>");
            out.println("<li><a href='new_module.jsp'>Evaluation Form</a></li>");
            out.println("<li><a href='FeedbackServlet'>View Reports</a></li>");
            out.println("<li><a href='Trainer_feedbacks.jsp'>Trainer Feedbacks</a></li>");
            out.println("<li><a href='login.jsp' class='logout'>Logout</a></li>");
            out.println("</ul>");
            out.println("</nav>");
        // Main content
        out.println("<main>");
        out.println("<h1>Trainer Profiles</h1>");
        out.println("<table>");
        out.println("<tr><th>Id</th><th>FirstName</th><th>LastName</th><th>Department</th><th>Options</th><th>Email</th><th>Phone</th></tr>");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String selectSql = "SELECT * FROM trainer_profile";
            pstmt = conn.prepareStatement(selectSql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString("FirstName") + "</td>");
                out.println("<td>" + rs.getString("LastName") + "</td>");
                out.println("<td>" + rs.getString("department") + "</td>");
                out.println("<td>" + rs.getString("options") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("phone") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        out.println("</main>");

        // Footer
        out.println("<footer>");
        out.println("Trainer Evaluation Management System all rights reserved");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }
}
