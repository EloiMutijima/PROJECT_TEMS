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
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get the username and password from the request
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

            // SQL query to check user credentials and get their role
            String sql = "SELECT Rol_id FROM users WHERE Username = ? AND Password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int rolId = rs.getInt("Rol_id");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("rolId", rolId);

                // Redirect based on Rol_id
                switch (rolId) {
                    case 1:
                        response.sendRedirect("adminpage.jsp");
                        break;
                    case 2:
                        response.sendRedirect("evaluators.jsp");
                        break;
                    case 3:
                        response.sendRedirect("trainerpage.jsp");
                        break;
                    case 4:
                        response.sendRedirect("studentpage.jsp");
                        break;
                    default:
                        out.println("<h2>Invalid Role!</h2>");
                }
            } else {
                out.println("<h2>Invalid Username or Password!</h2>");
                response.setHeader("Refresh", "1; URL=loginpage.jsp");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<h2>An error occurred: " + e.getMessage() + "</h2>");
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
