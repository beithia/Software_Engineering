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
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
       
    </head>
    <body>
        <!--%@ include file="header.html" %-->
        <div class="container"> 
            <div style="margin-top:60px;" class="mainbox col-md-7 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-info " id ="draggable">
                    <div class=" panel-heading ">
                        <div id="title" class="panel-title"> Chatting with: </div>
                    </div>                   
                    <div id="chatwindow" style="padding-top:10px" class="panel-body" >
                        <form id="chatform" class="form-vertical" role="form">
                            <div id="activeusers" style="border:solid 1px; margin-top:5px; margin-bottom:5px; margin-right:10px; height:285px; max-height:285px; overflow-y:auto" class ="panel-body col-sm-3"> 
                            <%-- names of active users --%>
                            </div>   
                            <div id ="messageArea"  style="margin-bottom:10px; height:200px; max-height:200px; overflow-y:auto">
                                <%--messages will go here when user clicks send--%>
                            </div>
                            
                            <div style="margin-bottom: 5px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-comment"></i></span>
                                <textarea id="message" style="resize:none" draggable="false" class="form-control" rows="2" wrap="hard"> </textarea>
                                <span class="input-group-addon"><input id="btnSend" class = "form-control btn btn-primary" type="button" value='Send'></input> </span> 
                            </div>
                            <div>
                                <input type="checkbox" id="sendOnEnter"> Send message on Enter </input>
                            </div>
                           <%-- <div style="margin-top:5px" class="input-group pull-right">
                                <input id="btnSend" class = "form-control btn btn-primary" type="button" value='Send'></input>  
                            </div> --%>
                            <script>
                                $(document).ready(function(){
                                    $('#draggable').draggable();
                                    $('#draggable').resizable();
                                }); 
                                <%-- adds message from text box to messageArea div --%>
                                var addMessage = function() {
                                    if ($("#message").val().trim()){
                                        var p = $("<p></p>");
                                        p.html("<b>You: </b>" + $("#message").val());
                                        $("#messageArea").append(p);
                                        $("#message").val("");
                                        <%-- force messageArea div to scroll to bottom --%>
                                        var objDiv = document.getElementById("messageArea");
                                        objDiv.scrollTop = objDiv.scrollHeight;
                                    }
                                };
                                $("#btnSend").click(addMessage);
                                $("#message").keydown(function(e){
                                    if (e.keyCode === 13 && $("#sendOnEnter").is(":checked")){
                                        addMessage();
                                    }
                                });
                            </script>
                        </form>  
                    </div>
                </div>
            </div>                               
        </div>
    </body>
</html>
