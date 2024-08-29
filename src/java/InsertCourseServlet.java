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

@WebServlet("/InsertCourseServlet")
public class InsertCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
        String username = "root";
        String password = "";

        // Retrieving form data from the request
        String moduleCode = request.getParameter("module_code");
        String moduleName = request.getParameter("module_name");
        String department = request.getParameter("department");
        String program = request.getParameter("program");
        String level = request.getParameter("level");
        String trainerId = request.getParameter("trainer_id");

        // SQL INSERT query
        String query = "INSERT INTO courses (Module_code, Module_name, Department, Program, Level, Trainer_id) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, username, password);

            // Prepare the SQL statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, moduleCode);
            statement.setString(2, moduleName);
            statement.setString(3, department);
            statement.setString(4, program);
            statement.setString(5, level);
            statement.setString(6, trainerId);

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                out.println("<h3>Course inserted successfully!</h3>");
                response.sendRedirect("new_module.jsp");
            } else {
                out.println("<h3>Error inserting course.</h3>");
            }

            // Close the statement and connection
            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h3>An error occurred: " + e.getMessage() + "</h3>");
        } finally {
            out.close();
        }
    }

    // Handles HTTP GET requests (optional if you only want to use POST)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
