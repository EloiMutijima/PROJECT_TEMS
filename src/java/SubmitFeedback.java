import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubmitFeedback")
public class SubmitFeedback extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String semester = request.getParameter("semester");
        String moduleCode = request.getParameter("module_code");
        String academicYear = request.getParameter("academic_year");
        String moduleName = request.getParameter("module_name");
        String trainer = request.getParameter("trainer");
        String department = request.getParameter("department");
        String optionField = request.getParameter("option");

        // Retrieve rating parameters
        String classroomSetting = request.getParameter("classroom_setting");
        String learningConsumables = request.getParameter("learning_consumables");
        String ppeAvailability = request.getParameter("ppe_availability");
        String courseObjectives = request.getParameter("course_objectives");
        String learningUnits = request.getParameter("learning_units");
        String learningActivities = request.getParameter("learning_activities");
        String punctuality = request.getParameter("punctuality");
        String feedbackEncouragement = request.getParameter("feedback_encouragement");
        String contentMastery = request.getParameter("content_mastery");
        String instructionalTechnology = request.getParameter("instructional_technology");
        String activeParticipation = request.getParameter("active_participation");
        String communicationSkills = request.getParameter("communication_skills");
        String assessmentsProvided = request.getParameter("assignments_assessments");
        String feedbackProvision = request.getParameter("progress_feedback");

        // Retrieve comments
        String strengths = request.getParameter("trainer_strengths");
        String improvementArea = request.getParameter("areas_of_improvement");

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
        String dbUser = "root";
        String dbPassword = ""; // Replace with your actual DB password

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            // SQL query to insert the form data into the database
            String sql = "INSERT INTO evaluatedmodule (semester, M_code, academic_year, module_name, trainer, department, option_field, classroom_setting, learning_consumables, ppe_availability, course_objectives, learning_units, learning_activities, punctuality, feedback_encouragement, content_mastery, instructional_technology, active_participation, communication_skills, assessments_provided, feedback_provision, strengths, improvement_area) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            // Set parameters for the SQL query
            pstmt.setString(1, semester);
            pstmt.setString(2, moduleCode);
            pstmt.setString(3, academicYear);
            pstmt.setString(4, moduleName);
            pstmt.setString(5, trainer);
            pstmt.setString(6, department);
            pstmt.setString(7, optionField);
            pstmt.setString(8, classroomSetting);
            pstmt.setString(9, learningConsumables);
            pstmt.setString(10, ppeAvailability);
            pstmt.setString(11, courseObjectives);
            pstmt.setString(12, learningUnits);
            pstmt.setString(13, learningActivities);
            pstmt.setString(14, punctuality);
            pstmt.setString(15, feedbackEncouragement);
            pstmt.setString(16, contentMastery);
            pstmt.setString(17, instructionalTechnology);
            pstmt.setString(18, activeParticipation);
            pstmt.setString(19, communicationSkills);
            pstmt.setString(20, assessmentsProvided);
            pstmt.setString(21, feedbackProvision);
            pstmt.setString(22, strengths);
            pstmt.setString(23, improvementArea);

            // Execute the SQL query
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle the error appropriately (e.g., log it, notify the user, etc.)

        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}