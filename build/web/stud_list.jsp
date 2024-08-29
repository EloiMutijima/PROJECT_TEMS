<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student List</title>
    <link rel="stylesheet" href="styles.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<style type="text/css">
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
}

nav ul li:hover ul {
    display: block;
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

.dashboard {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
}

.card {
    background-color: white;
    border: 1px solid #ccc;
    border-radius: 8px;
    padding: 20px;
    width: 250px;
    text-align: center;
}

.card img {
    width: 100%;
    height: auto;
}

.progress-bar {
    background-color: #e0e0e0;
    border-radius: 25px;
    overflow: hidden;
    height: 20px;
    margin: 10px 0;
}

.progress {
    background-color: #002D72;
    height: 100%;
}
input[type=text] {
  width:60%;
  padding: 12px 20px;
  margin: 8px 0;
  box-sizing: border-box;
  border: none;
  border-bottom: 2px solid #002D72;
}
button{
    background-color:#002D72;
    color: white;
    border: none;
    height:40px;
    width:20%;
}
</style>
<body>
    <header>
        <h1>TRAINER EVALUATION MANAGEMENT SYSTEM</h1>
    </header>
    <nav>
        <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="#">Student List</a></li>
            <li><a href="#">Trainer</a>
                <ul class="dropdown"></ul>
                 
            </li>
            <li><a href="#">Evaluation Form</a></li>
            <li><a href="#">Evaluation History</a>
                <ul class="dropdown"></ul>
                  
            </li>
            <li><a href="#">View Reports</a></li>
            <li><a href="#">Feedback</a></li>
            <li><a href="#" class="logout">Logout</a></li>
        </ul>
    </nav>
    <center>
        <h2>ALL STUDENTS</h2>
        <div class="card" style="width: 80%;">
          <table border="0" width="90%">
        <tr style="background-color:#002D72; color: white;">
            <th>REG NO</th>
            <th>FIRST NAME</th>
            <th>LAST NAME</th>
            <th>DEPARTMENT</th>
            <th>LEVEL</th>
            <th>E-MAIL</th>
        </tr>
    </table>
    </center>