<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create User - Trainer Evaluation Management System</title>
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
        .card {
            background-color: white;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            width: 100%;
            max-width: 600px;
            margin: 0 auto;
            text-align: left;
        }
        .card h3 {
            color: darkblue;
        }
        .input {
            height: 30px;
            width: 100%;
            border: 1px solid darkblue;
            border-radius: 5px;
            padding: 5px;
            margin-bottom: 10px;
        }
        .button {
            background-color: darkblue;
            color: white;
            font-weight: bold;
            border: none;
            padding: 10px 20px;
            font-size: 15px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
        }
        .button:hover {
            background-color: #0056b3;
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
    <script>
        function toggleTrainerIdField() {
            var userType = document.getElementById("userType");
            var trainerIdField = document.getElementById("trainerIdField");

            if (userType.value == "3") {
                trainerIdField.style.display = "block";
            } else {
                trainerIdField.style.display = "none";
            }
        }
    </script>
</head>
<body>
    <header>
        TRAINER EVALUATION MANAGEMENT SYSTEM
    </header>
    <div class="container">
         <div class="sidebar">
             <a href="adminpage.jsp">Go Home</a>
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
            <h2>Create New User</h2>
            <div class="card">
                <form method="post" action="InsertUsersServlet">
                    <label>User Roles</label>
                    <select id="userType" class="input" name="userType" onchange="toggleTrainerIdField()" required>
                        <option value="" disabled selected>Select Role</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option> <!-- Role ID 3 for Trainers -->
                    </select>

                    <label>E-mail</label>
                    <input type="email" placeholder="Enter email address" class="input" name="username" required>
                    
                    <label>Password</label>
                    <input type="password" placeholder="Enter user password" class="input" name="password" required>

                    <!-- Trainer ID field, initially hidden -->
                    <div id="trainerIdField" style="display: none;">
                        <label>Trainer ID</label>
                        <input type="text" placeholder="Enter Trainer ID" class="input" name="trainerId">
                    </div>
                    
                    <button type="submit" class="button">SIGNUP</button>
                    <p>For roles: 1 means <strong>Admin </strong><br>
                        2 means <strong>Quality Assurance Officer</strong> <br>
                        3 means <strong>Trainer</strong></p>
                </form>
            </div>
        </div>
    </div>
    <footer>
        Trainer Evaluation Management System &copy; All rights reserved
    </footer>
</body>
</html>
