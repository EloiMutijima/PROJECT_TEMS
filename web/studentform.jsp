<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student - Trainer Evaluation Management System</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }
        header { background-color: darkblue; color: white; padding: 10px; text-align: center; font-size: 30px; font-weight: bold; }
        .container { padding: 20px; max-width: 800px; margin: auto; }
        form { background-color: white; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        label { display: block; margin: 10px 0 5px; }
        input, select { width: 100%; padding: 8px; margin-bottom: 10px; border: 1px solid #ddd; border-radius: 4px; }
        button { background-color: darkblue; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background-color: #0056b3; }
    </style>
</head>
<body>
    <header>TRAINER EVALUATION MANAGEMENT SYSTEM</header>
    <div class="container">
        <h2>Add Student</h2>
        <a href='adminpage.jsp' style="text-decoration: none;">Go Home</a><br><br>
        <form action="AddStudentServlet" method="post">
            <label for="stud_name">Name:</label>
            <input type="text" id="stud_name" name="stud_name" required>

            <label for="Regno">Reg No:</label>
            <input type="text" id="Regno" name="Regno" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="Tel">Tel:</label>
            <input type="text" id="Tel" name="Tel" required>

            <label for="Department">Department:</label>
            <input type="text" id="Department" name="Department" required>

            <label for="Options">Options:</label>
            <input type="text" id="Options" name="Options" required>

            <label for="level">Level:</label>
            <input type="text" id="level" name="level" required>

            <button type="submit">Add Student</button>
        </form>
    </div>
</body>
</html>