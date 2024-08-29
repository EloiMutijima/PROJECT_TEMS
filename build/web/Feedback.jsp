<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .chat {
            margin: 20px;
        }
        .message {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            margin-bottom: 10px;
            padding: 10px;
            position: relative;
        }
        .message h4 {
            margin: 0;
            font-size: 16px;
        }
        .message p {
            margin: 5px 0;
        }
        .delete-button {
            position: absolute;
            right: 10px;
            top: 10px;
            background-color: red;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 3px;
            cursor: pointer;
        }
        .delete-button:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>
    <div class="chat">
        <h2>Feedback Messages</h2>
        <%
            ResultSet feedbacks = (ResultSet) request.getAttribute("feedbacks");
            while (feedbacks.next()) {
                String trainerId = feedbacks.getString("Trainer_id");
                String trainerName = feedbacks.getString("Trainer_name");
                String feedback = feedbacks.getString("Feedback");
        %>
            <div class="message">
                <h4><%= trainerName %> (ID: <%= trainerId %>)</h4>
                <p><%= feedback %></p>
                <form action="DeleteFeedbacksServlet" method="post" style="display: inline;">
                    <input type="hidden" name="trainerId" value="<%= trainerId %>">
                    <button type="submit" class="delete-button">Delete</button>
                </form>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>
