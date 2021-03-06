// Create the websocket and connect to the server.
var endpoint = "/Chat/controller";
var socket = "";
var loggedUsers;
if ("WebSocket" in window){
    if (window.location.protocol === 'http:'){
        socket = new WebSocket("ws://" + window.location.host + endpoint);
    } else {
        socket = new WebSocket("wss://" + window.location.host + endpoint);
    }
} else {
    // The browser doesn't support WebSocket
    alert("WebSocket NOT supported by your Browser!");
}
// When a message is received call the onMessage function
socket.onmessage = onMessage;

// Parses the incoming message and processes it by action
function onMessage(event) {
    var receivedMessage = JSON.parse(event.data);
    console.log(event.data);
    if(receivedMessage.action === "login") {
        window.location.replace("main.jsp");
    }
    else if(receivedMessage.action === "usernames") {
        loggedUsers = receivedMessage.data;
        getUsers(receivedMessage.data);
        $(".activeUsers").each(function() {
            fillActiveUsers($(this).attr("id").substr(12));
        });
    } 
    else if(receivedMessage.action === "heartbeat") {
        console.log(receivedMessage.data);
    }
    else if(receivedMessage.action === "newChat") {
        var receivedMessage = JSON.parse(event.data);
        openChat(receivedMessage);
    }
    else if(receivedMessage.action === "sendMessage") {
        var receivedMessage = JSON.parse(event.data);
        writeMessage(receivedMessage.data[0], receivedMessage.data[1], receivedMessage.data[2] );
    }
    else if(receivedMessage.action === "removeChatUser") {
        $("#groupchatlist-" + receivedMessage.data[1] + " #" + receivedMessage.data[0] + "-" + receivedMessage.data[1]).remove();
        writeLeftRoomMsg(receivedMessage);
        fillActiveUsers(receivedMessage.data[1]); 
    }
    else if(receivedMessage.action === "addChatUser") {
        writeJoinRoomMsg(receivedMessage);
        $("#groupchatlist-" + receivedMessage.data[1]).append("<span><strong id=" + receivedMessage.data[0] + "-" + receivedMessage.data[1] + " style='color:#017D5A;'>" + receivedMessage.data[0] + "<br></strong></span>");   
        fillActiveUsers(receivedMessage.data[1]);
    }
}

//Sends the heartbeat signal message
function sendHeartbeat() {
    var username = document.getElementById('username').value;
    var pulse = {
        action: "heartbeat",
        data: [username]
    };
    socket.send(JSON.stringify(pulse));
}

// Sends the login signal message
function sendLogin() {
    var username = document.getElementById('username').value;
    var UserObject = {
        action: "login",
	data: [username]
    };
    socket.send(JSON.stringify(UserObject));             
    heartbeat();
}

// Sends the logout signal message
function sendLogout() {
    var username = document.getElementById('username').value;
    var DeviceAction = {
        action: "logout",
	data: [username]
    };
    clearInterval(timer);
    socket.send(JSON.stringify(DeviceAction));
}

function sendNewChat(user1, user2) {
    var chatUsers = {
        action: "newChat",
        data: [user1, user2]
    };
    socket.send(JSON.stringify(chatUsers));
}

function sendMessage(username, chatroomId, chatMessage) {
    var message = {
        action: "sendMessage",
        data: [username, chatroomId, chatMessage]
    };
    socket.send(JSON.stringify(message));
}

function removeChatUser(username, chatId) {
    var message = {
        action: "removeChatUser",
        data: [username, chatId]
    };
    socket.send(JSON.stringify(message));  
}

function addChatUser(username, id) {
    var message = {
        action: "addChatUser",
        data: [username, id]
    };
    socket.send(JSON.stringify(message));
}





