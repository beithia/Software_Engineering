var usersArray = [];
//Heartbeat funtion. It makes a call to the server every 5 seconds to keep connection alive.
function heartbeat() {
    window.setInterval(sendHeartbeat, 1250);
 }
 
//This function dynamically creates a new chat window.
function createWindow(details) {
    var top = parseInt((Math.random() * 125 + 100));
    var left = parseInt((Math.random() * 50));
    var right = parseInt((Math.random() * 50));
    var newChat = $(".clonable").clone();
    newChat.css({
        "display": "block", 
        "position":"absolute", 
        "margin-left":"30%"
    });
    newChat.removeClass("clonable");
    newChat.appendTo("#windows");
    newChat.attr({id:details.id});
    $("#" + details.id).css({"top":top, "left":left, "right":right, "border":"2px solid #E0E0E0", "z-index":"0"});
    $("#" + details.id + " #messageArea").attr('id', "messageArea-" + details.id);
    $("#" + details.id + " #btnSend").attr('id', "btnSend-" + details.id);
    $("#" + details.id + " #message").attr('id', "message-" + details.id);
    $("#" + details.id + " #topDiv #groupchatlist").attr('id', "groupchatlist-" + details.id);
    $("#" + details.id + " #title #closeBtn").attr("id", "closeBtn" + details.id);
    $("#" + details.id + " #title #activeUsers").attr('id', "activeUsers-" + details.id);
    $("#" + details.id + " #title #addUser").attr('id', "addUser-" + details.id);
    var activeUsersContent = document.getElementById("activeUsers-" + details.id);
    activeUsersContent.innerHTML = "";
    for(var i = 0; i < usersArray.length; i++) {
        activeUsersContent.innerHTML += "<option id='" + usersArray[i] + "-" + i + "'>" + usersArray[i] + "</option>"; 
    }
    fillChattingWith(details);
    console.log("Chatting with: " + details.users[1] + "\nChatID: " + details.id);
    $('.fullChatWindow').draggable();
    $('.fullChatWindow').resizable();
}

function fillChattingWith(details) {
    for(i = 0; i < details.users.length; i++) {
        $("#groupchatlist-" + details.id).append("<strong id='" + details.users[i] + "-" + details.id + "' style='color:#017D5A'>" + details.users[i] + "<br></strong>");
    }
}

function writeMessage(details) {
    var messageArea = document.getElementById("messageArea-" + details.data[1]);
    messageArea.innerHTML += "<p><strong style='color:#017D5A;margin-left:10px'>" + details.data[0] + ":</strong> " + "<strong style='color:#337AB7'>" +details.data[2] + "</strong></p>";
    $("#message-" + details.data[1]).val("");
}

//This function removes the user from the "In this room" div and writes to the chat window that the particular user has left the chatroom.
function writeLogoutMsg(details) {
    $("#groupchatlist-" + details.data[1] + " #" + details.data[0] + "-" + details.data[1]).remove();
    var logoutMsg = "<i style='color:#CCC5C5;margin-left:10px'>" + details.data[0] + " left the room</i>";
    var html = document.getElementById("messageArea-" + details.data[1]);
    html.innerHTML += logoutMsg;
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
function getUsers(details) {
  for(var i = 0; i < details.length; i++) { 
    usersArray[i] = details[i];
  }
  console.log(usersArray);
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
  for(i = 0; i < details.length; i++) {
      if(details[i] === loggedUser) {
          continue;
      }
      var a = document.createElement("a");
      var span = document.createElement("span");
      a.setAttribute("id", details[i]);
      a.setAttribute("class", "username");
      a.setAttribute("data-value", details[i]);
      a.setAttribute("onclick", "getName('" + details[i] + "')");
      a.style.cssText = "text-decoration:none;font-size:24px;cursor:pointer";
      a.innerHTML = " " + details[i] + "<br>";
      span.style.cssText = "color:#037b58;font-size:24px";
      span.className = "glyphicon glyphicon-user";
      usersDiv.appendChild(span);
      usersDiv.appendChild(a);
  }   
}

function closeWindow(details) {
    var id = details.id.substr(8);
    var username = $("#username").val();
    removeChatUser(username, id);
    $("#" + id).remove();
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

$("#message").keydown(function(e){
    if (e.keyCode === 13 && $("#sendOnEnter").is(":checked")){
        addMessage();
    }
});

window.onbeforeunload = function(e) {
    sendLogout();
    return undefined;
    };


 
 



