<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.servlet.http.*, javax.servlet.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trainer Dashboard</title>
    <link rel="stylesheet" type="text/css" href="fontawesome-free-6.5.2-web/css/all.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            display: flex;
            height: 100vh;
            flex-direction: column;
        }
        header {
            background-color: darkblue;
            color: white;
            padding: 10px;
            text-align: center;
            font-size: 30px;
            font-weight: bold;
        }
        .container {
            display: flex;
            flex: 1;
        }
        .sidebar {
            background-color: darkblue;
            color: white;
            width: 200px;
            padding: 20px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
        }
        .sidebar a {
            display: block;
            color: white;
            padding: 10px 0;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: white;
            color: black;
        }
        .content {
            flex: 1;
            padding: 20px;
            box-sizing: border-box;
        }
        .evaluation-card {
            background-color: white;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .evaluation-card h3 {
            color: darkblue;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        textarea {
            width: 100%;
            height: 100px;
            margin-top: 10px;
        }
        .reply-form {
            margin-top: 20px;
        }
    </style>
    <script>
        function showSuccessMessage() {
            alert('Your reply has been sent successfully!');
        }
    </script>
</head>
<body>
    <header>
        TRAINER EVALUATION MANAGEMENT SYSTEM
    </header>
    <div class="container">
        <div class="sidebar">
            <a href="trainerpage.jsp">Home</a>
            <a href="Modules.jsp">Modules</a>
            <a href="Comments.jsp">View Reports</a>
            <a href="loginpage.jsp" class="logout">Logout</a>
        </div>
        <div class="content">
            <!-- Trainer Evaluation Comments -->
            <div class="evaluation-card">
                <h3>TRAINER EVALUATION COMMENTS</h3>
                <%
                    String username = (String) session.getAttribute("username");

                    if (username != null) {
                        Connection conn = null;
                        PreparedStatement pstmt = null;
                        ResultSet rs = null;

                        try {
                            // Load MySQL JDBC driver
                            Class.forName("com.mysql.cj.jdbc.Driver");

                            // Establish connection to the database
                            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "");

                            // Get Trainer_id based on the username
                            String sql = "SELECT Trainer_id FROM users WHERE Username = ?";
                            pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, username);
                            ResultSet rs2 = pstmt.executeQuery();

                            String trainerId = null;
                            if (rs2.next()) {
                                trainerId = rs2.getString("Trainer_id");
                            }

                            if (trainerId != null) {
                                // SQL query to get evaluation comments
                                String sql2 = "SELECT strengths, improvement_areas FROM evaluations WHERE Trainer_id = ?";
                                pstmt = conn.prepareStatement(sql2);
                                pstmt.setString(1, trainerId);
                                rs = pstmt.executeQuery();

                                if (rs.next()) {
                                    out.println("<form action='ReplyServlet' method='post' class='reply-form'>");
                                    out.println("<input type='hidden' name='trainer_id' value='" + trainerId + "'>");
                                    out.println("<table>");
                                    out.println("<tr><th>Strengths</th><th>Improvement Areas</th><th>Reply</th></tr>");
                                    do {
                                        out.println("<tr>");
                                        out.println("<td>" + rs.getString("strengths") + "</td>");
                                        out.println("<td>" + rs.getString("improvement_areas") + "</td>");
                                        out.println("<td><textarea name='reply' placeholder='Your reply here...'></textarea></td>");
                                        out.println("</tr>");
                                    } while (rs.next());
                                    out.println("</table>");
                                    out.println("<input type='hidden' name='username' value='" + username + "'>");
                                    out.println("<input type='submit' value='Submit Reply'>");
                                    out.println("</form>");
                                } else {
                                    out.println("<p>No comments found for this trainer.</p>");
                                }
                            } else {
                                out.println("<p>No Trainer ID found.</p>");
                            }
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                            out.println("<p>An error occurred: " + e.getMessage() + "</p>");
                        } finally {
                            try {
                                if (rs != null) rs.close();
                                if (pstmt != null) pstmt.close();
                                if (conn != null) conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        out.println("<p>You are not logged in.</p>");
                    }

                    // Check for success parameter and show notification
                    String replySuccess = request.getParameter("replySuccess");
                    if ("true".equals(replySuccess)) {
                        out.println("<script>showSuccessMessage();</script>");
                    }
                %>
            </div>
        </div>
    </div>
</body>
</html>
