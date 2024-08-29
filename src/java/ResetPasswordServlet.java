import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response type and output stream
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Get parameters from request
            String regNo = request.getParameter("regNo");
            String username = request.getParameter("username");
            String newPassword = request.getParameter("newPassword");

            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // Check if the user exists with provided credentials and has Rol_id of 4
            String query = "SELECT Username FROM users WHERE RegNo = ? AND Username = ? AND Rol_id = 4";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, regNo);
            stmt.setString(2, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // If user is valid, update the password
                String updateSQL = "UPDATE users SET Password = ? WHERE RegNo = ? AND Username = ? AND Rol_id = 4";
                stmt = conn.prepareStatement(updateSQL);
                stmt.setString(1, newPassword);
                stmt.setString(2, regNo);
                stmt.setString(3, username);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    // Password updated successfully
                    out.println("<html><body>");
                    out.println("<script>alert('Dear " + username + ", your password has changed.');</script>");
                    out.println("<script>window.location.href = 'loginpage.jsp';</script>");
                    out.println("</body></html>");
                } else {
                    // Failed to update password
                    out.println("<html><body>");
                    out.println("<script>alert('Error: Unable to update password.');</script>");
                    out.println("<script>window.location.href = 'resetpassword.jsp';</script>");
                    out.println("</body></html>");
                }
            } else {
                // Invalid credentials
                out.println("<html><body>");
                out.println("<script>alert('Invalid credentials. Password was not updated.');</script>");
                out.println("<script>window.location.href = 'reset.jsp';</script>");
                out.println("</body></html>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
            try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
