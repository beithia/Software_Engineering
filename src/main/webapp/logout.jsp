<%-- 
    Document   : logout
    Created on : Jan 29, 2016, 2:41:25 PM
    Author     : beithia
--%>
<%@page import="com.ucmo.chat.ActiveUsers"%>
<%
    String userName = request.getParameter("userName");
    ActiveUsers.removeUser(userName);
    response.sendRedirect("index.jsp");
%>

