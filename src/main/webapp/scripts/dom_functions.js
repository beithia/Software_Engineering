/* global loggedUsers, sendHeartbeat */
var timer;


function addUserToChat(btnDetails) {
    var id = btnDetails.id.substr(8);
    var username = $("#activeUsers-" + id).val();
    addChatUser(username, id);
}

function closeWindow(details) {
    var id = details.id.substr(8);
    var username = $("#username").val();
    removeChatUser(username, id);
    $("#" + id).remove();
}

function contains(a, b) {
    for (var i = 0; i < b.length; i++) {
        if (a === b[i]) {
            return true;
        }
    }
    return false;
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
        "margin-left":"30%",
        "top":top, 
        "left":left, 
        "right":right, 
        "border":"1px solid #000", 
        "z-index":"0"
    });
    newChat.removeClass("clonable");
    newChat.appendTo("#windows");
    newChat.attr({id:details.id});
    $("#" + details.id + " #messageArea").attr('id', "messageArea-" + details.id);
    $("#" + details.id + " #btnSend").attr('id', "btnSend-" + details.id);
    $("#" + details.id + " #message").attr('id', "message-" + details.id);
    $("#" + details.id + " #topDiv #groupchatlist").attr('id', "groupchatlist-" + details.id);
    $("#" + details.id + " #title #closeBtn").attr("id", "closeBtn" + details.id);
    $("#" + details.id + " #title #addUser").attr("id", "addUser-" + details.id);
    $("#" + details.id + " #title #activeUsers").attr('id', "activeUsers-" + details.id);
    fillChattingWith(details);
    fillActiveUsers(details.id);
    fillChatMessages(details);
    $('.fullChatWindow').draggable();
    $('.fullChatWindow').resizable();
    $(".fullChatWindow").mousedown(function(){
    $(".front").removeClass("front");
    $(this).addClass("front");
    
});
}

function fillActiveUsers(id){
    var chatUsers = [];
    $("#groupchatlist-" + id + " span").each(function(){
        chatUsers.push($(this).text());
    });
    var activeUsersContent = document.getElementById("activeUsers-" + id);
    if (activeUsersContent !== null){
        activeUsersContent.innerHTML = "";
        for(var i = 0; i < loggedUsers.length; i++) {
            if (!contains(loggedUsers[i], chatUsers)){
                activeUsersContent.innerHTML += "<option id='" + loggedUsers[i] + "-" + i + "'>" + loggedUsers[i] + "</option>";
            }
        }    
    }
}

function fillChattingWith(details) {
    for(i = 0; i < details.users.length; i++) {
        $("#groupchatlist-" + details.id).append("<span><strong id='" + details.users[i] + "-" + details.id + "' style='color:#017D5A'>" + details.users[i] + "<br></strong></span>");
    }
}

function fillChatMessages(details) {
    for(i = 0; i < details.messages.length; i++) {
        var splitMsg = [];
        splitMsg = details.messages[i].split(": " );
        writeMessage(splitMsg[0], details.id, splitMsg[1]);
    }
}

function getMessage(id) {
    var user = $("#username").val();
    var chatId = id.substr(8);
    var message = $("#message-" + chatId).val();
    sendMessage(user, chatId, message); 
}

function getName(user2) {
     var user1 = document.getElementById("username").value;
     sendNewChat(user1, user2); 
 }
 
//getUsers function. It fills div in main.jsp with the latest list of logged users.
function getUsers(details) {
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

//Heartbeat funtion. It makes a call to the server every 5 seconds to keep connection alive.
function heartbeat() {
   timer = window.setInterval(sendHeartbeat, 30000);
 }
 
 //Logs uses out if they navigate away from the page.
 window.onbeforeunload = function(e) {
    sendLogout();
    return undefined;
};

function openChat(details) {
   $(".username").click(createWindow(details));
}

function scrollToBottom(id){
    var objDiv = document.getElementById("messageArea-" + id);
    objDiv.scrollTop = objDiv.scrollHeight;
    var objDiv2 = document.getElementById("left-padding");
    objDiv2.scrollTop = objDiv.scrollHeight;
}

function writeJoinRoomMsg(details) {
    var joinMsg = "<p style='color:#CCC5C5;margin-left:10px'><i>" + details.data[0] + " joined the room</i></p>";
    var html = document.getElementById("messageArea-" + details.data[1]);
    html.innerHTML += joinMsg;
    scrollToBottom(details.data[1]);
}

//This function removes the user from the "In this room" div and writes to the chat window that the particular user has left the chatroom.
function writeLeftRoomMsg(details) {
    var logoutMsg = "<p style='color:#CCC5C5;margin-left:10px'><i>" + details.data[0] + " left the room</i></p>";
    var html = document.getElementById("messageArea-" + details.data[1]);
    html.innerHTML += logoutMsg;
    scrollToBottom(details.data[1]);
}

//Writes messages to the chat
function writeMessage(user, id, message) {
    var messageArea = document.getElementById("messageArea-" + id);
    messageArea.innerHTML += "<p style='margin-left:10px'><strong style='color:#017D5A;'>" + user + ":</strong> " + "<strong style='color:#337AB7'>" + message + "</strong></p>";
    $("#message-" + id).val("");
    scrollToBottom(id);
    
    //changes div color when message is written to messageArea
    //var test = $("#" + id).hasClass("front");
    //alert(test);
    if(!$("#" + id).hasClass("front")) {
        $("#" + id + " .panel-heading").attr("style", "background-image: linear-gradient(to bottom, #bbe86f 0, #bbe86f 100%) !important");
    }
    //changes color of panel-heading back to original when chatWindow is clicked
    $("#" + id).mousedown(function(){
    $("#" + id + " .panel-heading").attr("style", "background-image: linear-gradient(to bottom, #017d5a 0, #017d5a 100%) !important");
    });
}

$("body").delegate(".front textarea", "keydown", function(e){
    if (e.keyCode === 13 && $(".front #sendOnEnter").is(":checked")) {
        var id = $(this).attr("id");
        getMessage(id);
    }
});




