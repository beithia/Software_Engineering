<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
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
        
        <!-- Main div to be hidden initially, but displayed after the the user logs in --> 
        <div id="mainDiv" style="display:none">
            <%@include file="main.jsp" %>
        </div>
        <script type="text/javascript" src="scripts/websocket.js"></script>
        <script type="text/javascript" src="scripts/dom_functions.js"></script>
    </body>
</html>
