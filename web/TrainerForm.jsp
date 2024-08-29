<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Trainer - Trainer Evaluation Management System</title>
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

        .container {
            display: flex;
            min-height: calc(100vh - 60px); /* Adjust for header height */
        }

        .sidebar {
            background-color: darkblue;
            color: white;
            width: 200px;
            padding: 20px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: start;
        }

        .sidebar a {
            color: white;
            padding: 10px 0;
            text-decoration: none;
            display: block;
            width: 100%;
            box-sizing: border-box;
        }

        .sidebar a:hover {
            background-color: white;
            color: black;
        }

        .dropdown {
            width: 100%;
        }

        .dropdown-content {
            display: none;
            background-color: darkblue;
            width: 100%;
        }

        .dropdown-content a {
            padding-left: 20px;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .content {
            flex: 1;
            padding: 20px;
            box-sizing: border-box;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .form-container {
            width: 60%;
            padding: 20px;
            border-radius: 10px;
            background-color: white;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        .form-container h2 {
            text-align: center;
            color: darkblue;
            font-size: 24px;
            margin-bottom: 20px;
        }

        .form-container label {
            font-size: 16px;
            color: darkblue;
            display: block;
            margin-bottom: 8px;
        }

        .form-container input[type="text"],
        .form-container input[type="email"],
        .form-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        .form-container input[type="submit"] {
            background-color: darkblue;
            color: white;
            cursor: pointer;
            font-weight: bold;
        }

        .form-container input[type="submit"]:hover {
            background-color: #004080;
        }

        .message {
            text-align: center;
            margin: 20px 0;
            font-size: 18px;
        }

        .success {
            color: green;
        }

        .error {
            color: red;
        }

        footer {
            background-color: darkblue;
            color: white;
            text-align: center;
            padding: 10px 0;
            position: relative;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
    <header>
        TRAINER EVALUATION MANAGEMENT SYSTEM
    </header>
    <div class="container">
        <div class="sidebar">
            <a href="adminpage.jsp">Go Home</a>
            <div class="dropdown">
                <a href="#">Evaluations</a>
                <div class="dropdown-content">
                    <a href="SeeModules">View Modules</a>
                    <a href="ViewEvaluationServlet">Evaluated Modules</a>
                </div>
            </div>
            <div class="dropdown">
                <a href="#">Reports</a>
                <div class="dropdown-content">
                    <a href="SeeUsers">Users</a>
                </div>
            </div>
            <a href="logout" class="logout">Logout</a>
        </div>
        <div class="content">
            <div class="form-container">
                <h2>Add Trainer</h2>
                <form action="AddTrainerServlet" method="post">
                    <label for="trainerId">Trainer ID:</label>
                    <input type="text" id="trainerId" name="trainerId" required>

                    <label for="trainerName">Trainer Name:</label>
                    <input type="text" id="trainerName" name="trainerName" required>

                    <label for="phone">Phone:</label>
                    <input type="text" id="phone" name="phone" required>

                    <label for="department">Department:</label>
                    <input type="text" id="department" name="department" required>

                    <label for="options">Options:</label>
                    <input type="text" id="options" name="options">

                    <label for="module">Module:</label>
                    <input type="text" id="module" name="module" required>

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>

                    <input type="submit" value="Add Trainer">
                </form>
                <div class="message">
                    <!-- Success or error messages will appear here -->
                </div>
            </div>
        </div>
    </div>
    <footer>
        Trainer Evaluation Management System &copy; All rights reserved
    </footer>
</body>
</html>
