import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReplyServlet")
public class ReplyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reply = request.getParameter("reply");
        String trainerId = request.getParameter("trainer_id");
        String username = request.getParameter("username");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // SQL query to insert reply into the replies table
            String sql = "INSERT INTO replies (Trainer_id, Feedback) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, trainerId);
            pstmt.setString(2, reply);
            pstmt.executeUpdate();

            // Redirect to the page with a success message
            response.sendRedirect("trainerpage.jsp?replySuccess=true");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
