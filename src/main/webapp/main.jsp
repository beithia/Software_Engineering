<%-- 
    Document   : main
    Created on : Jan 27, 2016, 12:57:37 PM
    Author     : beithia
--%>

<%@page import="com.ucmo.chat.ActiveUsers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    String userName = request.getParameter("userName");
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="header.html" %>
        
        <div class="container">
            <% for (int i = 0; i < ActiveUsers.Size(); i++){
                    out.println("<p style='font-size:24px'><span style='color:#037b58; font-size' class=' glyphicon glyphicon-user'></span> " + ActiveUsers.getUser(i).getUserName() 
                            + "</p>");
                }
            %>
            <p style="color:red; font-size:20px"> Logged user is: <%=userName %> </p>
            <form action="logout.jsp">
                <input type="hidden" name="userName"  value=<%=userName%>>
                <input class="btn btn-danger" type="submit" name="logout" id="logout" value="Logout">
            </form>
        </div>
    </body>
</html>
