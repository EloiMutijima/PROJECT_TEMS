<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css?family=Inter&display=swap" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet" />
    <link href="./css/main.css" rel="stylesheet" />
    <style type="text/css">
        h1 {
            background: rgba(59, 79, 149, 1);
            color: white;
            font-family: Inter;
            font-weight: bold;
            height: 130px;
            margin-top: -2px;
            left: 30px;
            font-size: 40px;
            text-align: center;
        }
        body {
            font-family: Inter;
        }
        .login_here {
            text-align: center;
            font-size: 30px;
            font-weight: bold;
        }
        .label {
            color: rgba(59, 79, 149, 1);
            font-weight: bold;
        }
        .input {
            height: 30px;
            width: 25%;
            border: solid rgba(59, 79, 149, 1);
        }
        .button {
            background: rgba(59, 79, 149, 1);
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
        .ps {
            margin-left: 135px;
            font-size: 14px;
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
</head>
<body>
    <header>
        <h1><br>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>
    </header>
    <h3 class="login_here">LOGIN HERE</h3>
    <center>
        <div class="div">
            <form method="post" action="LoginServlet">
                <label class="label">USERNAME : </label>
                <input class="input" type="text" name="username" required=""><br><br>
                <label class="label">USER TYPE : </label>
                <select name="userType" class="input">
                    <option value="Admin">Admin</option>
                    <option value="Student">Student</option>
                    <option value="Evaluator">Evaluator</option>
                    <option value="Trainer">Trainer</option>
                </select><br><br>
                <label class="label">PASSWORD : </label>
                <input class="input" type="password" name="password" required=""><br><br>
                <button class="button">LOGIN</button>
                <p>Do you have an account or <a href="create_student.jsp" class="link">SIGNUP</a></p>
                <span class="ps"><a href="forgot.jsp" class="link">FORGOT PASSWORD?</a></span>
            </form> 
            <% if (request.getParameter("error") != null) { %>
                <script type="text/javascript">
                    alert("<%= request.getParameter("error") %>");
                </script>
            <% } %>
        </div>
    </center>
</body>
</html>
