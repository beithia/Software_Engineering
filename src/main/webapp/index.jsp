<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/styles.css">
        <script src="scripts/jquery/jquery-1.12.1.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    </head>
    <body>
        <div class="container" >
            <div style="margin-top:40px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2" >
                <a href="index.jsp"><img src ="images/CommerceBank.png" width="622" height = "107"></a>
            </div>
        </div>
        <!--Login div to be displayed when the page is first accessed and hidden after the user has logged in -->
        <div id="loginDiv">
            <%@include file="login.jsp" %>
        </div>
        
        <!-- Main div. Contains the list of all users logged in. It is hidden initially, but displayed after the the user logs in --> 
        <div id="mainDiv" style="display:none;width:30%;float:left;">
            <%@include file="main.jsp" %>
        </div>
        
 
        <!-- Chat Window -->                 
            <div id ="draggable" class="panel panel-info fullChatWindow" style="">
                <div class=" panel-heading ">
                    <div id="title" class="panel-title"> 
                         <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Active Users  <span class="caret"></span></button>
                     <button id="close" class="btn btn-xs pull-right" onclick="closeWindow()">x</button><button id="minimize" class="btn btn-xs pull-right">-</button>
                    </div>
                </div>                   
                <div id="chatwindow" class="panel-body" >
                    <form id="chatform" class="form-vertical" role="form">

                        <div id="topDiv">
                            <div id="groupchatlist" class ="panel-body col-sm-3"> Chatting with:
                            <%-- names of users in group chat --%>
                            </div>   
                            <div id="left-padding">
                                <div id ="messageArea">
                                    <%--messages will go here when user clicks send--%>
                                </div>
                            </div>
                        </div>

                        <div id="bottomDiv">
                           <div id="topPadding">
                               <div class="input-group">
                                   <span class="input-group-addon"><i class="glyphicon glyphicon-comment"></i></span>
                                   <textarea id="message" class="form-control uiField" rows="2" wrap="hard"> </textarea>
                                   <span class="input-group-addon"><input id="btnSend" class = "form-control btn btn-primary" type="button" value='Send'></input> </span>  
                               </div>
                           </div>
                           <div id="checkboxDiv">
                               <input type="checkbox" id="sendOnEnter"> Send message on Enter </input>                                
                           </div>
                        </div>

                        
                    </form>  
                </div>
            </div>
        <script type="text/javascript" src="scripts/websocket.js"></script>
        <script type="text/javascript" src="scripts/dom_functions.js"></script>
    </body>
</html>
