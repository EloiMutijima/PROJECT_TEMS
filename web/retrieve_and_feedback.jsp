<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Module Data and Trainer Feedback Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            color: #333;
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
            align-items: center;
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #002D72;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .rating {
            text-align: center;
        }
        textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            background-color: #002D72;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }
        button:hover {
            background-color: #001A4B;
        }
        footer{
    background-color:#002D72;
    color: white;
    width: 100%;
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
            <li><a href="students.jsp">Home</a></li>
            <li><a href="profile.jsp">Profile</a></li>
            <li><a href="retrieve_and_feedback.jsp">Evaluation Form</a>
                
            </li>
            <li><a href="AllTrainers">View Trainers</a>
            </li>
            <li><a href="#">About Us</a></li>
            <li><a href="login.jsp" class="logout">Logout</a></li>
        </ul>
    </nav>
    <h2 style="text-align:center">Modules allowed to be Evaluated</h2>
    <%
        String url = "jdbc:mysql://localhost:3306/trainer_evaluation_system";
        String user = "root";
        String password = "";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);

            // Create SQL query
            String sql = "SELECT Semester, Academic, M_code, M_name, Department, Trainer FROM newmodule";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
        %>
        <table>
            <tr>
                <th>Semester</th>
                <th>Academic</th>
                <th>Module Code</th>
                <th>Module Name</th>
                <th>Department</th>
                <th>Trainer</th>
            </tr>
            <%
                while (rs.next()) {
                    String semester = rs.getString("Semester");
                    String academic = rs.getString("Academic");
                    String mCode = rs.getString("M_code");
                    String mName = rs.getString("M_name");
                    String department = rs.getString("Department");
                    String trainer = rs.getString("Trainer");
            %>
            <tr>
                <td><%= semester %></td>
                <td><%= academic %></td>
                <td><%= mCode %></td>
                <td><%= mName %></td>
                <td><%= department %></td>
                <td><%= trainer %></td>
            </tr>
            <%
                }
            %>
        </table>
        <%
        } catch (ClassNotFoundException e) {
            out.println("<html><body><h3>Error: JDBC Driver not found - " + e.getMessage() + "</h3></body></html>");
        } catch (SQLException e) {
            out.println("<html><body><h3>Error: SQL Exception - " + e.getMessage() + "</h3></body></html>");
        } finally {
            // Close resources in the reverse order of their creation
            if (rs != null) try { rs.close(); } catch (SQLException e) { /* ignored */ }
            if (stmt != null) try { stmt.close(); } catch (SQLException e) { /* ignored */ }
            if (conn != null) try { conn.close(); } catch (SQLException e) { /* ignored */ }
        }
        %>
    
    <h2 style="text-align:center">Trainer Feedback Form</h2>
    <p>Please indicate your impressions by rating trainers on the following elements; use the tick under the number which stands for: <br>
    1=Very poor 2=Poor 3=Good 4=Very Good 5=Excellent</p>
    
    <form action="SubmitFeedback" method="post">
        <table>
            <tr>
                <td>Semester:</td>
                <td><input type="text" name="semester" required></td>
            </tr> 
            <tr>
                <td>Module Code:</td>
                <td><input type="text" name="module_code" required></td>
            </tr>
            <tr>
                <td>Academic Year:</td>
                <td><input type="text" name="academic_year" required></td>
            </tr>
            <tr>
                <td>Module Name:</td>
                <td><input type="text" name="module_name" required></td>
            </tr>
            <tr>
                <td>Trainer:</td>
                <td><input type="text" name="trainer" required></td>
            </tr>
            <tr>
                <td>Department:</td>
                <td><input type="text" name="department" required></td>
            </tr>
            <tr>
                <td>Option:</td>
                <td><input type="text" name="option" required></td>
            </tr>
        </table>

        <table>
            <thead>
                <tr>
                    <th>S/N</th>
                    <th>Learning Environment</th>
                    <th colspan="5" class="rating">Give marks (Use radio buttons)</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Classroom setting</td>
                    <td class="rating"><input type="radio" name="classroom_setting" value="1"></td>
                    <td class="rating"><input type="radio" name="classroom_setting" value="2"></td>
                    <td class="rating"><input type="radio" name="classroom_setting" value="3"></td>
                    <td class="rating"><input type="radio" name="classroom_setting" value="4"></td>
                    <td class="rating"><input type="radio" name="classroom_setting" value="5"></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Availability of learning consumables, tools, and equipment</td>
                    <td class="rating"><input type="radio" name="learning_consumables" value="1"></td>
                    <td class="rating"><input type="radio" name="learning_consumables" value="2"></td>
                    <td class="rating"><input type="radio" name="learning_consumables" value="3"></td>
                    <td class="rating"><input type="radio" name="learning_consumables" value="4"></td>
                    <td class="rating"><input type="radio" name="learning_consumables" value="5"></td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Availability of PPE</td>
                    <td class="rating"><input type="radio" name="ppe_availability" value="1"></td>
                    <td class="rating"><input type="radio" name="ppe_availability" value="2"></td>
                    <td class="rating"><input type="radio" name="ppe_availability" value="3"></td>
                    <td class="rating"><input type="radio" name="ppe_availability" value="4"></td>
                    <td class="rating"><input type="radio" name="ppe_availability" value="5"></td>
                </tr>
                <tr>
                    <td colspan="7" style="background-color: #f2f2f2; text-align: center;">Module/course content organization</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>Clarification of course objectives to students</td>
                    <td class="rating"><input type="radio" name="course_objectives" value="1"></td>
                    <td class="rating"><input type="radio" name="course_objectives" value="2"></td>
                    <td class="rating"><input type="radio" name="course_objectives" value="3"></td>
                    <td class="rating"><input type="radio" name="course_objectives" value="4"></td>
                    <td class="rating"><input type="radio" name="course_objectives" value="5"></td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>Provision of mapping of learning units, course handouts, and further references</td>
                    <td class="rating"><input type="radio" name="learning_units" value="1"></td>
                    <td class="rating"><input type="radio" name="learning_units" value="2"></td>
                    <td class="rating"><input type="radio" name="learning_units" value="3"></td>
                    <td class="rating"><input type="radio" name="learning_units" value="4"></td>
                    <td class="rating"><input type="radio" name="learning_units" value="5"></td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>Provision of sufficient learning activities for each learning outcome</td>
                    <td class="rating"><input type="radio" name="learning_activities" value="1"></td>
                    <td class="rating"><input type="radio" name="learning_activities" value="2"></td>
                    <td class="rating"><input type="radio" name="learning_activities" value="3"></td>
                    <td class="rating"><input type="radio" name="learning_activities" value="4"></td>
                    <td class="rating"><input type="radio" name="learning_activities" value="5"></td>
                </tr>
                <tr>
                    <td colspan="7" style="background-color: #f2f2f2; text-align: center;">Quality of Delivery</td>
                </tr>
                <tr>
                    <td>7</td>
                    <td>Trainer's punctuality</td>
                    <td class="rating"><input type="radio" name="punctuality" value="1"></td>
                    <td class="rating"><input type="radio" name="punctuality" value="2"></td>
                    <td class="rating"><input type="radio" name="punctuality" value="3"></td>
                    <td class="rating"><input type="radio" name="punctuality" value="4"></td>
                    <td class="rating"><input type="radio" name="punctuality" value="5"></td>
                </tr>
                <tr>
                    <td>8</td>
                    <td>Encouraging opinions and learners' feedback</td>
                    <td class="rating"><input type="radio" name="feedback_encouragement" value="1"></td>
                    <td class="rating"><input type="radio" name="feedback_encouragement" value="2"></td>
                    <td class="rating"><input type="radio" name="feedback_encouragement" value="3"></td>
                    <td class="rating"><input type="radio" name="feedback_encouragement" value="4"></td>
                    <td class="rating"><input type="radio" name="feedback_encouragement" value="5"></td>
                </tr>
                <tr>
                    <td>9</td>
                    <td>Mastery of content</td>
                    <td class="rating"><input type="radio" name="content_mastery" value="1"></td>
                    <td class="rating"><input type="radio" name="content_mastery" value="2"></td>
                    <td class="rating"><input type="radio" name="content_mastery" value="3"></td>
                    <td class="rating"><input type="radio" name="content_mastery" value="4"></td>
                    <td class="rating"><input type="radio" name="content_mastery" value="5"></td>
                </tr>
                <tr>
                    <td>10</td>
                    <td>Effective use of instructional technology</td>
                    <td class="rating"><input type="radio" name="instructional_technology" value="1"></td>
                    <td class="rating"><input type="radio" name="instructional_technology" value="2"></td>
                    <td class="rating"><input type="radio" name="instructional_technology" value="3"></td>
                    <td class="rating"><input type="radio" name="instructional_technology" value="4"></td>
                    <td class="rating"><input type="radio" name="instructional_technology" value="5"></td>
                </tr>
                <tr>
                    <td>11</td>
                    <td>Encouraging active participation and independent practice</td>
                    <td class="rating"><input type="radio" name="active_participation" value="1"></td>
                    <td class="rating"><input type="radio" name="active_participation" value="2"></td>
                    <td class="rating"><input type="radio" name="active_participation" value="3"></td>
                    <td class="rating"><input type="radio" name="active_participation" value="4"></td>
                    <td class="rating"><input type="radio" name="active_participation" value="5"></td>
                </tr>
                <tr>
                    <td>12</td>
                    <td>Communication skills and clarity in language</td>
                    <td class="rating"><input type="radio" name="communication_skills" value="1"></td>
                    <td class="rating"><input type="radio" name="communication_skills" value="2"></td>
                    <td class="rating"><input type="radio" name="communication_skills" value="3"></td>
                    <td class="rating"><input type="radio" name="communication_skills" value="4"></td>
                    <td class="rating"><input type="radio" name="communication_skills" value="5"></td>
                </tr>
                <tr>
                    <td>13</td>
                    <td>Provision of assignments and assessments</td>
                    <td class="rating"><input type="radio" name="assignments_assessments" value="1"></td>
                    <td class="rating"><input type="radio" name="assignments_assessments" value="2"></td>
                    <td class="rating"><input type="radio" name="assignments_assessments" value="3"></td>
                    <td class="rating"><input type="radio" name="assignments_assessments" value="4"></td>
                    <td class="rating"><input type="radio" name="assignments_assessments" value="5"></td>
                </tr>
                <tr>
                    <td>14</td>
                    <td>Provision of feedback to students on their progress</td>
                    <td class="rating"><input type="radio" name="progress_feedback" value="1"></td>
                    <td class="rating"><input type="radio" name="progress_feedback" value="2"></td>
                    <td class="rating"><input type="radio" name="progress_feedback" value="3"></td>
                    <td class="rating"><input type="radio" name="progress_feedback" value="4"></td>
                    <td class="rating"><input type="radio" name="progress_feedback" value="5"></td>
                </tr>
                <tr>
                    <td>15</td>
                    <td>Strengths of the trainer</td>
                    <td><textarea name="trainer_strengths" rows="3" cols="50"></textarea></td>
                </tr>
                <tr>
                    <td>16</td>
                    <td>Areas of improvement</td>
                    <td><textarea name="areas_of_improvement" rows="3" cols="50"></textarea></td>
                </tr>
            </tbody>
        </table>
        <p><button type="submit">Submit Feedback</button></p>
    </form>
    <footer><br><br>Trainer Evaluation Management System all rights reserved</footer>
</body>
</html>
