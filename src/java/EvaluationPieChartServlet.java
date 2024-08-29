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

@WebServlet("/EvaluationPieChartServlet")
public class EvaluationPieChartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

            // Prepare the SQL query with the updated table name and columns
            String query = "SELECT classroom_setting, learning_consumables, ppe_availability, course_objectives, " +
                           "learning_units, learning_activities, content_mastery, instructional_technology, " +
                           "active_participation, communication_skills, assessments_provided, feedback_provision " +
                           "FROM evaluations";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            // Initialize counters for each ranking
            Map<Integer, Integer> rankingCounts = new HashMap<>();
            for (int i = 1; i <= 5; i++) {
                rankingCounts.put(i, 0);
            }

            // Process the results
            while (rs.next()) {
                for (int i = 1; i <= 12; i++) {
                    int ranking = rs.getInt(i);
                    if (ranking >= 1 && ranking <= 5) { // Ensure ranking is within valid range
                        rankingCounts.put(ranking, rankingCounts.get(ranking) + 1);
                    }
                }
            }

            // Calculate total responses
            int totalResponses = 0;
            for (int count : rankingCounts.values()) {
                totalResponses += count;
            }

            // Generate the pie chart data in JavaScript
            out.println("<html><head><title>Evaluation Pie Chart</title>");
            out.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
            out.println("<script type='text/javascript'>");
            out.println("google.charts.load('current', {'packages':['corechart']});");
            out.println("google.charts.setOnLoadCallback(drawChart);");
            out.println("function drawChart() {");
            out.println("var data = google.visualization.arrayToDataTable([");
            out.println("['Ranking', 'Percentage'],");

            for (Map.Entry<Integer, Integer> entry : rankingCounts.entrySet()) {
                double percentage = (totalResponses > 0) ? (double) entry.getValue() / totalResponses * 100 : 0;
                String label = "";
                switch (entry.getKey()) {
                    case 1: label = "Very Bad"; break;
                    case 2: label = "Bad"; break;
                    case 3: label = "Good"; break;
                    case 4: label = "Very Good"; break;
                    case 5: label = "Excellent"; break;
                }
                out.println("['" + label + "', " + percentage + "],");
            }

            out.println("]);");

            out.println("var options = {");
            out.println("title: 'Evaluation Rankings',");
            out.println("is3D: true,");
            out.println("};");

            out.println("var chart = new google.visualization.PieChart(document.getElementById('piechart'));");
            out.println("chart.draw(data, options);");
            out.println("}");
            out.println("</script>");
            out.println("</head><body>");
            out.println("<div id='piechart' style='width: 450px; height: 300px;'></div>");
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
