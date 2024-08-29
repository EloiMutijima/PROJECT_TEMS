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

// Define the servlet and map it to a URL
@WebServlet("/FeedbackServlet")
public class Feedback_evaluator extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Replace with your actual DB password

    // Handle GET requests to retrieve and display feedback data
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Feedback> feedbackList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // SQL query to fetch data from the evaluatedmodule table
            String selectSql = "SELECT * FROM evaluatedmodule";
            pstmt = conn.prepareStatement(selectSql);
            rs = pstmt.executeQuery();

            // Fetching the data from the result set and storing it in feedbackList
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
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Generate HTML response with embedded CSS
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html><head>");
        response.getWriter().println("<title>Quality Assurance Dashboard</title>");
        response.getWriter().println("<meta charset=\"UTF-8\">");
        response.getWriter().println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        response.getWriter().println("<style>");
        response.getWriter().println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
        response.getWriter().println("header { background-color: #002D72; color: white; padding: 10px 0; text-align: center; margin-bottom: 10px; }");
        response.getWriter().println("nav { background-color: grey; margin-top: -10px; }");
        response.getWriter().println("nav ul { list-style-type: none; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; }");
        response.getWriter().println("nav ul li { margin: 0 15px; position: relative; }");
        response.getWriter().println("nav ul li a { color: white; text-decoration: none; padding: 10px 15px; display: block; }");
        response.getWriter().println("nav ul li ul.dropdown { display: none; position: absolute; background-color: grey; top: 100%; left: 0; padding: 0; list-style-type: none; z-index: 1000; }");
        response.getWriter().println("nav ul li:hover ul.dropdown { display: block; }");
        response.getWriter().println("nav ul li ul.dropdown li { width: 150px; }");
        response.getWriter().println("nav ul li ul.dropdown li a { padding: 10px; }");
        response.getWriter().println("footer { background-color: #002D72; color: white; text-align: center; height: 100px; }");
        response.getWriter().println(".card { background-color: white; border: 1px solid #ccc; border-radius: 8px; padding: 20px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); text-align: left; }");
        response.getWriter().println(".card h2 { margin-top: 0; }");
        response.getWriter().println("</style>");
        response.getWriter().println("</head><body>");
        response.getWriter().println("<header><h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1></header>");
        response.getWriter().println("<nav><ul>");
        response.getWriter().println("<li><a href=\"evaluators.jsp\">Home</a></li>");
        response.getWriter().println("<li><a href=\"student_list_evaluator\">Student List</a></li>");
        response.getWriter().println("<li><a href=\"Alltrain\">Trainer</a></li>");
        response.getWriter().println("<li><a href=\"new_module.jsp\">Evaluation Form</a></li>");
        response.getWriter().println("<li><a href=\"FeedbackServlet\">View Reports</a></li>");
        response.getWriter().println("<li><a href=\"Trainer_feedbacks.jsp\">Trainer Feedbacks</a></li>");
        response.getWriter().println("<li><a href=\"login.jsp\" class=\"logout\">Logout</a></li>");
        response.getWriter().println("</ul></nav>");

        response.getWriter().println("<main>");
        response.getWriter().println("<h2>Feedback Data</h2>");
        response.getWriter().println("<table border='1'>");
        response.getWriter().println("<tr style='background-color:darkblue;color:white;'><th>Semester</th><th>Module Code</th><th>Academic Year</th><th>Module Name</th><th>Trainer</th><th>Department</th><th>Option</th><th>Classroom Setting</th><th>Learning Consumables</th><th>PPE Availability</th><th>Course Objectives</th><th>Learning Units</th><th>Learning Activities</th><th>Punctuality</th><th>Feedback Encouragement</th><th>Content Mastery</th><th>Instructional Technology</th><th>Active Participation</th><th>Communication Skills</th><th>Assessments Provided</th><th>Feedback Provision</th><th>Strengths</th><th>Improvement Area</th></tr>");

        for (Feedback feedback : feedbackList) {
            response.getWriter().println("<tr><td>" + feedback.getSemester() + "</td><td>" + feedback.getModuleCode() + "</td><td>" + feedback.getAcademicYear() + "</td><td>" + feedback.getModuleName() + "</td><td>" + feedback.getTrainer() + "</td><td>" + feedback.getDepartment() + "</td><td>" + feedback.getOptionField() + "</td><td>" + feedback.getClassroomSetting() + "</td><td>" + feedback.getLearningConsumables() + "</td><td>" + feedback.getPpeAvailability() + "</td><td>" + feedback.getCourseObjectives() + "</td><td>" + feedback.getLearningUnits() + "</td><td>" + feedback.getLearningActivities() + "</td><td>" + feedback.getPunctuality() + "</td><td>" + feedback.getFeedbackEncouragement() + "</td><td>" + feedback.getContentMastery() + "</td><td>" + feedback.getInstructionalTechnology() + "</td><td>" + feedback.getActiveParticipation() + "</td><td>" + feedback.getCommunicationSkills() + "</td><td>" + feedback.getAssessmentsProvided() + "</td><td>" + feedback.getFeedbackProvision() + "</td><td>" + feedback.getStrengths() + "</td><td>" + feedback.getImprovementArea() + "</td></tr>");
        }

        response.getWriter().println("</table>");
        response.getWriter().println("</main>");

        // Add the footer section
        response.getWriter().println("<footer>");
        response.getWriter().println("<p>&copy; 2024 Trainer Evaluation Management System. All rights reserved.</p>");
        response.getWriter().println("</footer>");

        response.getWriter().println("</body></html>");
    }

    // Handle POST requests (if any)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // For this example, just call doGet()
    }

    // Feedback class to represent feedback data
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

        // Constructor to initialize all fields
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

        // Getters for all fields
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
