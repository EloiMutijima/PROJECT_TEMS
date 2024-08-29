import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Replace with your actual DB password

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String moduleName = request.getParameter("moduleName"); // Get the module name from request
        List<Feedback> feedbackList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // SQL query to search for modules by name if provided, otherwise fetch all data
            String searchSql = "SELECT * FROM evaluatedmodule";
            if (moduleName != null && !moduleName.trim().isEmpty()) {
                searchSql += " WHERE module_name LIKE ?";
            }

            pstmt = conn.prepareStatement(searchSql);

            if (moduleName != null && !moduleName.trim().isEmpty()) {
                pstmt.setString(1, "%" + moduleName + "%");
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Feedback feedback = new Feedback(
                    rs.getString("semester"),
                    rs.getString("M_code"),
                    rs.getString("academic_year"),
                    rs.getString("module_name"),
                    rs.getString("trainer"),
                    rs.getString("department"),
                    rs.getString("option_field"),
                    rs.getString("classroom_setting"),
                    rs.getString("learning_consumables"),
                    rs.getString("ppe_availability"),
                    rs.getString("course_objectives"),
                    rs.getString("learning_units"),
                    rs.getString("learning_activities"),
                    rs.getString("punctuality"),
                    rs.getString("feedback_encouragement"),
                    rs.getString("content_mastery"),
                    rs.getString("instructional_technology"),
                    rs.getString("active_participation"),
                    rs.getString("communication_skills"),
                    rs.getString("assessments_provided"),
                    rs.getString("feedback_provision"),
                    rs.getString("strengths"),
                    rs.getString("improvement_area")
                );
                feedbackList.add(feedback);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
            return;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.setContentType("text/html");
        response.getWriter().println("<html><head><title>Feedback Data</title>");
        response.getWriter().println("<style>");
        response.getWriter().println("body { font-family: Arial, sans-serif; margin: 20px; }");
        response.getWriter().println("h1 { color: #333; }");
        response.getWriter().println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
        response.getWriter().println("table, th, td { border: 1px solid #ddd; }");
        response.getWriter().println("th, td { padding: 10px; text-align: left; }");
        response.getWriter().println("th { background-color: #002D72; color: white; }");
        response.getWriter().println("tr:nth-child(even) { background-color: #f9f9f9; }");
        response.getWriter().println("</style>");
        response.getWriter().println("</head><body>");

        // Search Form
        response.getWriter().println("<h1>Search for Module Feedback</h1>");
        response.getWriter().println("<form action='FeedbackServlet' method='get'>");
        response.getWriter().println("<input type='text' name='moduleName' placeholder='Enter module name' value='" + (moduleName != null ? moduleName : "") + "'>");
        response.getWriter().println("<input type='submit' value='Search'>");
        response.getWriter().println("</form>");

        if (!feedbackList.isEmpty()) {
            response.getWriter().println("<h1>Feedback Data</h1>");
            response.getWriter().println("<table>");
            response.getWriter().println("<tr><th>Semester</th><th>Module Code</th><th>Academic Year</th><th>Module Name</th><th>Trainer</th><th>Department</th><th>Option</th><th>Classroom Setting</th><th>Learning Consumables</th><th>PPE Availability</th><th>Course Objectives</th><th>Learning Units</th><th>Learning Activities</th><th>Punctuality</th><th>Feedback Encouragement</th><th>Content Mastery</th><th>Instructional Technology</th><th>Active Participation</th><th>Communication Skills</th><th>Assessments Provided</th><th>Feedback Provision</th><th>Strengths</th><th>Improvement Area</th></tr>");

            for (Feedback feedback : feedbackList) {
                response.getWriter().println("<tr><td>" + feedback.getSemester() + "</td><td>" + feedback.getModuleCode() + "</td><td>" + feedback.getAcademicYear() + "</td><td>" + feedback.getModuleName() + "</td><td>" + feedback.getTrainer() + "</td><td>" + feedback.getDepartment() + "</td><td>" + feedback.getOptionField() + "</td><td>" + feedback.getClassroomSetting() + "</td><td>" + feedback.getLearningConsumables() + "</td><td>" + feedback.getPpeAvailability() + "</td><td>" + feedback.getCourseObjectives() + "</td><td>" + feedback.getLearningUnits() + "</td><td>" + feedback.getLearningActivities() + "</td><td>" + feedback.getPunctuality() + "</td><td>" + feedback.getFeedbackEncouragement() + "</td><td>" + feedback.getContentMastery() + "</td><td>" + feedback.getInstructionalTechnology() + "</td><td>" + feedback.getActiveParticipation() + "</td><td>" + feedback.getCommunicationSkills() + "</td><td>" + feedback.getAssessmentsProvided() + "</td><td>" + feedback.getFeedbackProvision() + "</td><td>" + feedback.getStrengths() + "</td><td>" + feedback.getImprovementArea() + "</td></tr>");
            }

            response.getWriter().println("</table>");
        } else {
            response.getWriter().println("<p>No feedback data found for the specified module name.</p>");
        }

        response.getWriter().println("</body></html>");
    }

    class Feedback {
        private String semester;
        private String moduleCode;
        private String academicYear;
        private String moduleName;
        private String trainer;
        private String department;
        private String optionField;
        private String classroomSetting;
        private String learningConsumables;
        private String ppeAvailability;
        private String courseObjectives;
        private String learningUnits;
        private String learningActivities;
        private String punctuality;
        private String feedbackEncouragement;
        private String contentMastery;
        private String instructionalTechnology;
        private String activeParticipation;
        private String communicationSkills;
        private String assessmentsProvided;
        private String feedbackProvision;
        private String strengths;
        private String improvementArea;

        public Feedback(String semester, String moduleCode, String academicYear, String moduleName, String trainer, String department, String optionField, String classroomSetting, String learningConsumables, String ppeAvailability, String courseObjectives, String learningUnits, String learningActivities, String punctuality, String feedbackEncouragement, String contentMastery, String instructionalTechnology, String activeParticipation, String communicationSkills, String assessmentsProvided, String feedbackProvision, String strengths, String improvementArea) {
            this.semester = semester;
            this.moduleCode = moduleCode;
            this.academicYear = academicYear;
            this.moduleName = moduleName;
            this.trainer = trainer;
            this.department = department;
            this.optionField = optionField;
            this.classroomSetting = classroomSetting;
            this.learningConsumables = learningConsumables;
            this.ppeAvailability = ppeAvailability;
            this.courseObjectives = courseObjectives;
            this.learningUnits = learningUnits;
            this.learningActivities = learningActivities;
            this.punctuality = punctuality;
            this.feedbackEncouragement = feedbackEncouragement;
            this.contentMastery = contentMastery;
            this.instructionalTechnology = instructionalTechnology;
            this.activeParticipation = activeParticipation;
            this.communicationSkills = communicationSkills;
            this.assessmentsProvided = assessmentsProvided;
            this.feedbackProvision = feedbackProvision;
            this.strengths = strengths;
            this.improvementArea = improvementArea;
        }

        // Getters for the fields
        public String getSemester() { return semester; }
        public String getModuleCode() { return moduleCode; }
        public String getAcademicYear() { return academicYear; }
        public String getModuleName() { return moduleName; }
        public String getTrainer() { return trainer; }
        public String getDepartment() { return department; }
        public String getOptionField() { return optionField; }
        public String getClassroomSetting() { return classroomSetting; }
        public String getLearningConsumables() { return learningConsumables; }
        public String getPpeAvailability() { return ppeAvailability; }
        public String getCourseObjectives() { return courseObjectives; }
        public String getLearningUnits() { return learningUnits; }
        public String getLearningActivities() { return learningActivities; }
        public String getPunctuality() { return punctuality; }
        public String getFeedbackEncouragement() { return feedbackEncouragement; }
        public String getContentMastery() { return contentMastery; }
        public String getInstructionalTechnology() { return instructionalTechnology; }
        public String getActiveParticipation() { return activeParticipation; }
        public String getCommunicationSkills() { return communicationSkills; }
        public String getAssessmentsProvided() { return assessmentsProvided; }
        public String getFeedbackProvision() { return feedbackProvision; }
        public String getStrengths() { return strengths; }
        public String getImprovementArea() { return improvementArea; }
    }
}
