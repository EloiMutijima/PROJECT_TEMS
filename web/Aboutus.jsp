<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About IPRC Karongi</title>
    <style>
        /* Reset default margin and padding */
        body, h1, p {
            margin: 0;
            padding: 0;
        }

        /* Apply a full-size background image */
        .background {
            background-image: url('resources/images/background.jpg'); /* Assuming the background image is saved in the resources/images folder */
            background-size: cover;
            background-position: center;
            height: 100vh;
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white; /* White text color for contrast */
        }

        /* Style the text container */
        .text-container {
            background-color: rgba(0, 0, 0, 0.5); /* Slight dark overlay for better text visibility */
            padding: 20px;
            max-width: 800px;
            text-align: center;
        }

        /* Title styling */
        h1 {
            font-size: 3em;
            margin-bottom: 20px;
        }

        /* Paragraph styling */
        p {
            font-size: 1.2em;
            line-height: 1.6;
        }

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: darkblue;
            color: white;
            padding: 10px;
            text-align: center;
            font-size: 30px;
            font-weight: bold;
        }

        nav {
            background-color: darkblue;
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

        nav ul li a:hover {
            background-color: white;
            color: black;
        }

        nav ul li ul {
            display: none;
            position: absolute;
            background-color: darkblue;
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

        .container {
            display: flex;
            flex: 1;
        }

        .sidebar {
            background-color: darkblue;
            color: white;
            width: 200px;
            padding: 20px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
        }

        .sidebar a {
            display: block;
            color: white;
            padding: 10px 0;
            text-decoration: none;
        }

        .sidebar a:hover {
            background-color: white;
            color: black;
        }

        .content {
            flex: 1;
            padding: 20px;
            box-sizing: border-box;
        }

        footer {
            background-color: darkblue;
            color: white;
            text-align: center;
            padding: 10px 0;
            width: 100%;
        }

        .row {
            display: flex;
            flex-wrap: wrap;
        }

        .column {
            flex: 1;
            padding: 10px;
        }

        .profile-card, .module-list {
            background-color: white;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        .profile-card h3, .module-list h3 {
            color: darkblue;
        }

        .profile-card p, .module-list ul {
            margin: 10px 0;
        }

        .module-list ul {
            list-style-type: none;
            padding: 0;
        }

        .module-list li {
            background-color: #f4f4f4;
            border: 1px solid #ddd;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <header>
        TRAINER EVALUATION MANAGEMENT SYSTEM
    </header>
    <div class="container">
        <div class="sidebar">
            <a href="studentpage.jsp">Home</a>
            <a href="ModuleHandlerServlet">Evaluation Form</a>
            <a href="Aboutus.jsp">About Us</a>
            <a href="loginpage.jsp" class="logout">Logout</a>
        </div>
        <div class="content">
            <div class="background">
                <div class="text-container">
                    <h1>ABOUT IPRC KARONGI</h1>
                    <p>
                        The Integrated Polytechnic Regional College (IPRC) of Karongi is one of eight IPRCs that make up Rwanda Polytechnic. We offer Technical and Vocational Education Training at all levels up to level seven, commonly known as Advanced Diploma. We are determined to equip our students with practical knowledge and skills that enable them to create jobs and compete in the labor market. Our programs are subdivided into five departments specifically Agricultural Engineering with Horticulture Technology Program, Electrical & Electronics Engineering with Electrical Technology Program, Hospitality Management with Culinary Arts, Food & Beverage Management and Room Division Management Programs, Information & Communication Technology with IT Program as well as Mechanical Engineering with Automobile Technology and Manufacturing Technology Programs.
                    </p>
                </div>
            </div>
        </div>
    </div>
    <footer>
        Trainer Evaluation Management System &copy; All rights reserved
    </footer>
</body>
</html>
