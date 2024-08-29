<%-- 
    Document   : login
    Created on : Jul 20, 2024, 2:52:41 AM
    Author     : user
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    
	<link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
	<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet" />
	<link href="./css/main.css" rel="stylesheet" />
    <style type="text/css">
	h1{
background: rgba(59,79,149,1);
color:white; 	
font-family: Inter;
font-weight: bold; 
height:130px;
margin-top:-2px;
left:30px;
font-size:40px;
text-align: center;
}
body{
	font-family: Inter;
}
.login_here{
	text-align: center;
	font-size:30px;
	font-weight: bold;
}
.label{
	color: rgba(59,79,149,1);
	font-weight: bold; 
}
.input{
	height:30px;
	width:25%;
	border:solid rgba(59,79,149,1);
	
}
.button{
background:rgba(59,79,149,1);
height:40px;
width:15%;
color: white;
font-weight: bold;	
border: none;
margin-left: 100px;
font-size:15px;
}
.link{
	font-weight: bold;
	text-decoration: none;
	color: black;
}
p{
	font-size:14px;
}
.ps{
	margin-left:135px;
	font-size:14px;
}
</style>
</head>
<body>
    	<header>
		<h1><br>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>
	</header>
<h3 class="login_here">LOGIN HERE</h3>
<center>
       <form method="post" action="LoginServlet">
	<label class="label">USERNAME : </label>
	<input class="input" type="text" name="username" ><br><br>
	<label class="label">PASSWORD : </label>
	<input class="input" type="password" name="password"><br><br>
	<button class="button">LOGIN</button>
	<p>Did you have already an account or <a href="signup.jsp" class="link">SIGNUP</a></p>
	<span class="ps"><a href="forgotPassword.html" class="link">FORGOT PASSWORD ?</a></span></center>

  </form> 
</body>
</html>