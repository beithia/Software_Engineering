

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    /* get the attributes from the request object that were delivered to this 
    .jsp page from the login servlet. */
    String userName = (String)request.getAttribute("userName");
    String[] onlineUsers = (String[])request.getAttribute("onlineUsers");                  
%>

        
<br><br>
<div class="container">
    <p id="welcomeTitle"></p>
    <div id="usersDiv"></div>
    <form name="logout" id="logout" action="logout.jsp">
        <input type="hidden" id="userName" name="username"  value="">
        <input type="hidden" name="visited" id="visited" value="" />
    </form>
    <br><br>
    <p id="test"></p>
    <button class="btn btn-danger" name="logout" id="logout" onclick="window.location.replace('index.jsp');">Logout</button>
</div>
                
   
