import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateTrainerServlet")
public class UpdateTrainerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Replace with your actual DB password

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String department = request.getParameter("department");
        String options = request.getParameter("options");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String updateSql = "UPDATE trainer_profile SET FirstName=?, LastName=?, department=?, options=?, email=?, phone=? WHERE id=?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, department);
            pstmt.setString(4, options);
            pstmt.setString(5, email);
            pstmt.setString(6, phone);
            pstmt.setInt(7, id);
            pstmt.executeUpdate();

            response.sendRedirect("TrainerProfileServlet");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
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
