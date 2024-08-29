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

@WebServlet("/StudentRegistrationServlet")
public class StudentRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get the username, regno, and password from the request
        String regno = request.getParameter("regno");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // Check if the user already exists
            String checkUserSql = "SELECT * FROM users WHERE Username = ?";
            pstmt = conn.prepareStatement(checkUserSql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // User already exists, display error message
                out.println("<div class='alert error' style='background-color: red; color: white; padding: 10px;'>User already found!</div>");
                response.setHeader("Refresh", "3; URL=register.jsp");
            } else {
                // SQL query to insert the user data into the 'users' table
                String sql = "INSERT INTO users (Rol_id, RegNo, Username, Password) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);

                // Set parameters
                pstmt.setInt(1, 4); // Rol_id set to 4 for students
                pstmt.setString(2, regno);
                pstmt.setString(3, username);
                pstmt.setString(4, password);

                // Execute the insert operation
                int rowsInserted = pstmt.executeUpdate();

                // Check if the insertion was successful
                if (rowsInserted > 0) {
                    out.println("<div class='alert success' style='background-color: green; color: white; padding: 10px;'>Dear " + username + ", your registration is successful!</div>");
                    response.setHeader("Refresh", "3; URL=register.jsp");
                } else {
                    out.println("<div class='alert error' style='background-color: red; color: white; padding: 10px;'>Registration failed. Please try again.</div>");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<div class='alert error' style='background-color: red; color: white; padding: 10px;'>An error occurred: " + e.getMessage() + "</div>");
        } finally {
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
