<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration Form</title>
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
    width:100%;
    border: solid #002D72;
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
width:52%;
margin-left:-100px;
color: white;
font-weight: bold;  
border: none;
margin-left:220px;
font-size:15px;
}
</style>
</head>
<body>
    <header>
        <h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>
    </header>
    <center>
        <h2>SIGNUP HERE</h2>
        <div class="card" style="width:60%;"><br><br>
<form method="post" action="signup">
<table width="80%">
    <tr>
        <td><label class="label">REG NO </label></td>
        <td><input class="input" type="text" name="regno" placeholder="Enter your regno" required=""></td>
    </tr>
    <tr>
        <td><label class="label">USERNAME </label></td>
        <td><input class="input" type="text" name="username" placeholder="Enter your username" required=""></td>
    </tr>
    <tr>
        <td><label class="label">E-MAIL </label></td>
        <td><input class="input" type="email" name="email" placeholder="Enter your e-mail " required=""></td>
    </tr>
    <tr>
        <td><label class="label">PASSWORD </label></td>
        <td><input class="input" type="password" name="password" placeholder="Enter your Password" required=""></td>
    </tr>
</table><br>
<button class="button">SIGNUP</button>
  </form> 
            <p style="text-align:center;">Have you already signed up click<strong> <a href="lolo.jsp" style="text-decoration:none;color:black;">Login to access system</a></strong></p>
        </div>
    </center>