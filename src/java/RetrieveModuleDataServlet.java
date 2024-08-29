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

@WebServlet("/RetrieveModuleData")
public class RetrieveModuleDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
        String user = "root";
        String password = "";
        
        try (PrintWriter out = response.getWriter()) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);

                String sql = "SELECT Semester, Academic, M_code, M_name, Department, Trainer FROM newmodule";
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();

                // HTML Table
                out.println("<html><head><title>Module Data</title></head><body>");
                out.println("<h2>Module Data</h2>");
                out.println("<table border='1' style='width:100%; border-collapse:collapse;'>");
                out.println("<tr><th>Semester</th><th>Academic</th><th>Module Code</th><th>Module Name</th><th>Department</th><th>Trainer</th></tr>");
                
                while (rs.next()) {
                    String semester = rs.getString("Semester");
                    String academic = rs.getString("Academic");
                    String mCode = rs.getString("M_code");
                    String mName = rs.getString("M_name");
                    String department = rs.getString("Department");
                    String trainer = rs.getString("Trainer");

                    out.println("<tr>");
                    out.println("<td>" + semester + "</td>");
                    out.println("<td>" + academic + "</td>");
                    out.println("<td>" + mCode + "</td>");
                    out.println("<td>" + mName + "</td>");
                    out.println("<td>" + department + "</td>");
                    out.println("<td>" + trainer + "</td>");
                    out.println("</tr>");
                }
                
                out.println("</table>");
                out.println("</body></html>");
            } catch (ClassNotFoundException | SQLException e) {
                out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
            } finally {
                if (rs != null) try { rs.close(); } catch (SQLException e) { /* ignored */ }
                if (stmt != null) try { stmt.close(); } catch (SQLException e) { /* ignored */ }
                if (conn != null) try { conn.close(); } catch (SQLException e) { /* ignored */ }
            }
        }
    }
}
