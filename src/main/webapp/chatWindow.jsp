<%-- 
    Document   : chatWindow
    Created on : Feb 7, 2016, 5:33:44 PM
    Author     : Jessica
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> JSP Page </title>
    </head>
    <body>
        <%@ include file="header.html" %>
        <div class="container">    
            <div id="loginbox" style="margin-top:60px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div id="title" class="panel-title"> Chatting with: </div>
                    </div>                   
                    <div style="padding-top:10px" class="panel-body" >
                        <form id="chatform" class="form-vertical" role="form">
                            <div id ="messageArea" style="margin-bottom:10px; height:200px; max-height:200px; overflow-y:auto">
                                <%--messages will go here when user clicks send--%>
                            </div>
                            <div style="margin-bottom: 5px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-comment"></i></span>
                                <textarea id="message" style="resize:none" draggable="false" class="form-control" rows="2" wrap="hard"> </textarea>
                                <span class="input-group-addon"><input id="btnSend" class = "form-control btn btn-primary" type="button" value='Send'></input> </span> 
                            </div>
                           <%-- <div style="margin-top:5px" class="input-group pull-right">
                                <input id="btnSend" class = "form-control btn btn-primary" type="button" value='Send'></input>  
                            </div> --%>
                            <script>
                                <%-- add event listener to send button --%>
                                document.getElementById("btnSend").addEventListener("click", addMessage);

                                <%-- adds message from text box to messageArea div --%>
                                function addMessage(){
                                    var element = document.createElement("P");
                                    var text = document.getElementById("message");
                                    var node = document.createTextNode("You: " + text.value);
                                    element.appendChild(node);
                                    document.getElementById("messageArea").appendChild(element);  
                                    document.getElementById("message").value = "";
                                    <%-- force messageArea div to scroll to bottom --%>
                                    var objDiv = document.getElementById("messageArea");
                                    objDiv.scrollTop = objDiv.scrollHeight;
                                }
                            </script>
                        </form>  
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
