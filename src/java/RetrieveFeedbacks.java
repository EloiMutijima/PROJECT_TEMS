

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

@WebServlet("/RetrieveFeedbackServlet")
public class RetrieveFeedbacks extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT id, module_id, feedback FROM feedback";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            out.println("<html><body>");
            out.println("<h1>Feedback Data</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Module ID</th><th>Feedback</th></tr>");

            while (rs.next()) {
                int id = rs.getInt("id");
                String moduleId = rs.getString("module_id");
                String feedback = rs.getString("feedback");

                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + moduleId + "</td>");
                out.println("<td>" + feedback + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            rs.close();
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
}
