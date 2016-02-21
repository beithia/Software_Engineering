
 function getUsers(usersArray) {
   var loginDiv = document.getElementById("loginDiv");
   var usersDiv = document.getElementById("usersDiv");
   var mainDiv = document.getElementById("mainDiv");
   loginDiv.style.display = "none";
   mainDiv.style.display = "block";
   console.log(usersArray);
   
   for(i = 1; i < usersArray.length; i++) {
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



