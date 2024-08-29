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

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Explicitly set the Rol_id to 1 for Admin
        String rolId = "1";
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // Selecting the user by Rol_id = 1 (Admin)
            String query = "SELECT * FROM users WHERE Rol_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, rolId);
            rs = ps.executeQuery();

            if (rs.next()) {
                // User exists, proceed with update
                query = "UPDATE users SET Username = ?, Password = ? WHERE Rol_id = ?";
                ps = con.prepareStatement(query);
                ps.setString(1, newUsername);
                ps.setString(2, newPassword);
                ps.setString(3, rolId);

                int rowsUpdated = ps.executeUpdate();
                if (rowsUpdated > 0) {
                    // If update is successful, show a pop-up and redirect
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Admin user updated successfully.');");
                    out.println("location='SeeUsers';");
                    out.println("</script>");
                } else {
                    // If update fails
                    out.println("<p style='color: red;'>Error: Unable to update Admin user.</p>");
                }
            } else {
                // Admin user with Rol_id = 1 does not exist
                out.println("<p style='color: red;'>Error: Admin user with Rol_id " + rolId + " not found.</p>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<p style='color: red;'>Database error: " + e.getMessage() + "</p>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                out.println("<p style='color: red;'>Error closing database resources: " + e.getMessage() + "</p>");
            }
        }
    }
}
