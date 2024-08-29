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

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        int userId = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String userType = request.getParameter("userType");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // SQL query to insert data into the users table
            String sql = "INSERT INTO users (UserID, Username, UserType, Password) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            pstmt.setString(2, username);
            pstmt.setString(3, userType);
            pstmt.setString(4, password); // Note: Hash passwords before storing them

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                out.println("<script>alert('User Created Successfully'); window.location='create_user.jsp';</script>");
            } else {
                out.println("<script>alert('Error: User Not Created'); window.location='create_user.jsp';</script>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<script>alert('An error occurred: " + e.getMessage() + "'); window.location='create_user.jsp';</script>");
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
