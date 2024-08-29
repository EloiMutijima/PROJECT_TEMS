import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class EvaluateModuleServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String moduleCode = request.getParameter("moduleCode");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "password");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM courses WHERE Module_code = ?");
            ps.setString(1, moduleCode);
            ResultSet rs = (ResultSet) ps.executeQuery();
            
            if (rs.next()) {
                out.print("{");
                out.print("\"Module_code\":\"" + rs.getString("Module_code") + "\",");
                out.print("\"Module_name\":\"" + rs.getString("Module_name") + "\",");
                out.print("\"Department\":\"" + rs.getString("Department") + "\",");
                out.print("\"Program\":\"" + rs.getString("Program") + "\",");
                out.print("\"Trainer_id\":\"" + rs.getString("Trainer_id") + "\"");
                out.print("}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String moduleCode = request.getParameter("Module_code");
        String moduleName = request.getParameter("Module_name");
        String department = request.getParameter("Department");
        String optionField = request.getParameter("option_field");
        String trainerId = request.getParameter("Trainer_id");
        
        // Retrieve evaluation criteria
        int classroomSetting = Integer.parseInt(request.getParameter("classroom_setting"));
        int learningConsumables = Integer.parseInt(request.getParameter("learning_consumables"));
        int ppeAvailability = Integer.parseInt(request.getParameter("ppe_availability"));
        int courseObjectives = Integer.parseInt(request.getParameter("course_objectives"));
        int learningUnits = Integer.parseInt(request.getParameter("learning_units"));
        int learningActivities = Integer.parseInt(request.getParameter("learning_activities"));
        int punctuality = Integer.parseInt(request.getParameter("punctuality"));
        int feedbackEncouragement = Integer.parseInt(request.getParameter("feedback_encouragement"));
        int contentMastery = Integer.parseInt(request.getParameter("content_mastery"));
        int instructionalTechnology = Integer.parseInt(request.getParameter("instructional_technology"));
        int activeParticipation = Integer.parseInt(request.getParameter("active_participation"));
        int communicationSkills = Integer.parseInt(request.getParameter("communication_skills"));
        int assessmentsProvided = Integer.parseInt(request.getParameter("assessments_provided"));
        int feedbackProvision = Integer.parseInt(request.getParameter("feedback_provision"));
        String strengths = request.getParameter("strengths");
        String improvementArea = request.getParameter("improvement_area");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "password");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO evaluations(Module_code, Module_name, Department, option_field, Trainer_id, classroom_setting, learning_consumables, ppe_availability, course_objectives, learning_units, learning_activities, punctuality, feedback_encouragement, content_mastery, instructional_technology, active_participation, communication_skills, assessments_provided, feedback_provision, strengths, improvement_area) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            ps.setString(1, moduleCode);
            ps.setString(2, moduleName);
            ps.setString(3, department);
            ps.setString(4, optionField);
            ps.setString(5, trainerId);
            ps.setInt(6, classroomSetting);
            ps.setInt(7, learningConsumables);
            ps.setInt(8, ppeAvailability);
            ps.setInt(9, courseObjectives);
            ps.setInt(10, learningUnits);
            ps.setInt(11, learningActivities);
            ps.setInt(12, punctuality);
            ps.setInt(13, feedbackEncouragement);
            ps.setInt(14, contentMastery);
            ps.setInt(15, instructionalTechnology);
            ps.setInt(16, activeParticipation);
            ps.setInt(17, communicationSkills);
            ps.setInt(18, assessmentsProvided);
            ps.setInt(19, feedbackProvision);
            ps.setString(20, strengths);
            ps.setString(21, improvementArea);
            
            int result = ps.executeUpdate();
            
            if (result > 0) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
