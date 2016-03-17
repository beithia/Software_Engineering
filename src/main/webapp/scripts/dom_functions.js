//Heartbeat funtion. It makes a call to the server every 5 seconds to keep connection alive.
function heartbeat() {
    window.setInterval(sendHeartbeat, 1250);
 }
 
function createWindow(details) {
    var newChat = $(".clonable").clone();
    newChat.css({
        "display": "block", 
        "position":"absolute", 
        "margin-left":"30%"
    });
    newChat.removeClass("clonable");
    newChat.appendTo("#windows");
    newChat.attr({id:details.id});
    $("#" + details.id + " #messageArea").attr('id', "messageArea-" + details.id);
    $("#" + details.id + " #btnSend").attr('id', "btnSend-" + details.id);
    $("#" + details.id + " #message").attr('id', "message-" + details.id);
    console.log("Chatting with: " + details.users[1] + "\nChatID: " + details.id);
    $('.fullChatWindow').draggable();
    $('.fullChatWindow').resizable();
}

function writeMessage(details) {
    var messageArea = document.getElementById("messageArea-" + details.data[1]);
    messageArea.innerHTML += "<p><strong style='color:#017D5A;margin-left:10px'>" + details.data[0] + ":</strong> " + "<strong style='color:#337AB7'>" +details.data[2] + "</strong></p>";
    $("#message-" + details.data[1]).val("");
}

function getMessage(sendBtn) {
    var user = $("#username").val();
    var chatId = sendBtn.id.substr(8);
    var message = $("#message-" + chatId).val();
    sendMessage(user, chatId, message); 
}


 function openChat(details) {
    $(".username").click(createWindow(details));
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
  welcomeTitle.innerHTML = "Welcome, <span>" + loggedUser + "!</span>";
  //Clears the old user list to replace with new.
  while (usersDiv.hasChildNodes()) {
       usersDiv.removeChild(usersDiv.firstChild);
  }
  loginDiv.style.display = "none";
  mainDiv.style.display = "block";
  for(i = 0; i < usersArray.length; i++) {
      if(usersArray[i] === loggedUser) {
          continue;
      }
      var a = document.createElement("a");
      var span = document.createElement("span");
      a.setAttribute("id", usersArray[i]);
      a.setAttribute("class", "username");
      a.setAttribute("data-value", usersArray[i]);
      a.setAttribute("onclick", "getName('" + usersArray[i] + "')");
      a.style.cssText = "text-decoration:none;font-size:24px;cursor:pointer";
      a.innerHTML = " " + usersArray[i] + "<br>";
      span.style.cssText = "color:#037b58;font-size:24px";
      span.className = "glyphicon glyphicon-user";
      usersDiv.appendChild(span);
      usersDiv.appendChild(a);
  }   
}

//adds message from text box to messageArea div
var addMessage = function() {
    if ($("#message").val().trim()) {
        var p = $("<p></p>");
        p.html("<b>You: </b>" + $("#message").val());
        $("#messageArea").append(p);
        $("#message").val("");
        //force messageArea div to scroll to bottom
        var objDiv = document.getElementById("messageArea");
        objDiv.scrollTop = objDiv.scrollHeight;
        var objDiv2 = document.getElementById("leftPadding");
        objDiv2.scrollTop = objDiv.scrollHeight;
    }
};


//$("#btnSend").click(addMessage);


$("#message").keydown(function(e){
    if (e.keyCode === 13 && $("#sendOnEnter").is(":checked")){
        addMessage();
    }
});


 
 



