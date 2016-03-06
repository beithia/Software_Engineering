//Heartbeat funtion. It makes a call to the server every 5 seconds to keep connection alive.
function heartbeat() {
    window.setInterval(sendHeartbeat, 1250);
 }
 
 function getName(user2) {
     var user1 = document.getElementById("username").value;
     sendNewChat(user1, user2);
 }

//getUsers function. It fills div in main.jsp with the latest list of logged users.
 function getUsers(usersArray) {
   var loginDiv = document.getElementById("loginDiv");
   var usersDiv = document.getElementById("usersDiv");
   var mainDiv = document.getElementById("mainDiv");
   var loggedUser = document.getElementById("username").value;
   var welcomeTitle = document.getElementById("welcomeTitle");
   welcomeTitle.innerHTML = "Welcome, " + loggedUser + "!";
   //Clears the old user list to replace with new.
   while (usersDiv.hasChildNodes()) {
        usersDiv.removeChild(usersDiv.firstChild);
    }
   loginDiv.style.display = "none";
   mainDiv.style.display = "block";
   for(i = 0; i < usersArray.length; i++) {
        var a = document.createElement("a");
        var span = document.createElement("span");
        a.setAttribute("id", usersArray[i]);
        a.setAttribute("data-value", usersArray[i]);
        a.setAttribute("onclick", "getName('" + usersArray[i] + "')");
        a.href = '##';
        a.style.cssText = "text-decoration:none;font-size:24px";
        a.innerHTML = " " + usersArray[i] + "<br>";
        span.style.cssText = "color:#037b58;font-size:24px";
        span.className = "glyphicon glyphicon-user";
        usersDiv.appendChild(span);
        usersDiv.appendChild(a);
    }   
 }
 
 



