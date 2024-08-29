<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Trainer Evaluation Management System</title>
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
            height: calc(100vh - 60px); /* Adjust for footer height */
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
        .cards {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }
        .card {
            background-color: white;
            width: 48%;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        .logout {
            color: red;
            text-decoration: none;
        }
        .cards-container {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 20px;
            justify-content: center;
            margin-top: 20px;
        }
        .column {
            float: left;
            width: 47%;
            padding: 10px;
            height: 300px;
        }
        .row:after {
            content: "";
            display: table;
            clear: both;
        }
        footer {
            background-color: darkblue;
            color: white;
            text-align: center;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
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
                    <a href="SeeModules">View Modules</a>
                    <a href="ViewEvaluationServlet">Evaluated Modules</a>
                </div>
            </div>
            <div class="dropdown">
                <a href="#">Reports</a>
                <div class="dropdown-content">
                    <a href="SeeUsers">Users</a>
                </div>
            </div>
            <a href="TrainerForm.jsp" class="logout">Trainer</a>
            <a href="studentform.jsp" class="logout">Student</a>
            <a href="loginpage.jsp" class="logout">Logout</a>
        </div>
        <div class="content">
            <h2>Welcome to Admin Dashboard</h2>
            <jsp:include page="UserCountDashboardServlet" />
            <center>
                <div class="row">
                    <div class="column">
                        <jsp:include page="EvaluationPieChartServlet" />
                    </div>
                    <div class="column">
                        <jsp:include page="EvaluationBarChartServlet" />
                    </div>
                </div>
            </center>
        </div>
    </div>
    <footer>
        Trainer Evaluation Management System &copy; All rights reserved
    </footer>
</body>
</html>
