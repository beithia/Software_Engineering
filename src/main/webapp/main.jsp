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
        <script>
           window.onbeforeunload = function(e) {
                var userName = document.getElementById("userName").value;
                var xhttp = new XMLHttpRequest();
                xhttp.open("POST", "logout.jsp?userName=" + userName, true);
                xhttp.send();
                //response.sendRedirect("index.jsp");
                return "This will Log you out"
            };
            var windowObjectReference;
            

            function openChat() {
                
                windowObjectReference = window.open("index.jsp", "Chatting With: ", "height=400,width=600,left=600,top=300");
            }
        </script>
    </head>
    <body>
        <%@ include file="header.html" %>
        
        <div class="container">
            <%  String[] userNames = ActiveUsers.getUserNames();
                for (int i = 0; i < userNames.length; i++){
                    out.println("<p style='font-size:24px'><span style='color:#037b58; font-size' class=' glyphicon glyphicon-user'></span> <a href='#' onclick=openChat()>" + userNames[i]
                            + "</a></p>");
                }
            %>
            <p style="color:red; font-size:20px"> Logged user is: <%=userName %> </p>
            <form name="logout" id="logout" action="logout.jsp">
                <input type="hidden" id="userName" name="userName"  value=<%=userName%>>
                <input class="btn btn-danger" type="submit" name="logout" id="logout" value="Logout">
                <input type="hidden" name="visited" id="visited" value="" />
            </form>
        </div>
                
    </body>
</html>
