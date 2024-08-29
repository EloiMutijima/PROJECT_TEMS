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

@WebServlet("/RetrieveFeedbackComments")
public class feedback extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
        String dbUser = "root";
        String dbPassword = ""; // Replace with your actual DB password

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // SQL query to select specific columns from the table
            String sql = "SELECT strengths, improvement_area FROM evaluatedmodule";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Set response content type
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            // Print the retrieved data
            out.println("<html><body>");
            out.println("<h2>Trainer Feedback Comments</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Strengths</th><th>Improvement Area</th></tr>");

            while (rs.next()) {
                String strengths = rs.getString("strengths");
                String improvementArea = rs.getString("improvement_area");

                out.println("<tr>");
                out.println("<td>" + strengths + "</td>");
                out.println("<td>" + improvementArea + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the error appropriately (e.g., log it, notify the user, etc.)

        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}