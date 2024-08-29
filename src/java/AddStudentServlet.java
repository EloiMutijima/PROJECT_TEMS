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

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Update with your DB password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String stud_name = request.getParameter("stud_name");
        String regNo = request.getParameter("Regno");
        String email = request.getParameter("email");
        String tel = request.getParameter("Tel");
        String department = request.getParameter("Department");
        String options = request.getParameter("Options");
        String level = request.getParameter("level");

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // SQL query to insert data into students table
                String sqlInsert = "INSERT INTO students (RegNo, Stud_name, email, Tel, Department, Options, Level) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sqlInsert)) {
                    // Set the parameters for the query
                    statement.setString(1, regNo);
                    statement.setString(2, stud_name);
                    statement.setString(3, email);
                    statement.setString(4, tel);
                    statement.setString(5, department);
                    statement.setString(6, options);
                    statement.setString(7, level);

                    // Execute the query
                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        out.println("<h3>Student added successfully!</h3>");
                        out.println("<a href='add_student.jsp'>Add another student</a> | <a href='student_list_evaluator'>View Student List</a>");
                    } else {
                        out.println("<h3>Failed to add student. Please try again.</h3>");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<h3>Error: Unable to connect to the database. Please check your connection parameters.</h3>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h3>Error: Unable to load database driver. Ensure that the MySQL JDBC driver is included in your project.</h3>");
        }
    }
}
