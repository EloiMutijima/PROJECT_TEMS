<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet" />
    <style type="text/css">
        h1 {
            background: rgba(59,79,149,1);
            color: white;
            font-family: Inter;
            font-weight: bold;
            height: 130px;
            margin-top: -2px;
            font-size: 40px;
            text-align: center;
        }
        body {
            font-family: Inter;
        }
        .reset_password {
            text-align: center;
            font-size: 30px;
            font-weight: bold;
        }
        .label {
            color: rgba(59,79,149,1);
            font-weight: bold;
        }
        .input {
            height: 30px;
            width: 25%;
            border: solid rgba(59,79,149,1);
        }
        .button {
            background: rgba(59,79,149,1);
            height: 40px;
            width: 15%;
            color: white;
            font-weight: bold;
            border: none;
            margin-left: 100px;
            font-size: 15px;
        }
        .link {
            font-weight: bold;
            text-decoration: none;
            color: black;
        }
        p {
            font-size: 14px;
        }
    </style>
</head>
<body>
    <header>
        <h1><br>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>
    </header>
    <h3 class="reset_password">RESET PASSWORD</h3>
    <center>
        <form method="post" action="ForgotServlet">
            <label class="label">USERNAME : </label>
            <input class="input" type="text" name="username" required=""><br><br>
            <label class="label">NEW PASSWORD : </label>
            <input class="input" type="password" name="newPassword" required=""><br><br>
            <button class="button">RESET PASSWORD</button>
        </form>
    </center>
</body>
</html>

