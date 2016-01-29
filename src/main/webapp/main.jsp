<%-- 
    Document   : main
    Created on : Jan 27, 2016, 12:57:37 PM
    Author     : beithia
--%>

<%@page import="com.ucmo.chat.ActiveUsers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%@ include file="header.html" %>
        <% for (int i = 0; i < ActiveUsers.Size(); i++){
                out.println("<p>" + ActiveUsers.getUser(i).getUserName() 
                        + ": IP = " + ActiveUsers.getUser(i).getIPAddress() + "</p>");
            }
        %>
    </body>
</html>
