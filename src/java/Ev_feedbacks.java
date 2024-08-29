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

@WebServlet("/FeedbackServlet")
public class Ev_feedbacks extends HttpServlet {
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

            // Prepare the SQL query to retrieve feedback
            String query = "SELECT id, module_id, feedback FROM feedback";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            // Start of HTML response
            out.println("<html><head><title>Trainer Feedback</title>");
            out.println("<style>");
            out.println(".chat-container { max-width: 600px; margin: auto; padding: 10px; font-family: Arial, sans-serif; }");
            out.println(".chat-message { background-color: #f1f1f1; padding: 10px; margin: 10px 0; border-radius: 10px; position: relative; }");
            out.println(".chat-message .module { font-weight: bold; color: #333; }");
            out.println(".chat-message .feedback { color: #555; margin-top: 5px; }");
            out.println(".delete-button { position: absolute; top: 10px; right: 10px; background-color: red; color: white; border: none; padding: 5px 10px; border-radius: 5px; cursor: pointer; }");
            out.println("</style>");
            out.println("</head><body>");
            out.println("<div class='chat-container'>");

            // Process and display each feedback as a chat message with a delete button
            while (rs.next()) {
                int id = rs.getInt("id");
                int moduleId = rs.getInt("module_id");
                String feedback = rs.getString("feedback");

                out.println("<div class='chat-message'>");
                out.println("<div class='module'>Module ID: " + moduleId + "</div>");
                out.println("<div class='feedback'>" + feedback + "</div>");
                out.println("<form method='post' action='DeleteFeedbackServlet' style='display:inline;'>");
                out.println("<input type='hidden' name='feedbackId' value='" + id + "'/>");
                out.println("</form>");
                out.println("</div>");
            }

            // End of HTML response
            out.println("</div>");
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
