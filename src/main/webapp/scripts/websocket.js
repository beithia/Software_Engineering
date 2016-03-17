// Create the websocket and connect to the server.
var endpoint = "/Chat/controller";
var socket = "";
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
    if(receivedMessage.action === "login") {
        window.location.replace("main.jsp");
    }
    else if(receivedMessage.action === "usernames") {
        getUsers(receivedMessage.data);
    }
    else if(receivedMessage.action === "heartbeat") {
        console.log(receivedMessage.data);
    }
    else if(receivedMessage.action === "newChat") {
        var receivedMessage = JSON.parse(event.data);
        console.log(receivedMessage);
        openChat(receivedMessage);
        //console.log(receivedMessage.id);
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
    socket.send(JSON.stringify(DeviceAction));
    window.location.replace("index.jsp");
}
    
    
function sendNewChat(user1, user2) {
    var chatUsers = {
        action: "newChat",
        data: [user1, user2]
    };
    socket.send(JSON.stringify(chatUsers));
}





