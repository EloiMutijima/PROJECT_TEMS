import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddTrainerServlet")
public class AddTrainerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Update with your DB password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String trainerId = request.getParameter("trainerId");
        String trainerName = request.getParameter("trainerName");
        String phone = request.getParameter("phone");
        String department = request.getParameter("department");
        String options = request.getParameter("options");
        String module = request.getParameter("module");
        String email = request.getParameter("email");

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Insert data into the trainers table including Trainer_id
                String sqlInsert = "INSERT INTO trainers (Trainer_id, Trainer, Tel, Departments, Options, Modules, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sqlInsert)) {
                    statement.setString(1, trainerId);
                    statement.setString(2, trainerName);
                    statement.setString(3, phone);
                    statement.setString(4, department);
                    statement.setString(5, options);
                    statement.setString(6, module);
                    statement.setString(7, email);

                    int rowsInserted = statement.executeUpdate();

                    if (rowsInserted > 0) {
                        out.println("<div class='message success'>Trainer added successfully!</div>");
                    } else {
                        out.println("<div class='message error'>Error adding trainer. Please try again.</div>");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<div class='message error'>MySQL JDBC Driver not found.</div>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<div class='message error'>Error: " + e.getMessage() + "</div>");
        } finally {
            out.println("<a href='TrainerForm.jsp'>Back to Add Trainer Form</a>");
            out.close();
        }
    }
}
