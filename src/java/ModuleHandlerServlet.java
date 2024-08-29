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

@WebServlet("/ModuleHandlerServlet")
public class ModuleHandlerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String moduleCode = request.getParameter("Module_code");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Load database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Retrieve module names for dropdown
            String sql = "SELECT Module_code, Module_name FROM courses";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            // HTML structure
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Module Selector</title>");
            out.println("<link rel='stylesheet' type='text/css' href='styles.css'>"); // Link to the CSS file
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; display: flex; flex-direction: column; height: 100vh; }");
            out.println("header { background-color: darkblue; color: white; padding: 10px; text-align: center; font-size: 30px; font-weight: bold; }");
            out.println(".container { display: flex; flex: 1; }");
            out.println(".sidebar { background-color: darkblue; color: white; width: 200px; padding: 20px; box-sizing: border-box; display: flex; flex-direction: column; }");
            out.println(".sidebar a { display: block; color: white; padding: 10px 0; text-decoration: none; }");
            out.println(".sidebar a:hover { background-color: white; color: black; }");
            out.println(".content { flex: 1; padding: 20px; box-sizing: border-box; }");
            out.println(".profile-card, .module-list { background-color: white; border-radius: 5px; padding: 20px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); margin-bottom: 20px; }");
            out.println(".profile-card h3, .module-list h3 { color: darkblue; }");
            out.println(".profile-card p, .module-list ul { margin: 10px 0; }");
            out.println(".module-list ul { list-style-type: none; padding: 0; }");
            out.println(".module-list li { background-color: #f4f4f4; border: 1px solid #ddd; padding: 10px; border-radius: 5px; margin-bottom: 10px; }");
            out.println("footer { background-color: darkblue; color: white; text-align: center; padding: 10px 0; width: 100%; }");
            out.println("table { width: 100%; border-collapse: collapse; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println(".rating input[type='radio'] { margin: 0; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>");
            out.println("TRAINER EVALUATION MANAGEMENT SYSTEM");
            out.println("</header>");
            out.println("<div class='container'>");
            out.println("<div class='sidebar'>");
            out.println("<a href='studentpage.jsp'>Home</a>");
            out.println("<a href='ModuleHandlerServlet'>Evaluation Form</a>");
            out.println("<a href='Aboutus.jsp'>About Us</a>");
            out.println("<a href='loginpage.jsp' class='logout'>Logout</a>");
            out.println("</div>");
            out.println("<div class='content'>");
            out.println("<div class='profile-card'>");
            out.println("<h3>Module Selector</h3>");
            out.println("<form action='ModuleHandlerServlet' method='get'>");

            // Dropdown for module selection
            out.println("<label for='module_code'>Select Module:</label>");
            out.println("<select id='module_code' name='Module_code' onchange='this.form.submit()'>");
            out.println("<option value=''>--Select Module--</option>");
            while (rs.next()) {
                String code = rs.getString("Module_code");
                String name = rs.getString("Module_name");
                out.println("<option value='" + code + "'" + (code.equals(moduleCode) ? " selected" : "") + ">" + name + "</option>");
            }
            out.println("</select>");
            out.println("<input type='submit' value='Select Module'>");
            out.println("</form>");

            // Display module details and feedback form if selected
            if (moduleCode != null && !moduleCode.isEmpty()) {
                // Query to retrieve module details
                sql = "SELECT Module_code, Module_name, Department, Program, Trainer_id FROM courses WHERE Module_code = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, moduleCode);
                rs = ps.executeQuery();

                if (rs.next()) {
                    out.println("<h2>Module Details</h2>");
                    out.println("<form action='InsertEvaluationServlet' method='post'>");
                    out.println("<label for='module_code'>Module Code:</label>");
                    out.println("<input type='text' id='module_code' name='Module_code' value='" + rs.getString("Module_code") + "' readonly><br>");

                    out.println("<label for='module_name'>Module Name:</label>");
                    out.println("<input type='text' id='module_name' name='Module_name' value='" + rs.getString("Module_name") + "' readonly><br>");

                    out.println("<label for='department'>Department:</label>");
                    out.println("<input type='text' id='department' name='Department' value='" + rs.getString("Department") + "' readonly><br>");

                    out.println("<label for='option_field'>Option Field:</label>");
                    out.println("<input type='text' id='option_field' name='option_field' value='" + rs.getString("Program") + "' readonly><br>");

                    out.println("<label for='trainer_id'>Trainer ID:</label>");
                    out.println("<input type='text' id='trainer_id' name='Trainer_id' value='" + rs.getString("Trainer_id") + "' readonly><br>");
                    
                    // Feedback Form
                    out.println("<h2 style='text-align:center'>Trainer Feedback Form</h2>");
                    out.println("<p>Please indicate your impressions by rating trainers on the following elements; use the tick under the number which stands for: <br>");
                    out.println("1=Very poor 2=Poor 3=Good 4=Very Good 5=Excellent</p>");
                    out.println("<table>");
                    out.println("<thead>");
                    out.println("<tr><th>S/N</th><th>Learning Environment</th><th colspan='5' class='rating'>Give marks (Use radio buttons)</th></tr>");
                    out.println("</thead>");
                    out.println("<tbody>");
                    // Feedback form rows
                    out.println(generateFeedbackRow(1, "Classroom setting"));
                    out.println(generateFeedbackRow(2, "Availability of learning consumables, tools, and equipment"));
                    out.println(generateFeedbackRow(3, "Availability of PPE"));
                    out.println(generateFeedbackRow(4, "Clarification of course objectives to students"));
                    out.println(generateFeedbackRow(5, "Provision of mapping of learning units, course handouts, and further references"));
                    out.println(generateFeedbackRow(6, "Provision of sufficient learning activities for each learning outcome"));
                    out.println("</tbody>");
                    out.println("<tbody>");
                    out.println("<tr><td colspan='7' style='background-color: #f2f2f2; text-align: center;'>Quality of Delivery</td></tr>");
                    out.println(generateFeedbackRow(7, "Trainer's punctuality"));
                    out.println(generateFeedbackRow(8, "Encouraging opinions and learners' feedback"));
                    out.println(generateFeedbackRow(9, "Mastery of content"));
                    out.println(generateFeedbackRow(10, "Effective use of instructional technology"));
                    out.println(generateFeedbackRow(11, "Encouraging active participation and independent practice"));
                    out.println(generateFeedbackRow(12, "Communication skills and clarity in language"));
                    out.println(generateFeedbackRow(13, "Provision of assignments and assessments"));
                    out.println(generateFeedbackRow(14, "Provision of feedback to students on their progress"));
                    out.println("<tr><td>15</td><td>Strengths of the trainer</td><td><textarea name='trainer_strengths' rows='3' cols='50'></textarea></td></tr>");
                    out.println("<tr><td>16</td><td>Areas of improvement</td><td><textarea name='areas_of_improvement' rows='3' cols='50'></textarea></td></tr>");
                    out.println("</tbody>");
                    out.println("</table>");
                    out.println("<p><button type='submit'>Submit Feedback</button></p>");
                    out.println("</form>");
                } else {
                    out.println("<p>No details found for the selected module.</p>");
                }
            }
            out.println("</div>");
            out.println("</div>");
            out.println("<footer>");
            out.println("© 2024 Trainer Evaluation Management System");
            out.println("</footer>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error retrieving data from the database: " + e.getMessage() + "</p>");
        } finally {
            // Close resources
            if (rs != null) try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        out.println("</body>");
        out.println("</html>");
    }

    private String generateFeedbackRow(int number, String label) {
        return "<tr><td>" + number + "</td><td>" + label + "</td>" +
               "<td class='rating'><input type='radio' name='" + label.toLowerCase().replace(" ", "_") + "' value='1'></td>" +
               "<td class='rating'><input type='radio' name='" + label.toLowerCase().replace(" ", "_") + "' value='2'></td>" +
               "<td class='rating'><input type='radio' name='" + label.toLowerCase().replace(" ", "_") + "' value='3'></td>" +
               "<td class='rating'><input type='radio' name='" + label.toLowerCase().replace(" ", "_") + "' value='4'></td>" +
               "<td class='rating'><input type='radio' name='" + label.toLowerCase().replace(" ", "_") + "' value='5'></td></tr>";
    }
}
