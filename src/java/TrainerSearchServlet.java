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

@WebServlet("/TrainerSearchServlet")
public class TrainerSearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // HTML form to enter Trainer ID
        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Trainer Search</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
        out.println("h2 { color: darkblue; }");
        out.println("form { margin: 20px; }");
        out.println("label { margin-right: 10px; }");
        out.println("input[type='text'] { padding: 10px; margin-right: 10px; width: 300px; border: 1px solid #ccc; border-radius: 4px; }");
        out.println("input[type='submit'] { padding: 10px 20px; background-color: darkblue; color: white; border: none; border-radius: 4px; cursor: pointer; }");
        out.println("input[type='submit']:hover { background-color: navy; }");
        out.println("table { width: 100%; border-collapse: collapse; margin: 20px 0; }");
        out.println("table, th, td { border: 1px solid #ddd; }");
        out.println("th, td { padding: 12px; text-align: left; }");
        out.println("th { background-color: darkblue; color: white; }");
        out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
        out.println("tr:hover { background-color: #ddd; }");
        out.println(".no-record { color: red; }");
        out.println("</style>");
        out.println("</head><body>");
        
        out.println("<h2>Search Trainer Reports</h2>");
        out.println("<form action='TrainerSearchServlet' method='get'>");
        out.println("<label for='trainer_id'>Enter Trainer ID:</label>");
        out.println("<input type='text' id='trainer_id' name='trainer_id'>");
        out.println("<input type='submit' value='Search'>");
        out.println("</form>");
        
        String trainerId = request.getParameter("trainer_id");
        
        if (trainerId != null && !trainerId.isEmpty()) {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String url = "jdbc:mysql://localhost:3306/trainer_evaluation_system"; // Replace with your DB connection details
            String user = "root";
            String password = "";
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
                
                String sql = "SELECT * FROM evaluations WHERE Trainer_id = ?";
                stmt = conn.prepareStatement(sql);
                
                stmt.setString(1, trainerId);
                rs = stmt.executeQuery();
                
                if (!rs.isBeforeFirst()) { // Check if ResultSet is empty
                    out.println("<p class='no-record'>No records found for Trainer ID: " + trainerId + "</p>");
                } else {
                    out.println("<h2>Trainer Report for ID: " + trainerId + "</h2>");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th>ID</th><th>Module Code</th><th>Module Name</th><th>Department</th>");
                    out.println("<th>Trainer ID</th><th>Classroom Setting</th><th>Learning Consumables</th>");
                    out.println("<th>PPE Availability</th><th>Course Objectives</th><th>Learning Units</th>");
                    out.println("<th>Learning Activities</th><th>Punctuality</th><th>Feedback Encouragement</th>");
                    out.println("<th>Content Mastery</th><th>Instructional Technology</th><th>Active Participation</th>");
                    out.println("<th>Communication Skills</th><th>Assessments Provided</th><th>Feedback Provision</th>");
                    out.println("<th>Strengths</th><th>Improvement Areas</th>");
                    out.println("</tr>");
                    
                    while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td>" + rs.getInt("id") + "</td>");
                        out.println("<td>" + rs.getString("Module_code") + "</td>");
                        out.println("<td>" + rs.getString("Module_name") + "</td>");
                        out.println("<td>" + rs.getString("Department") + "</td>");
                        out.println("<td>" + rs.getString("Trainer_id") + "</td>");
                        out.println("<td>" + rs.getInt("classroom_setting") + "</td>");
                        out.println("<td>" + rs.getInt("learning_consumables") + "</td>");
                        out.println("<td>" + rs.getInt("ppe_availability") + "</td>");
                        out.println("<td>" + rs.getInt("course_objectives") + "</td>");
                        out.println("<td>" + rs.getInt("learning_units") + "</td>");
                        out.println("<td>" + rs.getInt("learning_activities") + "</td>");
                        out.println("<td>" + rs.getInt("punctuality") + "</td>");
                        out.println("<td>" + rs.getInt("feedback_encouragement") + "</td>");
                        out.println("<td>" + rs.getInt("content_mastery") + "</td>");
                        out.println("<td>" + rs.getInt("instructional_technology") + "</td>");
                        out.println("<td>" + rs.getInt("active_participation") + "</td>");
                        out.println("<td>" + rs.getInt("communication_skills") + "</td>");
                        out.println("<td>" + rs.getInt("assessments_provided") + "</td>");
                        out.println("<td>" + rs.getInt("feedback_provision") + "</td>");
                        out.println("<td>" + rs.getString("strengths") + "</td>");
                        out.println("<td>" + rs.getString("improvement_areas") + "</td>");
                        out.println("</tr>");
                    }
                    
                    out.println("</table>");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
                try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
                try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
            }
        }
        
        out.println("</body></html>");
        out.close();
    }
}
