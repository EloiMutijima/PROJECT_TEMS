<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trainer Evaluation Management System</title>
    <link rel="stylesheet" type="text/css" href="fontawesome-free-6.5.2-web/css/all.css">
    <style>
        .column {
  float: left;
  width: 50%;
  padding: 10px;
  height: 300px;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        header {
            background-color: #002D72;
            color: white;
            padding: 10px 0;
            text-align: center;
        }
        nav {
            background-color: grey;
        }
        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        nav ul li {
            margin: 0 15px;
            position: relative;
        }
        nav ul li a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            display: block;
        }
        nav ul li ul {
            display: none;
            position: absolute;
            background-color: grey;
            top: 100%;
            left: 0;
            padding: 0;
            list-style-type: none;
            z-index: 1000;
        }
        nav ul li:hover ul {
            display: block;
        }
        nav ul li ul li {
            width: 150px;
        }
        nav ul li ul li a {
            padding: 10px;
        }
        .logout {
            color: red;
        }
        main {
            padding: 20px;
            text-align: center;
        }
        .dashboard {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
        }
        .card {
            background-color: white;
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: left;
        }
        .card h2 {
            margin-top: 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        table th, table td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
        }
        table th {
            background-color: #f0f0f0;
        }
        footer {
            background-color: #002D72;
            color: white;
            text-align: center;
            height: 100px;
        }
    </style>
</head>
<body>
    <header>
        <h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>
    </header>
    <nav>
        <ul>
            <li><a href="new_user.jsp">Create User</a></li>
            <li><a href="#">Evaluations</a>
                <ul>
                    <li><a href="ViewModulesServletadmins">View Modules</a></li>
                    <li><a href="FeedbackServlet">Evaluated Modules</a></li>
                </ul>
            </li>
                    <li><a href="TrainerProfileServlet">Trainers</a></li>   
            </li>
            <li><a href="#">Reports</a>
                <ul>
                    <li><a href="RetrieveUsersServlet">Users</a></li>
                    <li><a href="trainerreport">Trainer Reports</a></li>
                </ul>
            </li>
            <li><a href="student_list">Student</a></li>
            <li class="icons">
                <a href="login.jsp" style="color:red;">Logout</a>
            </li>
        </ul>
    </nav>
    <main>
        <div class="dashboard">
            <div class="card">
                <jsp:include page="StudentCountServlet" /> <br>   
                <jsp:include page="ModuleCountServlet" /> 
                <jsp:include page="EvaluatedModuleCountServlet" />
                <jsp:include page="TrainerCountServlet" />
            </div>
            <div class="card">
                 <jsp:include page="EvaluationPieChartServlet" />   
            </div>
            <div class="card">
                <h2>ALL USERS</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>UserType</th>
                            <th>Password</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            String url = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
                            String dbUser = "root";
                            String dbPassword = "";
                            String queryUsers = "SELECT Username, UserType, Password FROM users";

                            Connection connection = null;
                            PreparedStatement statement = null;
                            ResultSet resultSet = null;

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                connection = DriverManager.getConnection(url, dbUser, dbPassword);
                                statement = connection.prepareStatement(queryUsers);
                                resultSet = statement.executeQuery();

                                while (resultSet.next()) {
                                    String userName = resultSet.getString("Username");
                                    String userType = resultSet.getString("UserType");
                                    String userPassword = resultSet.getString("Password");
                        %>
                        <tr>
                            <td><%= userName %></td>
                            <td><%= userType %></td>
                            <td><%= userPassword %></td>
                        </tr>
                        <% 
                                }
                            } catch (ClassNotFoundException | SQLException e) {
                                out.println("<tr><td colspan='3'>An error occurred: " + e.getMessage() + "</td></tr>");
                            } finally {
                                try {
                                    if (resultSet != null) resultSet.close();
                                    if (statement != null) statement.close();
                                    if (connection != null) connection.close();
                                } catch (SQLException e) {
                                    out.println("<tr><td colspan='3'>An error occurred: " + e.getMessage() + "</td></tr>");
                                }
                            }
                        %>
                    </tbody>
                </table>
            </div>
            <div class="card">
                <h2>TRAINER FEEDBACKS</h2>
<jsp:include page="FeedbacksServlet" />
            </div>
        </div>
    </main>
    <footer>
        Trainer Evaluation Management System all rights reserved
    </footer>
</body>
</html>
