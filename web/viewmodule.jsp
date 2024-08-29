<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - View Modules</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
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
            height: 100vh;
        }
        .sidebar {
            background-color: darkblue;
            color: white;
            width: 200px;
            padding: 20px;
            box-sizing: border-box;
        }
        .sidebar a {
            display: block;
            color: white;
            padding: 10px 0;
            text-decoration: none;
            position: relative;
        }
        .sidebar a:hover {
            background-color: white;
            color: black;
        }
        .dropdown {
            position: relative;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: darkblue;
            min-width: 160px;
            z-index: 1;
            top: 100%;
            left: 0;
            border-radius: 5px;
        }
        .dropdown-content a {
            color: white;
            padding: 10px;
            text-decoration: none;
            display: block;
        }
        .dropdown-content a:hover {
            background-color: white;
            color: black;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        .content {
            flex: 1;
            padding: 20px;
            box-sizing: border-box;
        }
        .content h2 {
            color: darkblue;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid darkblue;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: darkblue;
            color: white;
        }
    </style>
</head>
<body>
    <header>
        TRAINER EVALUATION MANAGEMENT SYSTEM
    </header>
    <div class="container">
        <div class="sidebar">
            <a href="new_user.jsp">Create User</a>
            <div class="dropdown">
                <a href="#">Evaluations</a>
                <div class="dropdown-content">
                    <a href="#">View Modules</a>
                    <a href="#">Evaluated Modules</a>
                </div>
            </div>
            <div class="dropdown">
                <a href="#">Reports</a>
                <div class="dropdown-content">
                    <a href="#">Users</a>
                    <a href="#">Trainer Reports</a>
                </div>
            </div>
            <a href="#">Student</a>
            <a href="#" class="logout">Logout</a>
        </div>
        <div class="content">
            <h2>Available Modules</h2>
            <table>
                <thead>
                    <tr>
                        <th>Module Code</th>
                        <th>Module Name</th>
                        <th>Department</th>
                        <th>Program</th>
                        <th>Level</th>
                        <th>Trainer ID</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Connection conn = null;
                        PreparedStatement pstmt = null;
                        ResultSet rs = null;
                        try {
                            // Database connection
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainer_evaluation_system", "root", "password");
                            String sql = "SELECT * FROM courses";
                            pstmt = conn.prepareStatement(sql);
                            rs = pstmt.executeQuery();

                            while(rs.next()) {
                                String moduleCode = rs.getString("Module_code");
                                String moduleName = rs.getString("Module_name");
                                String department = rs.getString("Department");
                                String program = rs.getString("Program");
                                String level = rs.getString("Level");
                                String trainerId = rs.getString("Trainer_id");

                                %>
                                <tr>
                                    <td><%= moduleCode %></td>
                                    <td><%= moduleName %></td>
                                    <td><%= department %></td>
                                    <td><%= program %></td>
                                    <td><%= level %></td>
                                    <td><%= trainerId %></td>
                                </tr>
                                <%
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
