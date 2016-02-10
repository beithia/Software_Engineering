<%-- 
    Document   : main
    Created on : Jan 27, 2016, 12:57:37 PM
    Author     : beithia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
    /* get the attributes from the request object that were delivered to this 
    .jsp page from the login servlet. */
    String userName = (String)request.getAttribute("userName");
    String[] onlineUsers = (String[])request.getAttribute("onlineUsers");                  
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
            <%  /* loop through the array of online user names and display them
                on the page*/
                for (int i = 0; i < onlineUsers.length; i++){ %>
                <p style='font-size:24px'>
                    <span style='color:#037b58; font-size' class=' glyphicon glyphicon-user'></span> 
                    <a href='#' onclick=openChat()> <%= onlineUsers[i] %> </a>
                </p>
            <%  } %>
            
            <p style="color:red; font-size:20px"> Logged user is: <%= userName %> </p>
            <form name="logout" id="logout" action="logout.jsp">
                <input type="hidden" id="userName" name="username"  value="<%= userName %>">
                <input class="btn btn-danger" type="submit" name="logout" id="logout" value="Logout">
                <input type="hidden" name="visited" id="visited" value="" />
            </form>
        </div>
                
    </body>
</html>
