import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProgressBarsServlet")
public class ProgressBarsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        Map<String, double[]> percentages = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // SQL query to get all evaluations
            String sql = "SELECT classroom_setting, learning_consumables, ppe_availability, course_objectives, learning_units, "
                       + "learning_activities, punctuality, feedback_encouragement, content_mastery, instructional_technology, "
                       + "active_participation, communication_skills, assessments_provided, feedback_provision "
                       + "FROM evaluatedmodule";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Prepare to calculate averages
            Map<String, Integer[]> counts = new HashMap<>();
            Map<String, Integer> totals = new HashMap<>();

            // Initialize counts and totals
            String[] columns = { "classroom_setting", "learning_consumables", "ppe_availability", "course_objectives",
                                 "learning_units", "learning_activities", "punctuality", "feedback_encouragement",
                                 "content_mastery", "instructional_technology", "active_participation", "communication_skills",
                                 "assessments_provided", "feedback_provision" };

            for (String column : columns) {
                counts.put(column, new Integer[5]); // Array to count occurrences of each ranking
                totals.put(column, 0); // Total number of responses
                for (int i = 0; i < 5; i++) {
                    counts.get(column)[i] = 0;
                }
            }

            // Count rankings
            while (rs.next()) {
                for (String column : columns) {
                    int rank = rs.getInt(column);
                    if (rank >= 1 && rank <= 5) {
                        counts.get(column)[rank - 1]++;
                        totals.put(column, totals.get(column) + 1);
                    }
                }
            }

            // Calculate percentages
            for (String column : columns) {
                double[] columnPercentages = new double[5];
                for (int i = 0; i < 5; i++) {
                    columnPercentages[i] = (counts.get(column)[i] / (double) totals.get(column)) * 100;
                }
                percentages.put(column, columnPercentages);
            }

            // Generate HTML
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Progress Bars</title>");
            out.println("<style>");
            out.println(".progress {");
            out.println("    width: 100%;");
            out.println("    background-color: #f3f3f3;");
            out.println("    border-radius: 5px;");
            out.println("    overflow: hidden;");
            out.println("    margin-bottom: 8px;");
            out.println("}");
            out.println(".progress-bar {");
            out.println("    height: 20px;");
            out.println("    text-align: center;");
            out.println("    color: white;");
            out.println("    line-height: 20px;");
            out.println("    border-radius: 5px;");
            out.println("    font-size: 12px;");
            out.println("}");
            out.println(".container {");
            out.println("    width: 70%;");
            out.println("    margin: 0 auto;");
            out.println("}");
            out.println(".very-poor { background-color: #e57373; }");
            out.println(".poor { background-color: #f06292; }");
            out.println(".good { background-color: #ba68c8; }");
            out.println(".very-good { background-color: #64b5f6; }");
            out.println(".excellent { background-color: #4caf50; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");

            for (String column : columns) {
                double[] data = percentages.get(column);
                out.println("<h3>" + column.replace("_", " ").toUpperCase() + "</h3>");
                boolean hasDisplayedBar = false;
                for (int i = 0; i < 5; i++) {
                    if (data[i] > 1) {
                        String rankingClass = "";
                        switch (i) {
                            case 0: rankingClass = "very-poor"; break;
                            case 1: rankingClass = "poor"; break;
                            case 2: rankingClass = "good"; break;
                            case 3: rankingClass = "very-good"; break;
                            case 4: rankingClass = "excellent"; break;
                        }
                        String rankingText = "";
                        switch (i) {
                            case 0: rankingText = "Very Poor"; break;
                            case 1: rankingText = "Poor"; break;
                            case 2: rankingText = "Good"; break;
                            case 3: rankingText = "Very Good"; break;
                            case 4: rankingText = "Excellent"; break;
                        }
                        out.println("<div class=\"progress\">");
                        out.println("    <div class=\"progress-bar " + rankingClass + "\" style=\"width: " + data[i] + "%\">");
                        out.println("        " + String.format("%.1f", data[i]) + "% <span>" + rankingText + "</span>");
                        out.println("    </div>");
                        out.println("</div>");
                        hasDisplayedBar = true;
                    }
                }
                if (!hasDisplayedBar) {
                    out.println("<p>No data available for this column.</p>");
                }
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while generating the progress bars.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
