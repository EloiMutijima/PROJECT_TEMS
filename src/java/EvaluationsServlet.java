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

@WebServlet("/EvaluationsServlet")
public class EvaluationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // JDBC variables for opening, managing, and closing connections
    private Connection connection;
    
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Database connection error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Fetch and display evaluations
        displayEvaluations(out);
    }
    
    private void displayEvaluations(PrintWriter out) throws ServletException {
        String query = "SELECT id, Module_code, Module_name, Department, Trainer_id, " +
                       "classroom_setting, learning_consumables, ppe_availability, " +
                       "course_objectives, learning_units, learning_activities, " +
                       "content_mastery, instructional_technology, active_participation, " +
                       "communication_skills, assessments_provided, feedback_provision, " +
                       "strengths, improvement_areas FROM evaluations";
        
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Evaluations List</title>");
            out.println("<style>");
            out.println("body {");
            out.println("    font-family: Arial, sans-serif;");
            out.println("    margin: 0;");
            out.println("    padding: 0;");
            out.println("    background-color: #f4f4f4;");
            out.println("    display: flex;");
            out.println("    flex-direction: column;");
            out.println("    min-height: 100vh;");
            out.println("}");
            out.println("header {");
            out.println("    background-color: darkblue;");
            out.println("    color: white;");
            out.println("    padding: 10px;");
            out.println("    text-align: center;");
            out.println("    font-size: 24px;");
            out.println("    font-weight: bold;");
            out.println("}");
            out.println(".container {");
            out.println("    display: flex;");
            out.println("    flex: 1;");
            out.println("    overflow: hidden;");
            out.println("}");
            out.println(".sidebar {");
            out.println("    background-color: darkblue;");
            out.println("    color: white;");
            out.println("    width: 250px;");
            out.println("    padding: 20px;");
            out.println("    box-sizing: border-box;");
            out.println("    flex-shrink: 0;");
            out.println("    overflow-y: auto;");
            out.println("}");
            out.println(".sidebar a {");
            out.println("    display: block;");
            out.println("    color: white;");
            out.println("    padding: 10px 0;");
            out.println("    text-decoration: none;");
            out.println("    position: relative;");
            out.println("}");
            out.println(".sidebar a:hover {");
            out.println("    background-color: white;");
            out.println("    color: black;");
            out.println("}");
            out.println(".dropdown {");
            out.println("    position: relative;");
            out.println("}");
            out.println(".dropdown-content {");
            out.println("    display: none;");
            out.println("    position: absolute;");
            out.println("    background-color: darkblue;");
            out.println("    min-width: 160px;");
            out.println("    z-index: 1;");
            out.println("    top: 100%;");
            out.println("    left: 0;");
            out.println("    border-radius: 5px;");
            out.println("}");
            out.println(".dropdown-content a {");
            out.println("    color: white;");
            out.println("    padding: 10px;");
            out.println("    text-decoration: none;");
            out.println("    display: block;");
            out.println("}");
            out.println(".dropdown-content a:hover {");
            out.println("    background-color: white;");
            out.println("    color: black;");
            out.println("}");
            out.println(".dropdown:hover .dropdown-content {");
            out.println("    display: block;");
            out.println("}");
            out.println(".content {");
            out.println("    flex: 1;");
            out.println("    padding: 20px;");
            out.println("    box-sizing: border-box;");
            out.println("    overflow-y: auto;");
            out.println("    margin-left: 250px;");
            out.println("}");
            out.println(".content h1 {");
            out.println("    color: darkblue;");
            out.println("    margin-top: 0;");
            out.println("    text-align: center;");
            out.println("}");
            out.println(".table-container {");
            out.println("    overflow-x: auto;");
            out.println("}");
            out.println("table {");
            out.println("    width: 100%;");
            out.println("    border-collapse: collapse;");
            out.println("    background-color: white;");
            out.println("    margin-top: 20px;");
            out.println("}");
            out.println("th, td {");
            out.println("    padding: 10px;");
            out.println("    text-align: left;");
            out.println("    border-bottom: 1px solid #ddd;");
            out.println("}");
            out.println("th {");
            out.println("    background-color: darkblue;");
            out.println("    color: white;");
            out.println("}");
            out.println("tr:hover {");
            out.println("    background-color: #f1f1f1;");
            out.println("}");
            out.println("footer {");
            out.println("    background-color: darkblue;");
            out.println("    color: white;");
            out.println("    text-align: center;");
            out.println("    padding: 10px;");
            out.println("    margin-top: auto;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>Trainer Evaluation Management System</header>");
            out.println("<div class='container'>");
            out.println("<div class='sidebar'>");
            out.println("<a href='evaluators.jsp'>Home</a>");
            out.println("<a href='AllTrainers'>Trainer</a>");
            out.println("<a href='new_module.jsp'>Create Module</a>");
            out.println("<a href='RepliesServlet'>Trainer Feedback</a>");
            out.println("<a href='EvaluationsServlet'>View Reports</a>");
            out.println("<a href='loginpage.jsp' class='logout'>Logout</a>");
            out.println("</div>");
            out.println("<div class='content'>");
            out.println("<h1>Evaluations List</h1>");
            out.println("<div class='table-container'>");
            out.println("<table>");
            out.println("<tr><th>ID</th><th>Module Code</th><th>Module Name</th><th>Department</th><th>Trainer ID</th><th>Classroom Setting</th><th>Learning Consumables</th><th>PPE Availability</th><th>Course Objectives</th><th>Learning Units</th><th>Learning Activities</th><th>Content Mastery</th><th>Instructional Technology</th><th>Active Participation</th><th>Communication Skills</th><th>Assessments Provided</th><th>Feedback Provision</th><th>Strengths</th><th>Improvement Areas</th></tr>");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String moduleCode = rs.getString("Module_code");
                String moduleName = rs.getString("Module_name");
                String department = rs.getString("Department");
                int trainerId = rs.getInt("Trainer_id");
                String classroomSetting = rs.getString("classroom_setting");
                String learningConsumables = rs.getString("learning_consumables");
                String ppeAvailability = rs.getString("ppe_availability");
                String courseObjectives = rs.getString("course_objectives");
                String learningUnits = rs.getString("learning_units");
                String learningActivities = rs.getString("learning_activities");
                String contentMastery = rs.getString("content_mastery");
                String instructionalTechnology = rs.getString("instructional_technology");
                String activeParticipation = rs.getString("active_participation");
                String communicationSkills = rs.getString("communication_skills");
                String assessmentsProvided = rs.getString("assessments_provided");
                String feedbackProvision = rs.getString("feedback_provision");
                String strengths = rs.getString("strengths");
                String improvementAreas = rs.getString("improvement_areas");
                
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + moduleCode + "</td>");
                out.println("<td>" + moduleName + "</td>");
                out.println("<td>" + department + "</td>");
                out.println("<td>" + trainerId + "</td>");
                out.println("<td>" + classroomSetting + "</td>");
                out.println("<td>" + learningConsumables + "</td>");
                out.println("<td>" + ppeAvailability + "</td>");
                out.println("<td>" + courseObjectives + "</td>");
                out.println("<td>" + learningUnits + "</td>");
                out.println("<td>" + learningActivities + "</td>");
                out.println("<td>" + contentMastery + "</td>");
                out.println("<td>" + instructionalTechnology + "</td>");
                out.println("<td>" + activeParticipation + "</td>");
                out.println("<td>" + communicationSkills + "</td>");
                out.println("<td>" + assessmentsProvided + "</td>");
                out.println("<td>" + feedbackProvision + "</td>");
                out.println("<td>" + strengths + "</td>");
                out.println("<td>" + improvementAreas + "</td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<footer>© 2024 Trainer Evaluation Management System</footer>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            throw new ServletException("Database query error", e);
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.destroy();
    }
}
