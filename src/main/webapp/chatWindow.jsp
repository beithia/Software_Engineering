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
        <link rel="stylesheet" type="text/css" href="css/styles.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
       
    </head>
    <body>
        <!--%@ include file="header.html" %-->
        <div class="container"> 
            <div style="margin-top:60px;" class="mainbox col-md-7 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                <div class="panel panel-info " id ="draggable">
                    <div class=" panel-heading ">
                        <div id="title" class="panel-title"> 
                             <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Active Users  <span class="caret"></span></button>
                         <button id="close" class="btn btn-xs pull-right">x</button><button id="minimize" class="btn btn-xs pull-right">-</button>
                        </div>
                    </div>                   
                    <div id="chatwindow"  class="panel-body" >
                        <form id="chatform" class="form-vertical" role="form">
                            <div id="groupchatlist" class ="panel-body col-sm-3"> Chatting with:
                            <%-- names of users in group chat --%>
                            </div>   
                            <div id ="messageArea">
                                <%--messages will go here when user clicks send--%>
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-comment"></i></span>
                                <textarea id="message" class="form-control" rows="2" wrap="hard"> </textarea>
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
