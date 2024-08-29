import java.io.IOException;
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

@WebServlet("/InsertUsersServlet")
public class InsertUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String userType = request.getParameter("userType");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String trainerId = request.getParameter("trainerId");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // Check if the username already exists
            String checkUserQuery = "SELECT * FROM users WHERE Username = ?";
            stmt = conn.prepareStatement(checkUserQuery);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // User already exists
                response.getWriter().println("<script>alert('User Already Found');window.location='new_user.jsp';</script>");
            } else {
                // Insert the user into the database
                String insertQuery = "INSERT INTO users (Rol_id, RegNo, Username, Password, Trainer_id) VALUES (?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertQuery);
                
                stmt.setString(1, userType); // Set Role ID
                if ("3".equals(userType)) {
                    stmt.setNull(2, java.sql.Types.VARCHAR); // RegNo is NULL
                    stmt.setString(5, trainerId); // Trainer ID is set
                } else {
                    stmt.setNull(2, java.sql.Types.VARCHAR); // RegNo is NULL
                    stmt.setNull(5, java.sql.Types.VARCHAR); // Trainer ID is NULL
                }
                stmt.setString(3, username); // Set Username
                stmt.setString(4, password); // Set Password
                
                stmt.executeUpdate();
                
                // User successfully inserted
                response.getWriter().println("<script>alert('User Created Successfully');window.location='new_user.jsp';</script>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<script>alert('Error Occurred');window.location='new_user.jsp';</script>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
