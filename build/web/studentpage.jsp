<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, javax.servlet.http.*, javax.servlet.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Profile</title>
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
        .profile-card, .module-list {
            background-color: white;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }
        .profile-card h3, .module-list h3 {
            color: darkblue;
        }
        .profile-card p, .module-list ul {
            margin: 10px 0;
        }
        .module-list ul {
            list-style-type: none;
            padding: 0;
        }
        .module-list li {
            background-color: #f4f4f4;
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        footer {
            background-color: darkblue;
            color: white;
            text-align: center;
            padding: 10px 0;
            width: 100%;
        }
        .column {
  float: left;
  width: 31%;
  padding: 10px;
}
.row:after {
  content: "";
  display: table;
  clear: both;
}
    </style>
</head>
<body>
    <header>
        TRAINER EVALUATION MANAGEMENT SYSTEM
    </header>
    <div class="container">
        <div class="sidebar">
            <a href="studentpage.jsp">Home</a>
            <a href="ModuleHandlerServlet">Evaluation Form</a>
            <a href="Aboutus.jsp">About Us</a>
            <a href="loginpage.jsp" class="logout">Logout</a>
        </div>
        <div class="content">
            <div class="profile-card">
                <h3>STUDENT IDENTIFICATIONS</h3>
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

                            // SQL query to get student profile details
                            String sql = "SELECT * FROM students WHERE RegNo = (SELECT RegNo FROM users WHERE Username = ?)";
                            pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, username);
                            rs = pstmt.executeQuery();

                            if (rs.next()) {
                                out.println("<p><strong>Reg NO:</strong> " + rs.getString("RegNo") + "</p>");
                                out.println("<p><strong>Names:</strong> " + rs.getString("Stud_name") + "</p>");
                                out.println("<p><strong>E-mail:</strong> " + rs.getString("email") + "</p>");
                                out.println("<p><strong>Telephone:</strong> " + rs.getString("Tel") + "</p>");
                                out.println("<p><strong>Department:</strong> " + rs.getString("Department") + "</p>");
                                out.println("<p><strong>Option:</strong> " + rs.getString("Options") + "</p>");
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

            <div class="module-list">
                <h3>OUR FACULTIES</h3>
                 <div class="row">
  <div class="column">
   <h4>1.Information Technology</h4>
   <h4>2.Automobile Technology</h4>
   <h4>3.Manufacturing Technology</h4>
  </div>
  <div class="column">
    <h4>4.Electrical Technology</h4>
    <h4>5.Horticulture Management</h4>
    <h4>6.Manufacturing Technology</h4>
  </div>
  <div class="column">
  <h4>7.Room Division Management</h4>
  <h4>8.Culnary Arts</h4>
  <h4>9.Food and Beverages</h4>
  </div>
</div>
            </div>
        </div>
    </div>
    <footer>
        Trainer Evaluation Management System &copy; All rights reserved
    </footer>
</body>
</html>
