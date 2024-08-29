<%-- 
    Document   : protected
    Created on : Jul 20, 2024, 2:56:30 AM
    Author     : user
--%>
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
    <h1>Welcome, <c:out value="${sessionScope.username}" />!</h1>
    <p>This is a protected page only accessible to logged-in users.</p>

    <c:choose>
        <c:when test="${sessionScope.username == 'trainer'}">
            <p>You have admin privileges.</p>
              
        </c:when>
             
        <c:otherwise>
            <p>You are a regular user.</p>
        </c:otherwise>
    </c:choose>
            

    <c:if test="${not empty sessionScope.username}">
     <%@include file="trainers.jsp"%>
    </c:if>
    

    
   
    <form action="LogoutServlet" method="post">
        <button type="submit">Logout</button>
    </form>
</body>
</html>