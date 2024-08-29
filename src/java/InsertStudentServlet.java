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

@WebServlet("/InsertStudentServlet")
public class InsertStudentServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            out.println("<h2>Error: All fields are required!</h2>");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "INSERT INTO users (Username, Password, UserType) VALUES (?, ?, 'Student')";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('User " + username + " registered successfully!');");
                out.println("window.location.href = 'login.jsp';");
                out.println("</script>");
            } else {
                out.println("<h2>Error: Unable to register user.</h2>");
            }

            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            out.println("<h2>Error: Unable to load database driver.</h2>");
            e.printStackTrace(out);
        } catch (SQLException e) {
            out.println("<h2>Error: Database connection failed.</h2>");
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
