<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display Users</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .alert-custom {
            background-color: skyblue;
            color: darkblue;
            border: 1px solid darkblue;
            border-radius: 5px;
            margin-bottom: 15px;
        }
        .alert-custom .btn-danger {
            background-color: red;
            border: none;
            color: white;
        }
        .alert-custom .row {
            display: flex;
            align-items: center;
        }
        .alert-custom .col {
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <h2>User List</h2>
        <c:forEach var="user" items="${users}">
            <div class="alert alert-custom" role="alert">
                <div class="row">
                    <div class="col">
                        <strong>REG NO:</strong> ${user.regNo}
                    </div>
                    <div class="col">
                        <strong>Names:</strong> ${user.username}
                    </div>
                    <div class="col text-right">
                        <form action="DeleteUser" method="post" style="display: inline;">
                            <input type="hidden" name="regNo" value="${user.regNo}">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
