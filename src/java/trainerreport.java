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

@WebServlet("/RetrieveBasicData")
public class trainerreport extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
        String dbUser = "root";
        String dbPassword = ""; // Replace with your actual DB password

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // SQL query to select specific columns from the table
            String sql = "SELECT id, semester, M_code, academic_year, module_name, trainer, department, option_field FROM evaluatedmodule";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Set response content type
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            // Print the retrieved data with CSS styles
            out.println("<html><head>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
            out.println("h2 { color: #333; text-align: center; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("th { background-color: #002D72; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<h2>Basic Feedback Data</h2>");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Semester</th><th>Module Code</th><th>Academic Year</th><th>Module Name</th><th>Trainer</th><th>Department</th><th>Option</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String semester = rs.getString("semester");
                String M_code = rs.getString("M_code");
                String academicYear = rs.getString("academic_year");
                String moduleName = rs.getString("module_name");
                String trainer = rs.getString("trainer");
                String department = rs.getString("department");
                String optionField = rs.getString("option_field");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + semester + "</td>");
                out.println("<td>" + M_code + "</td>");
                out.println("<td>" + academicYear + "</td>");
                out.println("<td>" + moduleName + "</td>");
                out.println("<td>" + trainer + "</td>");
                out.println("<td>" + department + "</td>");
                out.println("<td>" + optionField + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</body></html>");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the error appropriately (e.g., log it, notify the user, etc.)

        } finally {
            // Close resources
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
