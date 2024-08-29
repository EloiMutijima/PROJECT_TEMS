import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/trainerprofile")
public class trainerprofile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String department = request.getParameter("department");
        String option = request.getParameter("option");
        String email = request.getParameter("email");
        String phone = request.getParameter("number");

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Database connection settings
            String dbURL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
            String dbUser = "root";
            String dbPass = "";

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            // SQL query to insert data into the database
            String sql = "INSERT INTO trainer_profile (Id, FirstName, LastName, Department, Options, Email, Phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, department);
            stmt.setString(5, option);
            stmt.setString(6, email);
            stmt.setString(7, phone);

            // Execute the insertion
            stmt.executeUpdate();

            response.getWriter().println("Trainer profile created successfully!");
            response.sendRedirect("admins.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error creating trainer profile: " + e.getMessage());
        } finally {
            // Close the resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}