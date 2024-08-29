import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditTrainerServlet")
public class EditTrainerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Update with your DB password

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String trainerId = request.getParameter("Trainer_id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sqlSelect = "SELECT * FROM trainers WHERE Trainer_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sqlSelect)) {
                    statement.setString(1, trainerId);
                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<meta charset='UTF-8'>");
                        out.println("<title>Update Trainer</title>");
                        out.println("<style>");
                        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
                        out.println("header { background-color: #002D72; color: white; padding: 10px 0; text-align: center; margin-bottom: 10px; }");
                        out.println("nav { background-color: grey; margin-top: -10px; }");
                        out.println("nav ul { list-style-type: none; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; }");
                        out.println("nav ul li { margin: 0 15px; position: relative; }");
                        out.println("nav ul li a { color: white; text-decoration: none; padding: 10px 15px; display: block; }");
                        out.println(".logout { color: red; }");
                        out.println("main { padding: 20px; text-align: center; }");
                        out.println("main h2 { margin-bottom: 20px; }");
                        out.println(".form-group { margin-bottom: 15px; }");
                        out.println(".form-group label { display: block; font-weight: bold; }");
                        out.println(".form-group input { padding: 8px; width: 300px; max-width: 100%; }");
                        out.println("input[type=submit] { padding: 10px 20px; background-color: #002D72; color: white; border: none; cursor: pointer; }");
                        out.println("</style>");
                        out.println("</head>");
                        out.println("<body>");

                        out.println("<header>");
                        out.println("<h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>");
                        out.println("</header>");

                        out.println("<nav>");
                        out.println("<ul>");
                        out.println("<li><a href='evaluators.jsp'>Home</a></li>");
                        out.println("<li><a href='student_list'>Student List</a></li>");
                        out.println("<li><a href='Alltrain'>Trainer</a></li>");
                        out.println("<li><a href='new_module.jsp'>Evaluation Form</a></li>");
                        out.println("<li><a href='FeedbackServlet'>View Reports</a></li>");
                        out.println("<li><a href='Trainer_feedbacks.jsp'>Trainer Feedbacks</a></li>");
                        out.println("<li><a href='login.jsp' class='logout'>Logout</a></li>");
                        out.println("</ul>");
                        out.println("</nav>");

                        out.println("<main>");
                        out.println("<h2>Update Trainer Information</h2>");
                        out.println("<form action='EditTrainerServlet' method='post'>");

                        out.println("<input type='hidden' name='Trainer_id' value='" + resultSet.getString("Trainer_id") + "'>");

                        out.println("<div class='form-group'>");
                        out.println("<label for='Trainer'>Trainer Name:</label>");
                        out.println("<input type='text' id='Trainer' name='Trainer' value='" + resultSet.getString("Trainer") + "' required>");
                        out.println("</div>");

                        out.println("<div class='form-group'>");
                        out.println("<label for='Tel'>Phone:</label>");
                        out.println("<input type='text' id='Tel' name='Tel' value='" + resultSet.getString("Tel") + "' required>");
                        out.println("</div>");

                        out.println("<div class='form-group'>");
                        out.println("<label for='Departments'>Department:</label>");
                        out.println("<input type='text' id='Departments' name='Departments' value='" + resultSet.getString("Departments") + "' required>");
                        out.println("</div>");

                        out.println("<div class='form-group'>");
                        out.println("<label for='Options'>Options:</label>");
                        out.println("<input type='text' id='Options' name='Options' value='" + resultSet.getString("Options") + "' required>");
                        out.println("</div>");

                        out.println("<div class='form-group'>");
                        out.println("<label for='Modules'>Modules:</label>");
                        out.println("<input type='text' id='Modules' name='Modules' value='" + resultSet.getString("Modules") + "' required>");
                        out.println("</div>");

                        out.println("<div class='form-group'>");
                        out.println("<label for='email'>Email:</label>");
                        out.println("<input type='email' id='email' name='email' value='" + resultSet.getString("email") + "' required>");
                        out.println("</div>");

                        out.println("<input type='submit' value='Update Trainer'>");

                        out.println("</form>");
                        out.println("</main>");

                        out.println("</body>");
                        out.println("</html>");
                    } else {
                        out.println("<h2>Trainer not found.</h2>");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h2>MySQL JDBC Driver not found.</h2>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String trainerId = request.getParameter("Trainer_id");
        String trainerName = request.getParameter("Trainer");
        String phone = request.getParameter("Tel");
        String department = request.getParameter("Departments");
        String options = request.getParameter("Options");
        String modules = request.getParameter("Modules");
        String email = request.getParameter("email");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sqlUpdate = "UPDATE trainers SET Trainer = ?, Tel = ?, Departments = ?, Options = ?, Modules = ?, email = ? WHERE Trainer_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(sqlUpdate)) {
                    statement.setString(1, trainerName);
                    statement.setString(2, phone);
                    statement.setString(3, department);
                    statement.setString(4, options);
                    statement.setString(5, modules);
                    statement.setString(6, email);
                    statement.setString(7, trainerId);

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        // Redirect to AllTrainers page after successful update
                        response.sendRedirect("AllTrainers");
                    } else {
                        out.println("<script>alert('Error updating trainer.');window.location='AllTrainers';</script>");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h2>MySQL JDBC Driver not found.</h2>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        } finally {
            out.close();
        }
    }
}