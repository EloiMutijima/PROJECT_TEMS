import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteFeedbackServlet")
public class DeleteFeedbacksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Use DatabaseUtils to get a connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // Retrieve the Trainer_id to delete
            String trainerId = request.getParameter("trainerId");
            
            // Query to delete feedback
            String query = "DELETE FROM replies WHERE Trainer_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, trainerId);
            ps.executeUpdate();

            response.sendRedirect("FetchFeedbackServlet");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
