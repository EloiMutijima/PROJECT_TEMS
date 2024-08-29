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

@WebServlet("/InsertEvaluationServlet")
public class InsertEvaluationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String moduleCode = request.getParameter("Module_code");
        String moduleName = request.getParameter("Module_name");
        String department = request.getParameter("Department");
        String trainerId = request.getParameter("Trainer_id");

        // Retrieve all feedback fields
        String classroomSetting = request.getParameter("classroom_setting");
        String learningConsumables = request.getParameter("availability_of_learning_consumables,_tools,_and_equipment");
        String ppeAvailability = request.getParameter("availability_of_ppe");
        String courseObjectives = request.getParameter("clarification_of_course_objectives_to_students");
        String learningUnits = request.getParameter("provision_of_mapping_of_learning_units,_course_handouts,_and_further_references");
        String learningActivities = request.getParameter("provision_of_sufficient_learning_activities_for_each_learning_outcome");
        String punctuality = request.getParameter("trainer's_punctuality");
        String feedbackEncouragement = request.getParameter("encouraging_opinions_and_learners'_feedback");
        String contentMastery = request.getParameter("mastery_of_content");
        String instructionalTechnology = request.getParameter("effective_use_of_instructional_technology");
        String activeParticipation = request.getParameter("encouraging_active_participation_and_independent_practice");
        String communicationSkills = request.getParameter("communication_skills_and_clarity_in_language");
        String assessmentsProvided = request.getParameter("provision_of_assignments_and_assessments");
        String feedbackProvision = request.getParameter("provision_of_feedback_to_students_on_their_progress");
        String strengths = request.getParameter("trainer_strengths");
        String improvementAreas = request.getParameter("areas_of_improvement");

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Load database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // SQL insert statement with placeholders for all fields
            String sql = "INSERT INTO evaluations (Module_code, Module_name, Department, Trainer_id, " +
                         "classroom_setting, learning_consumables, ppe_availability, course_objectives, " +
                         "learning_units, learning_activities, punctuality, feedback_encouragement, " +
                         "content_mastery, instructional_technology, active_participation, communication_skills, " +
                         "assessments_provided, feedback_provision, strengths, improvement_areas) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(sql);

            // Set parameters for all columns
            ps.setString(1, moduleCode);
            ps.setString(2, moduleName);
            ps.setString(3, department);
            ps.setString(4, trainerId);
            ps.setString(5, classroomSetting);
            ps.setString(6, learningConsumables);
            ps.setString(7, ppeAvailability);
            ps.setString(8, courseObjectives);
            ps.setString(9, learningUnits);
            ps.setString(10, learningActivities);
            ps.setString(11, punctuality);
            ps.setString(12, feedbackEncouragement);
            ps.setString(13, contentMastery);
            ps.setString(14, instructionalTechnology);
            ps.setString(15, activeParticipation);
            ps.setString(16, communicationSkills);
            ps.setString(17, assessmentsProvided);
            ps.setString(18, feedbackProvision);
            ps.setString(19, strengths);
            ps.setString(20, improvementAreas);

            // Execute the insert
            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Evaluation Done');");
                out.println("location='studentpage.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Error inserting data. Please try again.');");
                out.println("location='evaluation.jsp';");
                out.println("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error inserting data: " + e.getMessage() + "</p>");
        } finally {
            // Close resources
            if (ps != null) try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
