<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration</title>
    <link rel="stylesheet" type="text/css" href="fontawesome-free-6.5.2-web/css/all.css">
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
        main {
            padding: 20px;
            text-align: center;
            margin-top: 50px;
        }
        .card {
            background-color: white;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            width: 50%;
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
        .alert {
            width: 50%;
            margin: 10px auto;
            padding: 15px;
            border-radius: 5px;
            text-align: center;
            font-weight: bold;
        }
        .alert.success {
            background-color: green;
            color: white;
        }
        .alert.error {
            background-color: red;
            color: white;
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
    <main>
        <div class="card">
            <h3>Register as a New Student</h3>
            <form method="post" action="StudentRegistrationServlet">
                <label>Reg No</label>
                <input type="text" placeholder="Enter Reg No" class="input" name="regno" required>
                
                <label>Username</label>
                <input type="text" placeholder="Enter username" class="input" name="username" required>
                
                <label>Password</label>
                <input type="password" placeholder="Enter your password" class="input" name="password" required>
                
                <button class="button" type="submit">Register</button>
            </form>
            <p>If you have already an account to the system please <a href="loginpage.jsp" style="text-decoration:none;"><strong>Login Here</strong></a></p>
            <div id="alert" class="alert" style="display: none;"></div>
        </div>
    </main>
    <footer>
        Trainer Evaluation Management System &copy; All rights reserved
    </footer>
</body>
</html>
