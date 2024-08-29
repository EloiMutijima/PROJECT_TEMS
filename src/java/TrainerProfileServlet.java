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

@WebServlet("/TrainerProfileServlet")
public class TrainerProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Replace with your actual DB password

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Trainer Profile</title>");
        out.println("<style>");
        out.println("    body {\n" +
"    font-family: Arial, sans-serif;\n" +
"    margin: 0;\n" +
"    padding: 0;\n" +
"}\n" +
"\n" +
"header {\n" +
"    background-color: #002D72;\n" +
"    color: white;\n" +
"    padding: 10px 0;\n" +
"    text-align: center;\n" +
"}\n" +
"\n" +
"nav {\n" +
"    background-color: grey;\n" +
"}\n" +
"\n" +
"nav ul {\n" +
"    list-style-type: none;\n" +
"    margin: 0;\n" +
"    padding: 0;\n" +
"    display: flex;\n" +
"    justify-content: center;\n" +
"}\n" +
"\n" +
"nav ul li {\n" +
"    margin: 0 15px;\n" +
"    position: relative;\n" +
"}\n" +
"\n" +
"nav ul li a {\n" +
"    color: white;\n" +
"    text-decoration: none;\n" +
"    padding: 10px 15px;\n" +
"    display: block;\n" +
"}\n" +
"\n" +
"nav ul li ul {\n" +
"    display: none;\n" +
"    position: absolute;\n" +
"    background-color: grey;\n" +
"    top: 100%;\n" +
"    left: 0;\n" +
"    padding: 0;\n" +
"    list-style-type: none;\n" +
"    z-index: 1000;\n" +
"}\n" +
"\n" +
"nav ul li:hover ul {\n" +
"    display: block;\n" +
"}\n" +
"\n" +
"nav ul li ul li {\n" +
"    width: 150px;\n" +
"}\n" +
"\n" +
"nav ul li ul li a {\n" +
"    padding: 10px;\n" +
"}\n" +
"\n" +
".logout {\n" +
"    color: red;\n" +
"}\n" +
"\n" +
"main {\n" +
"    padding: 20px;\n" +
"    text-align: center;\n" +
"}\n" +
"\n" +
"main img {\n" +
"    max-width: 100%;\n" +
"    height: auto;\n" +
"}\n" +
".card {\n" +
"    background-color: white;\n" +
"    border: 1px solid #ccc;\n" +
"    border-radius: 8px;\n" +
"    padding: 20px;\n" +
"    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
"    text-align: left;\n" +
"}\n" +
"\n" +
".card h2 {\n" +
"    margin-top: 0;\n" +
"}\n" +
"\n" +
".input{\n" +
"    height:30px;\n" +
"    width: 80%;\n" +
"}\n" +
".column{\n" +
"    float: left;\n" +
"    width: 33.33%;\n" +
"}\n" +
".row:after{\n" +
"    content: \"\";\n" +
"    display: table;\n" +
"    clear: both;\n" +
"}\n" +
"\n" +
".button{\n" +
"background:rgba(59,79,149,1);\n" +
"height:40px;\n" +
"width:20%;\n" +
"color: white;\n" +
"font-weight: bold;	\n" +
"border: none;\n" +
"margin-left: 100px;\n" +
"font-size:15px;\n" +
"}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<header>\n" +
"        <h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>\n" +
"    </header>");
        out.println("<h1 style='text-align:center;'>Trainer Profiles</h1>");
        out.println("<center><table width='90%'>");
        out.println("<tr style='background-color:darkblue;color:white;'><th>Id</th><th>FirstName</th><th>LastName</th><th>Department</th><th>Options</th><th>Email</th><th>Phone</th><th>Actions</th></tr>");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            String selectSql = "SELECT * FROM trainer_profile";
            pstmt = conn.prepareStatement(selectSql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString("FirstName") + "</td>");
                out.println("<td>" + rs.getString("LastName") + "</td>");
                out.println("<td>" + rs.getString("department") + "</td>");
                out.println("<td>" + rs.getString("options") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("phone") + "</td>");
                out.println("<td>");
                out.println("<form method='get' action='EditTrainerServlet'>");
                out.println("<input type='hidden' name='id' value='" + id + "'/>");
                out.println("<input type='submit' value='Edit'/ style='background-color:green;color:white;height:30px;width:50px;border:none;'>");
                out.println("</form>");
                out.println("<form method='post' action='DeleteTrainerServlet'>");
                out.println("<input type='hidden' name='id' value='" + id + "'/>");
                out.println("<input type='submit' value='Delete'/ style='background-color:red;color:white;height:30px;width:50px;border:none;'>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table></center>");
            out.println("</body>");
            out.println("</html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
