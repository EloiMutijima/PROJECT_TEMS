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

@WebServlet("/studentProfile")
public class student_list extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Update with your DB password

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Retrieve and display data
                String sqlSelect = "SELECT * FROM student_profile";
                try (PreparedStatement statement = connection.prepareStatement(sqlSelect)) {
                    ResultSet resultSet = statement.executeQuery();

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<meta charset='UTF-8'>");
                    out.println("<title>Student Profiles</title>");
                    out.println("<style>");
                    out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
                    out.println("h3 { color: #333; }");
                    out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
                    out.println("th, td { padding: 12px; border: 1px solid #ddd; text-align: left; }");
                    out.println("th { background-color: #002D72; color: white; }");
                    out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
                    out.println("a { color: #1E90FF; text-decoration: none; }");
                    out.println("a:hover { text-decoration: underline; }");
                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h3>Student Profiles:</h3>");
                    out.println("<table>");
                    out.println("<tr><th>Regno</th><th>FirstName</th><th>LastName</th><th>Department</th><th>Options</th><th>Level</th><th>Email</th><th>Date of Birth</th><th>Actions</th></tr>");
                    while (resultSet.next()) {
                        out.println("<tr>");
                        out.println("<td>" + resultSet.getString("Regno") + "</td>");
                        out.println("<td>" + resultSet.getString("FirstName") + "</td>");
                        out.println("<td>" + resultSet.getString("LastName") + "</td>");
                        out.println("<td>" + resultSet.getString("Department") + "</td>");
                        out.println("<td>" + resultSet.getString("Options") + "</td>");
                        out.println("<td>" + resultSet.getString("Level") + "</td>");
                        out.println("<td>" + resultSet.getString("Email") + "</td>");
                        out.println("<td>" + resultSet.getDate("Dates") + "</td>");
                        out.println("<td><a href='EditProfileServlet?regNo=" + resultSet.getString("Regno") + "'>Edit</a> | <a href='DeleteProfileServlet?regNo=" + resultSet.getString("Regno") + "'>Delete</a></td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h2>MySQL JDBC Driver not found.</h2>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            out.close();
        }
    }
}
