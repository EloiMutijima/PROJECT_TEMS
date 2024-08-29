
<%-- 
    Document   : reports
    Created on : Jul 20, 2024, 5:19:01 AM
    Author     : user
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trainer Evaluation Management System</title>
    <link rel="stylesheet" type="text/css" href="fontawesome-free-6.5.2-web/css/all.css">
</head>
<style>
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

nav ul .icons a img {
    width: 20px;
    height: 20px;
    margin-left: 5px;
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
    grid-template-columns: repeat(3, 1fr);
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

.progress {
    background-color: #f0f0f0;
    border-radius: 4px;
    overflow: hidden;
}

.progress-bar {
    background-color: #002D72;
    height: 10px;
    border-radius: 4px 0 0 4px;
}

.notifications ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
}

.notifications li {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    border-bottom: 1px solid #ccc;
}

.notifications li span {
    cursor: pointer;
}

.clock {
    margin-top: 20px;
    font-size: 24px;
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
footer{
    background-color:#002D72;
    color: white;
    text-align: center;
    height:100px;
}
</style>
<body>
    <header>
        <h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>
    </header>
    <nav>
        <ul>
            <li><a href="new_user.jsp">Create User</a></li>
            <li><a href="#">Evaluations</a>
                <ul>
                    <li><a href="ViewModulesServlet">View Modules</a></li>
                    <li><a href="FeedbackServlet">Evaluated Modules</a></li>
                </ul>
            </li>
            <li><a href="TrainerProfileServlet">Trainers</a>
                
            </li>
            <li><a href="#">Reports</a></li>
            <li><a href="student_list">Student</a></li>
            <li class="icons">
                <a href="#">Notifications</a>
            </li>
            <li class="icons">
                <a href="logout" style="color:red;">Logout</a>
        </ul>
    </nav>
    <div class="card">
        
    </div>
</body>
</html>
