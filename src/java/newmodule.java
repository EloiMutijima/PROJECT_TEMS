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

@WebServlet("/newmodule")
public class newmodule extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String semesterStr = request.getParameter("semester");
        String academic = request.getParameter("academic");
        String m_code = request.getParameter("moduleCode");
        String m_name = request.getParameter("moduleName");
        String department = request.getParameter("department");
        String trainer = request.getParameter("trainer");
        String program = request.getParameter("program");
        String level = request.getParameter("level");

        Connection conn = null;
        PreparedStatement pstmt = null;
        PrintWriter out = response.getWriter();

        try {
            // Convert semester to int
            int semester = Integer.parseInt(semesterStr);
            
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // SQL query to insert data into the table
            String sql = "INSERT INTO newmodule (Semester, Academic, M_code, M_name, Department, Trainer, Program, Level) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, semester);
            pstmt.setString(2, academic);
            pstmt.setString(3, m_code);
            pstmt.setString(4, m_name);
            pstmt.setString(5, department);
            pstmt.setString(6, trainer);
            pstmt.setString(7, program);
            pstmt.setString(8, level);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                out.println("<script>alert('Module Inserted')</script>");
            } else {
                out.println("<script>alert('Module not inserted')</script>");
            }
        } catch (NumberFormatException e) {
            out.println("<script>alert('Invalid semester value. Please enter a number.')</script>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("An error occurred: " + e.getMessage());
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