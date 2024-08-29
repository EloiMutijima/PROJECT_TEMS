import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubmitFeedbacks")
public class SubmitFeedbacks extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
        String username = "root";
        String password = "";

        String moduleId = request.getParameter("moduleId");
        String feedback = request.getParameter("feedback");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "INSERT INTO feedback (module_id, feedback) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(moduleId));
            statement.setString(2, feedback);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Feedback submitted successfully
                request.getSession().setAttribute("feedbackMessage", "Yes, your feedback has been sent.");
            } else {
                // Feedback submission failed
                request.getSession().setAttribute("feedbackMessage", "Error: Feedback submission failed.");
            }

            statement.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            request.getSession().setAttribute("feedbackMessage", "An error occurred: " + e.getMessage());
        }

        // Redirect to RetrieveDataServlet
        response.sendRedirect("retrieveData");
    }
}
