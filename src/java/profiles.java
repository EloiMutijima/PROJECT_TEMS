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
public class profiles extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Update with your DB password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String regNo = request.getParameter("regNo");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String department = request.getParameter("department");
        String options = request.getParameter("options");
        String level = request.getParameter("level");
        String email = request.getParameter("email");
        String dateOfBirth = request.getParameter("dateOfBirth");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Insert data into the database
                String sqlInsert = "INSERT INTO student_profile (Regno, FirstName, LastName, Department, Options, Level, Email, Dates) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sqlInsert)) {
                    statement.setString(1, regNo);
                    statement.setString(2, firstName);
                    statement.setString(3, lastName);
                    statement.setString(4, department);
                    statement.setString(5, options);
                    statement.setString(6, level);
                    statement.setString(7, email);
                    statement.setDate(8, java.sql.Date.valueOf(dateOfBirth));

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        out.println("<h2>Profile created successfully!</h2>");
                    } else {
                        out.println("<h2>Error creating profile.</h2>");
                    }
                }

                // Retrieve and display data
                String sqlSelect = "SELECT * FROM student_profile WHERE Regno = ?";
                try (PreparedStatement statement = connection.prepareStatement(sqlSelect)) {
                    statement.setString(1, regNo);
                    ResultSet resultSet = statement.executeQuery();

                    out.println("<h3>Student Profile:</h3>");
                    out.println("<table border='1'><tr><th>Regno</th><th>FirstName</th><th>LastName</th><th>Department</th><th>Options</th><th>Level</th><th>Email</th><th>Date of Birth</th></tr>");
                    while (resultSet.next()) {
                        out.println("<tr><td>" + resultSet.getString("Regno") + "</td>");
                        out.println("<td>" + resultSet.getString("FirstName") + "</td>");
                        out.println("<td>" + resultSet.getString("LastName") + "</td>");
                        out.println("<td>" + resultSet.getString("Department") + "</td>");
                        out.println("<td>" + resultSet.getString("Options") + "</td>");
                        out.println("<td>" + resultSet.getString("Level") + "</td>");
                        out.println("<td>" + resultSet.getString("Email") + "</td>");
                        out.println("<td>" + resultSet.getDate("Dates") + "</td></tr>");
                    }
                    out.println("</table>");
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