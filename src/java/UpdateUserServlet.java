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

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // SQL query to retrieve user data by username
            String sql = "SELECT * FROM users WHERE Username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String userType = rs.getString("UserType");
                String password = rs.getString("Password");

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Update User</title>");
                out.println("<style>");
                out.println("body {");
                out.println("    font-family: Arial, sans-serif;");
                out.println("    margin: 0;");
                out.println("    padding: 0;");
                out.println("}");
                out.println("header {");
                out.println("    background-color: #002D72;");
                out.println("    color: white;");
                out.println("    padding: 10px 0;");
                out.println("    text-align: center;");
                out.println("}");
                out.println("nav {");
                out.println("    background-color: grey;");
                out.println("}");
                out.println("nav ul {");
                out.println("    list-style-type: none;");
                out.println("    margin: 0;");
                out.println("    padding: 0;");
                out.println("    display: flex;");
                out.println("    justify-content: center;");
                out.println("    align-items: center;");
                out.println("}");
                out.println("nav ul li {");
                out.println("    margin: 0 15px;");
                out.println("    position: relative;");
                out.println("}");
                out.println("nav ul li a {");
                out.println("    color: white;");
                out.println("    text-decoration: none;");
                out.println("    padding: 10px 15px;");
                out.println("    display: block;");
                out.println("}");
                out.println("nav ul li ul {");
                out.println("    display: none;");
                out.println("    position: absolute;");
                out.println("    background-color: grey;");
                out.println("    top: 100%;");
                out.println("    left: 0;");
                out.println("    padding: 0;");
                out.println("    list-style-type: none;");
                out.println("    z-index: 1000;");
                out.println("}");
                out.println("nav ul li:hover ul {");
                out.println("    display: block;");
                out.println("}");
                out.println("nav ul li ul li {");
                out.println("    width: 150px;");
                out.println("}");
                out.println("nav ul li ul li a {");
                out.println("    padding: 10px;");
                out.println("}");
                out.println("main {");
                out.println("    padding: 20px;");
                out.println("    text-align: center;");
                out.println("}");
                out.println(".form-container {");
                out.println("    max-width: 600px;");
                out.println("    margin: auto;");
                out.println("    padding: 20px;");
                out.println("    background-color: #f9f9f9;");
                out.println("    border: 1px solid #ddd;");
                out.println("    border-radius: 8px;");
                out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
                out.println("}");
                out.println(".form-container h1 {");
                out.println("    margin-top: 0;");
                out.println("}");
                out.println(".form-container input[type='text'], .form-container input[type='password'] {");
                out.println("    width: 100%;");
                out.println("    padding: 10px;");
                out.println("    margin: 5px 0 10px 0;");
                out.println("    border: 1px solid #ccc;");
                out.println("    border-radius: 4px;");
                out.println("}");
                out.println(".form-container input[type='submit'] {");
                out.println("    background-color: #002D72;");
                out.println("    color: white;");
                out.println("    border: none;");
                out.println("    padding: 10px 20px;");
                out.println("    border-radius: 4px;");
                out.println("    cursor: pointer;");
                out.println("}");
                out.println(".form-container input[type='submit']:hover {");
                out.println("    background-color: #004080;");
                out.println("}");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<header><h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1></header>");
                out.println("<main>");
                out.println("<div class='form-container'>");
                out.println("<h1>Update User</h1>");
                out.println("<form method='post' action='UpdateUserServlet'>");
                out.println("Username: <input type='text' name='username' value='" + username + "' readonly/><br>");
                out.println("User Type: <input type='text' name='userType' value='" + userType + "'/><br>");
                out.println("Password: <input type='password' name='password' value='" + password + "'/><br>");
                out.println("<input type='submit' value='Update'/>");
                out.println("</form>");
                out.println("</div>");
                out.println("</main>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("<script>alert('User not found.'); window.location='RetrieveUsersServlet';</script>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<script>alert('An error occurred: " + e.getMessage() + "');</script>");
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String userType = request.getParameter("userType");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // SQL query to update user data
            String sql = "UPDATE users SET UserType = ?, Password = ? WHERE Username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userType);
            pstmt.setString(2, password); // Note: Hash passwords before storing them
            pstmt.setString(3, username);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                response.sendRedirect("RetrieveUsersServlet");
            } else {
                response.getWriter().println("<script>alert('Error: User Not Updated'); window.location='RetrieveUsersServlet';</script>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<script>alert('An error occurred: " + e.getMessage() + "');</script>");
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
