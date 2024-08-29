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

@WebServlet("/UserDisplayServlet")
public class UserDisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Load database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Fetch user counts by Rol_id
            String countSql = "SELECT Rol_id, COUNT(*) AS count FROM users GROUP BY Rol_id";
            ps = conn.prepareStatement(countSql);
            rs = ps.executeQuery();

            // Start HTML output
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>User Display</title>");
            out.println("<link rel='stylesheet' type='text/css' href='styles.css'>"); // Link to CSS
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }");
            out.println("header { background-color: darkblue; color: white; padding: 10px; text-align: center; font-size: 30px; font-weight: bold; }");
            out.println(".container { padding: 20px; }");
            out.println(".modal { display: none; position: fixed; z-index: 1; left: 0; top: 0; width: 100%; height: 100%; overflow: auto; background-color: rgba(0,0,0,0.4); }");
            out.println(".modal-content { background-color: #fefefe; margin: 15% auto; padding: 20px; border: 1px solid #888; width: 80%; }");
            out.println(".close { color: #aaa; float: right; font-size: 28px; font-weight: bold; }");
            out.println(".close:hover, .close:focus { color: black; text-decoration: none; cursor: pointer; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
            out.println("table, th, td { border: 1px solid black; }");
            out.println("th, td { padding: 10px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<header>User Display</header>");
            out.println("<div class='container'>");

            // Display links to user counts
            while (rs.next()) {
                int rolId = rs.getInt("Rol_id");
                int count = rs.getInt("count");
                out.println("<a href='#' onclick=\"showTable('table_" + rolId + "')\">Role " + rolId + " (" + count + " users)</a><br>");
                out.println("<div id='table_" + rolId + "' class='modal'>");
                out.println("<div class='modal-content'>");
                out.println("<span class='close' onclick=\"closeModal('table_" + rolId + "')\">&times;</span>");
                out.println("<table>");
                out.println("<thead><tr>");
                if (rolId == 1) {
                    out.println("<th>Username</th><th>Password</th>");
                } else if (rolId == 2) {
                    out.println("<th>Username</th><th>Trainer ID</th>");
                } else if (rolId == 3) {
                    out.println("<th>RegNo</th><th>Username</th>");
                } else if (rolId == 4) {
                    // Add columns for Rol_id 4 if needed
                    out.println("<th>Username</th>");
                }
                out.println("</tr></thead>");
                out.println("<tbody>");

                // Fetch and display user details based on Rol_id
                String detailSql = "SELECT RegNo, Username, Password, Trainer_id FROM users WHERE Rol_id = ?";
                ps = conn.prepareStatement(detailSql);
                ps.setInt(1, rolId);
                ResultSet userRs = ps.executeQuery();

                while (userRs.next()) {
                    out.println("<tr>");
                    if (rolId == 1) {
                        out.println("<td>" + userRs.getString("Username") + "</td>");
                        out.println("<td>" + userRs.getString("Password") + "</td>");
                    } else if (rolId == 2) {
                        out.println("<td>" + userRs.getString("Username") + "</td>");
                        out.println("<td>" + userRs.getString("Trainer_id") + "</td>");
                    } else if (rolId == 3) {
                        out.println("<td>" + userRs.getString("RegNo") + "</td>");
                        out.println("<td>" + userRs.getString("Username") + "</td>");
                    } else if (rolId == 4) {
                        // Display details for Rol_id 4 if needed
                        out.println("<td>" + userRs.getString("Username") + "</td>");
                    }
                    out.println("</tr>");
                }

                out.println("</tbody>");
                out.println("</table>");
                out.println("</div>");
                out.println("</div>");
                userRs.close();
            }

            out.println("</div>");
            out.println("<script>");
            out.println("function showTable(tableId) { document.getElementById(tableId).style.display = 'block'; }");
            out.println("function closeModal(modalId) { document.getElementById(modalId).style.display = 'none'; }");
            out.println("window.onclick = function(event) { if (event.target.classList.contains('modal')) { event.target.style.display = 'none'; } };");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error retrieving data from the database: " + e.getMessage() + "</p>");
        } finally {
            // Close resources
            if (rs != null) try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (Exception e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}
