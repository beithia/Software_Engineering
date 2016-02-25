

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    /* get the attributes from the request object that were delivered to this 
    .jsp page from the login servlet. */
    String userName = (String)request.getAttribute("userName");
    String[] onlineUsers = (String[])request.getAttribute("onlineUsers");                  
%>

        
<script>
   window.onbeforeunload = function(e) {
        var userName = document.getElementById("userName").value;
        var xhttp = new XMLHttpRequest();
        xhttp.open("POST", "logout.jsp?userName=" + userName, true);
        xhttp.send();
        //response.sendRedirect("index.jsp");
        return "This will Log you out";
    };
    var windowObjectReference;


    function openChat() {

        windowObjectReference = window.open("index.jsp", "Chatting With: ", "height=400,width=600,left=600,top=300");
    }
</script>
<br><br>
<div class="container">
    <div id="usersDiv"></div>
    <form name="logout" id="logout" action="logout.jsp">
        <input type="hidden" id="userName" name="username"  value="">
        <input type="hidden" name="visited" id="visited" value="" />
    </form>
    <br><br>
    <button class="btn btn-danger" name="logout" id="logout">Logout</button>
</div>
                
   
