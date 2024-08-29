<%-- 
    Document   : profile
    Created on : Jul 27, 2024, 4:29:02 AM
    Author     : user
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Trainer Profile</title>
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

main img {
    max-width: 100%;
    height: auto;
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

.input{
    height:30px;
    width: 80%;
}
.column{
    float: left;
    width: 33.33%;
}
.row:after{
    content: "";
    display: table;
    clear: both;
}

.button{
background:rgba(59,79,149,1);
height:40px;
width:20%;
color: white;
font-weight: bold;	
border: none;
margin-left: 400px;
font-size:15px;
}
</style>
</head>
<body>
    <header>
        <h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>
    </header>
    <nav>
        <ul>
            <li><a href="trainers.jsp">Home</a></li>
            <li><a href="trainer_profile.jsp">Profile</a></li>
            <li><a href="ViewModulesServlet">Module</a>
            </li>
            <li><a href="RetrieveDataServlet">View Reports</a></li>
            <li><a href="login.jsp" class="logout">Logout</a></li>
        </ul>
    </nav>
    <main>
        <h2>PLEASE FILL OUT THE FOLLOWING FORM</h2>
        <div class="card">
            <h3 style="text-align: center;">CREATE TRAINER</h3>
            <center>
                <form method="post" action="trainerprofile">
                <div class="row">
                    <div class="column"> 
                        <table border="0" width="100%">
                            <tr>
                                <td>
            <label>National ID</label>
        </td>
        <td>
            <input type="text" placeholder="Enter your National ID" class="input" name="id"><br><br>
        </td>
        </tr>
        <tr>
            <td>
            <label>First name:</label>
        </td>
        <td>
            <input type="text" placeholder="Enter the last name" class="input" name="firstName"><br><br>
        </td>
    </tr>
    <tr>
        <td>
            <label>Last Name:</label>
        </td><td>
            <input type="text" placeholder="Enter the your age" class="input" name="lastName"><br><br>
        </td>
        </table>
        </div>
        <div class="column">
         <table border="0" width="100%">
                            <tr>
                                <td>
            <label>Department:</label>
        </td>
        <td>
            <select class="input" name="department">
                <option>ICT</option>
                <option>EEE</option>
                <option>AUTO</option>
                <option>MT</option>
                <option>Agriculture</option>
                <option>Hospitality</option>
            </select><br><br>
        </td>
        </tr>
        <tr>
            <td>
            <label>Option</label>
        </td>
        <td>
            <select class="input" name="option">
                <option>Information Technology</option>
                <option>Electrical Technology</option>
                <option>Mechanics Automobile</option>
                <option>Manufacturing Technology</option>
                <option>Horticulture Technology</option>
                <option>Culnary Arts</option>
                <option>Food and Beverages</option>
                <option>Room Division</option>
            </select><br><br>
        </td>
    </tr>
    <tr>
        </table>
        </div>
        <div class="column">
         <table border="0" width="100%">
                            <tr>
                                <td>
            <label>Email:</label>
        </td>
        <td>
            <input type="email" placeholder="Enter your email" class="input" name="email"><br><br>
        </td>
        </tr>
        <tr>
            <td>
            <label>Phone No</label>
        </td>
        <td>
            <input type="number" placeholder="Enter the phone number" class="input" name="number"><br><br>
        </td>
    </tr>
    <tr>
        </table>
        </div>
                </div>
                    <button class="button" style="margin-right:400px;">Create profile</button></form>
           
             </center> 
        </div> </body></html>