import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModuleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Map<String, String>> modules = new ArrayList<>();

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "password");

            // SQL query to retrieve modules
            String sql = "SELECT Module_code, Module_name, Department, Program, Level, Trainer_id FROM courses";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            // Process the ResultSet and store it in a List
            while (rs.next()) {
                Map<String, String> module = new HashMap<>();
                module.put("Module_code", rs.getString("Module_code"));
                module.put("Module_name", rs.getString("Module_name"));
                module.put("Department", rs.getString("Department"));
                module.put("Program", rs.getString("Program"));
                module.put("Level", rs.getString("Level"));
                module.put("Trainer_id", rs.getString("Trainer_id"));
                modules.add(module);
            }

            // Set the List as a request attribute
            request.setAttribute("modules", modules);

            // Forward to the JSP page
            request.getRequestDispatcher("evaluation.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
