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

@WebServlet("/RetrieveUsersServlet")
public class RetrieveUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            // SQL query to retrieve data from the users table
            String sql = "SELECT * FROM users";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Users List</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
            out.println("h1 { color: #333; text-align: center; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }");
            out.println("th { background-color: #002D72; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
            out.println("form { display: inline; }");
            out.println("input[type='submit'] { padding: 5px 10px; border: none; border-radius: 4px; cursor: pointer; }");
            out.println("input[type='submit']:first-of-type { background-color: #4CAF50; color: white; }"); // Edit button
            out.println("input[type='submit']:last-of-type { background-color: #f44336; color: white; }");  // Delete button
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>List of Users</h1>");
            out.println("<table>");
            out.println("<tr><th>Username</th><th>UserType</th><th>Password</th><th>Actions</th></tr>");

            // Loop through the result set and display the user data
            while (rs.next()) {
                String username = rs.getString("Username");
                String userType = rs.getString("UserType");
                String password = rs.getString("Password");

                out.println("<tr>");
                out.println("<td>" + username + "</td>");
                out.println("<td>" + userType + "</td>");
                out.println("<td>" + password + "</td>");
                out.println("<td>");
                // Edit button/form
                out.println("<form method='get' action='UpdateUserServlet'>");
                out.println("<input type='hidden' name='username' value='" + username + "'/>");
                out.println("<input type='submit' value='Edit'/ style='background-color:green;'>");
                out.println("</form>");
                // Delete button/form
                out.println("<form method='post' action='DeleteUserServlet'>");
                out.println("<input type='hidden' name='username' value='" + username + "'/>");
                out.println("<input type='submit' value='Delete' onclick='return confirm(\"Are you sure you want to delete this user?\");'/>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

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
}
