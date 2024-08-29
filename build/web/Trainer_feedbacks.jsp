<<%-- 
    Document   : evaluators
    Created on : Jul 20, 2024, 6:18:39 AM
    Author     : user
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quality Assurance Dashboard</title>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<style>
    body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
}

header {
    background-color: #002D72;
    color: white;
    padding: 10px 0;
    text-align: center;
    margin-bottom: 10px;
}

nav {
    background-color: grey;
    margin-top:-10px;
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

nav ul li ul.dropdown {
    display: none;
    position: absolute;
    background-color: grey;
    top: 100%;
    left: 0;
    padding: 0;
    list-style-type: none;
    z-index: 1000;
}

nav ul li:hover ul.dropdown {
    display: block;
}

nav ul li ul.dropdown li {
    width: 150px;
}

nav ul li ul.dropdown li a {
    padding: 10px;
}

.logout {
    color: red;
}

main {
    padding: 20px;
    text-align: center;
}

main h2 {
    margin-bottom: 20px;
}
footer{
    background-color:#002D72;
    color: white;
    text-align: center;
    height:100px;
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
</style>
<body>
    <header>
        <h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>
    </header>
    <nav>
        <ul>
            <li><a href="evaluators.jsp">Home</a></li>
            <li><a href="student_list">Student List</a></li>
            <li><a href="Alltrain">Trainer</a>
                <ul class="dropdown"></ul>
                 
            </li>
            <li><a href="new_module.jsp">Evaluation Form</a></li>
            <li><a href="Feedback_evaluator">View Reports</a></li>
                        <li><a href="Trainer_feedbacks.jsp">Trainer Feedbacks</a></li>
            <li><a href="login.jsp" class="logout">Logout</a></li>
        </ul>
    </nav>
   <jsp:include page="Ev_feedbacks" />