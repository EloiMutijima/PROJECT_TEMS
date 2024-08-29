<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.io.PrintWriter" %>
<%
    HttpSession Session = request.getSession(false);
   PrintWriter Pw=response.getWriter(); 
    if(Session!=null || Session.getAttribute("username")!=null) {
         Pw.print("<script>alert('Please login first')</script>");
       request.getRequestDispatcher("login.jsp").include(request, response); 
        return;
    }
   
%>

<!DOCTYPE html>
<html>
<head>
    <title>Protected Page</title>
</head>
<body>

    <c:choose>
        <c:when test="${sessionScope.username == 'admin'}">
            <h1>Welcome, <c:out value="${sessionScope.username}" />!</h1>
            <p>Successfully logged on</p>
              
        </c:when>
             
        <c:otherwise>
            <p>You are a regular user.</p>
        </c:otherwise>
    </c:choose>
            

    <c:if test="${not empty sessionScope.username}">
          <p>Your are: <c:out value="${sessionScope.username}" /></p>
    </c:if>
<p>Activity to be done : </p>
    <c:set var="userList" value="${['Registration', 'Report', 'Evaluation']}" />
    
    <ul>
        <c:forEach var="user" items="${userList}">
            <li><c:out value="${user}" /></li>
        </c:forEach>
    </ul>

        <%@include file="admins.jsp"%>
    
    

    
   
    <form action="LogoutServlet" method="post">
        <button type="submit">Logout</button>
    </form>
</body>
</html>