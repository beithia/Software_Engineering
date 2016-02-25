<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
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
