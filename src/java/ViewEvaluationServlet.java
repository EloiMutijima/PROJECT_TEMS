import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewEvaluationServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<p>Error: JDBC Driver not found.</p>");
            return;
        }

        String searchQuery = request.getParameter("search");
        String sql = "SELECT * FROM evaluations";
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            sql += " WHERE Module_code LIKE ? OR Module_name LIKE ?";
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String likeQuery = "%" + searchQuery + "%";
                stmt.setString(1, likeQuery);
                stmt.setString(2, likeQuery);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>Evaluations - Trainer Evaluation Management System</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
                out.println("header { background-color: darkblue; color: white; padding: 10px; text-align: center; font-size: 30px; font-weight: bold; }");
                out.println(".container { display: flex; height: 100vh; }");
                out.println(".sidebar { background-color: darkblue; color: white; width: 200px; padding: 20px; box-sizing: border-box; }");
                out.println(".sidebar a { display: block; color: white; padding: 10px 0; text-decoration: none; position: relative; }");
                out.println(".sidebar a:hover { background-color: white; color: black; }");
                out.println(".dropdown { position: relative; }");
                out.println(".dropdown-content { display: none; position: absolute; background-color: darkblue; min-width: 160px; z-index: 1; top: 100%; left: 0; border-radius: 5px; }");
                out.println(".dropdown-content a { color: white; padding: 10px; text-decoration: none; display: block; }");
                out.println(".dropdown-content a:hover { background-color: white; color: black; }");
                out.println(".dropdown:hover .dropdown-content { display: block; }");
                out.println(".content { flex: 1; padding: 20px; box-sizing: border-box; }");
                out.println(".content h2 { color: darkblue; }");
                out.println(".cards { display: flex; flex-wrap: wrap; gap: 20px; }");
                out.println(".card { background-color: white; width: calc(25% - 20px); padding: 20px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); border-radius: 5px; cursor: pointer; }");
                out.println(".card:hover { background-color: #f0f0f0; }");
                out.println(".modal { display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); justify-content: center; align-items: center; }");
                out.println(".modal-content { background-color: white; padding: 20px; border-radius: 5px; width: 80%; max-width: 800px; }");
                out.println(".close { float: right; font-size: 1.5em; cursor: pointer; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<header>TRAINER EVALUATION MANAGEMENT SYSTEM</header>");
                out.println("<div class='container'>");
                out.println("<div class='sidebar'>");
                out.println("<a href='adminpage.jsp'>Go Home</a>");
                out.println("<a href='new_user.jsp'>Create User</a>");
                out.println("<div class='dropdown'>");
                out.println("<a href='#'>Evaluations</a>");
                out.println("<div class='dropdown-content'>");
                out.println("<a href='SeeModules'>View Modules</a>");
                out.println("<a href='#'>Evaluated Modules</a>");
                out.println("</div></div>");
                out.println("<div class='dropdown'>");
                out.println("<a href='#'>Reports</a>");
                out.println("<div class='dropdown-content'>");
                out.println("<a href='#'>Users</a>");
                out.println("<a href='#'>Trainer Reports</a>");
                out.println("</div></div>");
                out.println("<a href='#'>Student</a>");
                out.println("<a href='#' class='logout'>Logout</a>");
                out.println("</div>");
                out.println("<div class='content'>");
                out.println("<h2>Evaluations</h2>");
                out.println("<div class='cards'>");

                while (rs.next()) {
                    out.println("<div class='card' onclick='openModal(\"" + rs.getString("Module_code") + "\", \"" + rs.getString("Module_name") + "\", \"" + rs.getString("Department") + "\", \"" + rs.getString("Trainer_id") + "\", \"" + rs.getString("classroom_setting") + "\", \"" + rs.getString("learning_consumables") + "\", \"" + rs.getString("ppe_availability") + "\", \"" + rs.getString("course_objectives") + "\", \"" + rs.getString("learning_units") + "\", \"" + rs.getString("learning_activities") + "\", \"" + rs.getString("content_mastery") + "\", \"" + rs.getString("instructional_technology") + "\", \"" + rs.getString("active_participation") + "\", \"" + rs.getString("communication_skills") + "\", \"" + rs.getString("assessments_provided") + "\", \"" + rs.getString("feedback_provision") + "\", \"" + rs.getString("strengths") + "\", \"" + rs.getString("improvement_areas") + "\")'>");
                    out.println("<h3>" + rs.getString("Module_name") + "</h3>");
                    out.println("<p><b>Module Code:</b> " + rs.getString("Module_code") + "</p>");
                    out.println("<p><b>Department:</b> " + rs.getString("Department") + "</p>");
                    out.println("</div>");
                }

                out.println("</div>");
                out.println("</div>");
                out.println("<div id='modal' class='modal'>");
                out.println("<div class='modal-content'>");
                out.println("<span class='close' onclick='closeModal()'>&times;</span>");
                out.println("<h2>Evaluation Details</h2>");
                out.println("<p id='modal-content'></p>");
                out.println("</div>");
                out.println("</div>");
                out.println("<script>");
                out.println("function openModal(moduleCode, moduleName, department, trainerId, classroomSetting, learningConsumables, ppeAvailability, courseObjectives, learningUnits, learningActivities, contentMastery, instructionalTechnology, activeParticipation, communicationSkills, assessmentsProvided, feedbackProvision, strengths, improvementAreas) {");
                out.println("    document.getElementById('modal-content').innerHTML = '<b>Module Code:</b> ' + moduleCode + '<br>' +");
                out.println("        '<b>Module Name:</b> ' + moduleName + '<br>' +");
                out.println("        '<b>Department:</b> ' + department + '<br>' +");
                out.println("        '<b>Trainer ID:</b> ' + trainerId + '<br>' +");
                out.println("        '<b>Classroom Setting:</b> ' + classroomSetting + '<br>' +");
                out.println("        '<b>Learning Consumables:</b> ' + learningConsumables + '<br>' +");
                out.println("        '<b>PPE Availability:</b> ' + ppeAvailability + '<br>' +");
                out.println("        '<b>Course Objectives:</b> ' + courseObjectives + '<br>' +");
                out.println("        '<b>Learning Units:</b> ' + learningUnits + '<br>' +");
                out.println("        '<b>Learning Activities:</b> ' + learningActivities + '<br>' +");
                out.println("        '<b>Content Mastery:</b> ' + contentMastery + '<br>' +");
                out.println("        '<b>Instructional Technology:</b> ' + instructionalTechnology + '<br>' +");
                out.println("        '<b>Active Participation:</b> ' + activeParticipation + '<br>' +");
                out.println("        '<b>Communication Skills:</b> ' + communicationSkills + '<br>' +");
                out.println("        '<b>Assessments Provided:</b> ' + assessmentsProvided + '<br>' +");
                out.println("        '<b>Feedback Provision:</b> ' + feedbackProvision + '<br>' +");
                out.println("        '<b>Strengths:</b> ' + strengths + '<br>' +");
                out.println("        '<b>Improvement Areas:</b> ' + improvementAreas;");
                out.println("    document.getElementById('modal').style.display = 'flex';");
                out.println("}");
                out.println("function closeModal() {");
                out.println("    document.getElementById('modal').style.display = 'none';");
                out.println("}");
                out.println("</script>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<p>Error: Unable to retrieve evaluations.</p>");
        }
    }
}
