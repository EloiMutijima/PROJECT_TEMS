<%-- 
    Document   : trainers.jsp
    Created on : Jul 20, 2024, 6:15:21 AM
    Author     : user
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trainer Evaluation Management System</title>
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
}

nav ul li:hover ul {
    display: block;
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
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    gap: 20px;
}

.card {
    background-color: white;
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 20px;
    width: 250px;
    text-align: center;
}

.card img {
    width: 100%;
    height: auto;
}

.progress-bar {
    background-color: #e0e0e0;
    border-radius: 25px;
    overflow: hidden;
    height: 20px;
    margin: 10px 0;
}

.progress {
    background-color: #002D72;
    height: 100%;
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
            <li><a href="#">Home</a></li>
            <li><a href="trainer_profile.jsp">Profile</a></li>
            <li><a href="ViewModulesServlet">Module</a>
            </li>
            <li><a href="RetrieveDataServlet">View Reports</a></li>
            <li><a href="login.jsp" class="logout">Logout</a></li>
        </ul>
    </nav>
    <main>
        <h2>Trainer Dashboard</h2>
        <div class="dashboard">
            <img src="Lectures.jpg">
        </div>
    </main>
    <footer><br><br>Trainer Evaluation Management System all rights reserved</footer>
</body>
</html>