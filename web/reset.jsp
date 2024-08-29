<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
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
            <h3>Reset Password</h3>
            <form action="ResetPasswordServlet" method="post">
                <label for="regNo">Registration Number</label>
                <input type="text" id="regNo" class="input" name="regNo" placeholder="Enter your registration number" required>
                
                <label for="username">Username</label>
                <input type="text" id="username" class="input" name="username" placeholder="Enter your username" required>
                
                <label for="newPassword">New Password</label>
                <input type="password" id="newPassword" class="input" name="newPassword" placeholder="Enter your new password" required>
                
                <button type="submit" class="button">Reset Password</button>
            </form>
            <p>Back to <a href="loginpage.jsp" style="text-decoration:none;"><strong>Login</strong></a></p>
        </div>
    </main>
    <footer>
        Trainer Evaluation Management System &copy; All rights reserved
    </footer>
</body>
</html>
