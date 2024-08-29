<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quality Assurance Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        header {
            background-color: darkblue;
            color: white;
            padding: 10px;
            text-align: center;
            font-size: 24px;
            font-weight: bold;
        }

        .container {
            display: flex;
            flex: 1;
            overflow: hidden;
        }

        .sidebar {
            background-color: darkblue;
            color: white;
            width: 250px; /* Increased width of the sidebar */
            padding: 20px;
            box-sizing: border-box;
            flex-shrink: 0;
            overflow-y: auto;
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
            overflow-y: auto;
            margin-left: 250px; /* Adjusted margin to accommodate the increased sidebar width */
        }

        .content h2 {
            color: darkblue;
            margin-top: 0;
        }

        .cards {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            margin-top: 20px;
        }

        .card {
            background-color: white;
            flex: 1 1 calc(100% - 40px); /* Adjusted to fit single column on small screens */
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            box-sizing: border-box;
        }

        @media (min-width: 600px) {
            .card {
                flex: 1 1 calc(50% - 20px); /* Two columns on medium screens */
            }
        }

        @media (min-width: 1024px) {
            .card {
                flex: 1 1 calc(33.33% - 20px); /* Three columns on large screens */
            }
        }

        .logout {
            color: red;
            text-decoration: none;
        }

        footer {
            background-color: darkblue;
            color: white;
            text-align: center;
            padding: 10px;
            margin-top: auto;
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
            <a href="AllTrainers">Trainer</a>
            <a href="new_module.jsp">Create Module</a>
            <a href="RepliesServlet">Trainer Feedback</a>
            <a href="EvaluationsServlet">View Reports</a>
            <a href="loginpage.jsp" class="logout">Logout</a>
        </div>
        <div class="content">
            <h2>QUALITY ASSURANCE OFFICER DASHBOARD</h2>
            <div class="cards">
                <div class="card">
                    <h3>WELCOME TO EVALUATOR DASHBOARD GENERAL RANKINGS</h3>
                    <jsp:include page="TrainerSearchServlet" />
                </div>
            </div>
        </div>
    </div>
    <footer>
        Trainer Evaluation Management System all rights reserved
    </footer>
</body>
</html>
