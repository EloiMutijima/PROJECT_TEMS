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
        .profile-card {
            background-color: white;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .profile-card h3 {
            color: darkblue;
        }
        .profile-card p {
            margin: 10px 0;
        }
        footer {
            background-color: darkblue;
            color: white;
            text-align: center;
            padding: 10px 0;
            width: 100%;
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
    </style>
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
            <div class="profile-card">
                <h3>TRAINER PROFILE</h3>
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

                            // SQL query to get trainer profile details
                            String sql = "SELECT t.Trainer_id, t.Trainer, t.Tel, t.Departments, t.Options, t.Modules " +
                                         "FROM trainers t " +
                                         "JOIN users u ON t.Trainer_id = u.Trainer_id " +
                                         "WHERE u.Username = ?";
                            pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, username);
                            rs = pstmt.executeQuery();

                            if (rs.next()) {
                                out.println("<p><strong>ID:</strong> " + rs.getString("Trainer_id") + "</p>");
                                out.println("<p><strong>Names:</strong> " + rs.getString("Trainer") + "</p>");
                                out.println("<p><strong>Phone:</strong> " + rs.getString("Tel") + "</p>");
                                out.println("<p><strong>Department:</strong> " + rs.getString("Departments") + "</p>");
                                out.println("<p><strong>Program:</strong> " + rs.getString("Options") + "</p>");
                                out.println("<p><strong>Modules:</strong> " + rs.getString("Modules") + "</p>");
                                
                                // Save Trainer_id for further queries
                                String trainerId = rs.getString("Trainer_id");

                                // Second query to get module details
                                String sql2 = "SELECT Module_code, Module_name, Department, Program, Level " +
                                              "FROM courses " +
                                              "WHERE Trainer_id = ?";
                                pstmt = conn.prepareStatement(sql2);
                                pstmt.setString(1, trainerId);
                                ResultSet rs2 = pstmt.executeQuery();
                                
                                if (rs2.next()) {
                                    out.println("<div class='profile-card'>");
                                    out.println("<h3>MODULE DETAILS</h3>");
                                    out.println("<table>");
                                    out.println("<tr><th>Module Code</th><th>Module Name</th><th>Department</th><th>Program</th><th>Level</th></tr>");
                                    do {
                                        out.println("<tr>");
                                        out.println("<td>" + rs2.getString("Module_code") + "</td>");
                                        out.println("<td>" + rs2.getString("Module_name") + "</td>");
                                        out.println("<td>" + rs2.getString("Department") + "</td>");
                                        out.println("<td>" + rs2.getString("Program") + "</td>");
                                        out.println("<td>" + rs2.getString("Level") + "</td>");
                                        out.println("</tr>");
                                    } while (rs2.next());
                                    out.println("</table>");
                                    out.println("</div>");
                                } else {
                                    out.println("<p>No module details found.</p>");
                                }
                                rs2.close();
                            } else {
                                out.println("<p>No profile information found.</p>");
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
                %>
            </div>
        </div>
    </div>
    <footer>
        Trainer Evaluation Management System &copy; All rights reserved
    </footer>
</body>
</html>
