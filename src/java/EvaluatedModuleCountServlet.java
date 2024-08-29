import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EvaluatedModuleCountServlet")
public class EvaluatedModuleCountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // Prepare the SQL query
            String query = "SELECT COUNT(*) AS evaluatedModuleCount FROM evaluations";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            int evaluatedModuleCount = 0;
            if (rs.next()) {
                evaluatedModuleCount = rs.getInt("evaluatedModuleCount");
            }

            // Generate the card HTML
            out.println("<div style='background-color: darkblue; color: white; padding: 20px; border-radius: 10px; width: fit-content; float: right;margin-top:-210px;'>");
            out.println("<h3>Evaluations: " + evaluatedModuleCount + "</h3>");
            out.println("</div>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
