<%-- 
    Document   : students.jsp
    Created on : Jul 20, 2024, 6:00:56 AM
    Author     : user
--%>
<body> 
</body>
</html>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trainer Evaluation Management System</title>
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

nav {
    background-color: grey;
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

nav ul li ul {
    display: none;
    position: absolute;
    background-color: grey;
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

.logout {
    color: red;
}

main {
    padding: 20px;
    text-align: center;
}

main img {
    max-width: 100%;
    height: auto;
}
footer{
    background-color:#002D72;
    color: white;
    text-align: center;
    height:100px;
}
</style>
</head>
<body>
    <header>
        <h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>
    </header>
    <nav>
        <ul>
            <li><a href="#">Home</a></li>
            <li><a href="profile.jsp">Profile</a></li>
            <li><a href="retrieve_and_feedback.jsp">Evaluation Form</a>
                
            </li>
            <li><a href="AllTrainers">View Trainers</a>
            </li>
            <li><a href="Aboutus.jsp">About Us</a></li>
            <li><a href="login.jsp" class="logout">Logout</a></li>
        </ul>
    </nav>
    <main>
        <h2>WELCOME STUDENT</h2>
        <img src="WhatsApp Image 2024-07-30 at 6.16.59 PM.jpeg" alt="Welcome Student" width="100%">
    </main>
    <footer><br><br>Trainer Evaluation Management System all rights reserved</footer>
</body>
</html>