<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Trainer Evaluation Management System</title>
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
            height: 100vh;
        }
        .sidebar {
            background-color: darkblue;
            color: white;
            width: 200px;
            padding: 20px;
            box-sizing: border-box;
        }
        .sidebar a {
            display: block;
            color: white;
            padding: 10px 0;
            text-decoration: none;
            position: relative;
        }
        .sidebar a:hover {
            background-color: white;
            color: black;
        }
        .dropdown {
            position: relative;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: darkblue;
            min-width: 160px;
            z-index: 1;
            top: 100%;
            left: 0;
            border-radius: 5px;
        }
        .dropdown-content a {
            color: white;
            padding: 10px;
            text-decoration: none;
            display: block;
        }
        .dropdown-content a:hover {
            background-color: white;
            color: black;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        .content {
            flex: 1;
            padding: 20px;
            box-sizing: border-box;
        }
        .content h2 {
            color: darkblue;
        }
        .cards {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
            
        }
        .card {
            background-color: white;
            width: 48%;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .form-container label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        .form-container input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }
        .form-container input[type="submit"] {
            width: 100%;
            padding: 8px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .form-container input[type="submit"]:hover {
            background-color: #45a049;
        }
        .logout {
            color: red;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <header>
        TRAINER EVALUATION MANAGEMENT SYSTEM
    </header>
    <div class="container">
        <div class="sidebar">
            <a href="evaluators.jsp">Home</a>
            <a href="AllTrainers">Trainer List</a>
            <a href="new_module.jsp">Create Module</a>
            <a href="EvaluationsServlet">View Reports</a>
            <a href="Trainer_feedbacks.jsp">Trainer Feedbacks</a>
            <a href="loginpage.jsp">Logout</a>
        </div>
        <div class="content">
            <div class="form-container">
                <h2>Insert Module</h2>
                <form action="InsertCourseServlet" method="post">
                    <label for="module_code">Module Code:</label>
                    <input type="text" id="module_code" name="module_code" required>

                    <label for="module_name">Module Name:</label>
                    <input type="text" id="module_name" name="module_name" required>

                    <label for="department">Department:</label>
                    <input type="text" id="department" name="department" required>

                    <label for="program">Program:</label>
                    <input type="text" id="program" name="program" required>

                    <label for="level">Level:</label>
                    <input type="text" id="level" name="level" required>

                    <label for="trainer_id">Trainer ID:</label>
                    <input type="text" id="trainer_id" name="trainer_id" required>

                    <input type="submit" value="Insert Module">
                </form>
            </div>
        </div>
    </div>
</body>
</html>