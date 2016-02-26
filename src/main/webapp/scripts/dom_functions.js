//Heartbeat funtion. It makes a call to the server every 5 seconds to keep connection alive.
function heartbeat() {
    window.setInterval(sendHeartbeat, 1250);
 }

//getUsers function. It fills div in main.jsp with the latest list of logged users.
 function getUsers(usersArray) {
   var loginDiv = document.getElementById("loginDiv");
   var usersDiv = document.getElementById("usersDiv");
   var mainDiv = document.getElementById("mainDiv");
   //Clears the old user list to replace with new.
   while (usersDiv.hasChildNodes()) {
        usersDiv.removeChild(usersDiv.firstChild);
    }
   loginDiv.style.display = "none";
   mainDiv.style.display = "block";
   for(i = 0; i < usersArray.length; i++) {
        var a = document.createElement("a"); 
        var p = document.createElement("P");
        var span = document.createElement("span");
        var user = document.createTextNode(" " + usersArray[i]);
        a.href = 'chatWindow.jsp';
        a.style.cssText = "text-decoration:none";
        p.style.cssText = "font-size:24px";
        span.style.cssText = "color:#037b58";
        span.className = "glyphicon glyphicon-user";
        p.appendChild(span);
        p.appendChild(user);
        a.appendChild(p);
        usersDiv.appendChild(a);
    }   
 }
 
 



