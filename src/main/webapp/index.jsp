<%-- 
    Document   : login
    Created on : Jan 25, 2016, 6:53:40 PM
    Author     : beithia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%@ include file="header.html" %>
        <div class="container">    
            <div id="loginbox" style="margin-top:60px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title">Sign In to Commerce Chat</div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >
                        <div style="display:none" id="login" class="alert alert-danger col-sm-12"></div>
                        <form id="loginform" class="form-horizontal" role="form" action="Login" method="post">
                            <label>Username</label>
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="username" type="text" class="form-control" name="username"> 
                            </div>
                            <label>Password</label>    
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="login-password" type="password" class="form-control" name="password" >
                            </div>
                            
                        </form>  
                        <div style="margin-top:10px" class="input-group">
                            <button class = "form-control" value ="Login" onclick="sendLogin('Jeff')">Login</button>
                         </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

