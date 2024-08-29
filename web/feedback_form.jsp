<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trainer Feedback Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            color: #333;
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
    </style>
</head>
<body>
    <h2 style="text-align:center">Trainer Feedback Form</h2>
    <p>Please indicate your impressions by rating trainers on the following elements; use the tick under the number which stands for: <br>
    1=Very poor 2=Poor 3=Good 4=Very Good 5=Excellent</p>
    
    <form action="SubmitFeedback" method="post">
        <table>
            <tr>
                <td>Semester:</td>
                <td><input type="text" name="semester"></td>
            </tr> 
            <tr>
                <td>Module Code:</td>
                <td><input type="text" name="module_code"></td>
            </tr>
            <tr>
                <td>Academic Year:</td>
                <td><input type="text" name="academic_year"></td>
            </tr>
            <tr>
                <td>Module Name:</td>
                <td><input type="text" name="module_name"></td>
            </tr>
            <tr>
                <td>Trainer:</td>
                <td><input type="text" name="trainer"></td>
            </tr>
            <tr>
                <td>Department:</td>
                <td><input type="text" name="department"></td>
            </tr>
            <tr>
                <td>Option:</td>
                <td><input type="text" name="option"></td>
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
</body>
</html>
