import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form
        String regNo = request.getParameter("regNo");
        String studName = request.getParameter("stud_name");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String department = request.getParameter("department");
        String options = request.getParameter("option");
        String level = request.getParameter("level");
        
        // Database connection variables
        String url = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
        String username = "root";
        String password = "";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            Connection conn = DriverManager.getConnection(url, username, password);
            
            // SQL query to update student data
            String sql = "UPDATE students SET Stud_name = ?, Email = ?, Tel = ?, Department = ?, Options = ?, Level = ? WHERE Regno = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, studName);
            stmt.setString(2, email);
            stmt.setString(3, tel);
            stmt.setString(4, department);
            stmt.setString(5, options);
            stmt.setString(6, level);
            stmt.setString(7, regNo);
            
            // Execute the update
            int rowsUpdated = stmt.executeUpdate();
            
            // Provide feedback to the user
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Profile Update</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; text-align: center; }");
            out.println(".message { margin: auto; padding: 20px; max-width: 600px; background-color: white; border: 1px solid #ddd; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
            out.println(".message.success { color: green; }");
            out.println(".message.error { color: red; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            if (rowsUpdated > 0) {
                out.println("<div class='message success'>Profile updated successfully!</div>");
            } else {
                out.println("<div class='message error'>Failed to update profile. Please try again.</div>");
            }
            
            out.println("<a href='student_list_evaluation.jsp'>Back to Profile</a>");
            out.println("</body>");
            out.println("</html>");
            
            // Close connections
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<div class='message error'>An error occurred: " + e.getMessage() + "</div>");
        }
    }
}