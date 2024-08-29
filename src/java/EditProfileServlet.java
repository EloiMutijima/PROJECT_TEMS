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

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regNo = request.getParameter("regNo");
        
        // Database connection variables
        String url = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
        String username = "root";
        String password = "";
        
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // SQL query to retrieve data
            String sql = "SELECT * FROM students WHERE Regno = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, regNo);
            ResultSet rs = stmt.executeQuery();
            
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            if (rs.next()) {
                // Display the current data for editing
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Edit Profile</title>");
                out.println("<style>");
                out.println("body {");
                out.println("    font-family: Arial, sans-serif;");
                out.println("    margin: 0;");
                out.println("    padding: 0;");
                out.println("    background-color: #f4f4f4;");
                out.println("}");
                out.println("header {");
                out.println("    background-color: #002D72;");
                out.println("    color: white;");
                out.println("    padding: 10px 0;");
                out.println("    text-align: center;");
                out.println("}");
                out.println("main {");
                out.println("    padding: 20px;");
                out.println("    text-align: center;");
                out.println("}");
                out.println(".form-container {");
                out.println("    max-width: 600px;");
                out.println("    margin: auto;");
                out.println("    padding: 20px;");
                out.println("    background-color: white;");
                out.println("    border: 1px solid #ddd;");
                out.println("    border-radius: 8px;");
                out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
                out.println("}");
                out.println(".form-container h1 {");
                out.println("    margin-top: 0;");
                out.println("}");
                out.println(".form-container input[type='text'] {");
                out.println("    width: 100%;");
                out.println("    padding: 10px;");
                out.println("    margin: 5px 0 10px 0;");
                out.println("    border: 1px solid #ccc;");
                out.println("    border-radius: 4px;");
                out.println("}");
                out.println(".form-container input[type='submit'] {");
                out.println("    background-color: #002D72;");
                out.println("    color: white;");
                out.println("    border: none;");
                out.println("    padding: 10px 20px;");
                out.println("    border-radius: 4px;");
                out.println("    cursor: pointer;");
                out.println("}");
                out.println(".form-container input[type='submit']:hover {");
                out.println("    background-color: #004080;");
                out.println("}");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<header><h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1></header>");
                out.println("<main>");
                out.println("<div class='form-container'>");
                out.println("<h1>Edit Profile</h1>");
                out.println("<form action='UpdateProfileServlet' method='post'>");
                out.println("<input type='hidden' name='regNo' value='" + rs.getString("Regno") + "'/>");
                out.println("First Name: <input type='text' name='stud_name' value='" + rs.getString("stud_name") + "'/><br/>");
                  out.println("Email: <input type='text' name='email' value='" + rs.getString("email") + "'/><br/>");
                   out.println("Level: <input type='text' name='level' value='" + rs.getString("Tel") + "'/><br/>");
                out.println("Department: <input type='text' name='department' value='" + rs.getString("Department") + "'/><br/>");
                out.println("Options: <input type='text' name='option' value='" + rs.getString("Options") + "'/><br/>");
                out.println("Level: <input type='text' name='level' value='" + rs.getString("Level") + "'/><br/>");
                out.println("<input type='submit' value='Update'/>");
                out.println("</form>");
                out.println("</div>");
                out.println("</main>");
                out.println("</body>");
                out.println("</html>");
            }
            
            // Close connections
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
