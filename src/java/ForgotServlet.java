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

@WebServlet("/ForgotServlet")
public class ForgotServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");

        if (username == null || username.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Error: All fields are required!');");
            out.println("window.location.href = 'forgot.jsp';");
            out.println("</script>");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String checkUserSql = "SELECT UserType FROM users WHERE Username = ?";
            PreparedStatement checkUserPstmt = conn.prepareStatement(checkUserSql);
            checkUserPstmt.setString(1, username);

            ResultSet rs = checkUserPstmt.executeQuery();

            if (rs.next()) {
                String userType = rs.getString("UserType");

                if ("Student".equalsIgnoreCase(userType)) {
                    String updateSql = "UPDATE users SET Password = ? WHERE Username = ?";
                    PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
                    updatePstmt.setString(1, newPassword);
                    updatePstmt.setString(2, username);

                    int rows = updatePstmt.executeUpdate();

                    if (rows > 0) {
                        out.println("<script type='text/javascript'>");
                        out.println("alert('Password reset successfully for user: " + username + ".');");
                        out.println("window.location.href = 'login.jsp';");
                        out.println("</script>");
                    } else {
                        out.println("<script type='text/javascript'>");
                        out.println("alert('Error: Unable to reset password.');");
                        out.println("window.location.href = 'forgot.jsp';");
                        out.println("</script>");
                    }

                    updatePstmt.close();
                } else {
                    out.println("<script type='text/javascript'>");
                    out.println("alert('Error: Only students can reset their password.');");
                    out.println("window.location.href = 'forgot.jsp';");
                    out.println("</script>");
                }
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('Error: Invalid username.');");
                out.println("window.location.href = 'forgot.jsp';");
                out.println("</script>");
            }

            rs.close();
            checkUserPstmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            out.println("<h2>Error: Unable to load database driver.</h2>");
            e.printStackTrace(out);
        } catch (SQLException e) {
            out.println("<h2>Error: Database connection failed.</h2>");
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
