import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteProfileServlet")
public class DeleteProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Update with your DB password

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String regNo = request.getParameter("regNo");

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // SQL query to delete data
                String sqlDelete = "DELETE FROM student_profile WHERE Regno = ?";
                try (PreparedStatement statement = connection.prepareStatement(sqlDelete)) {
                    statement.setString(1, regNo);
                    statement.executeUpdate();
                }
            }

            // Redirect to the profile list page
           response.sendRedirect("student_list");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}