// Create the websocket and connect to the server.
var socket = new WebSocket("ws://localhost:8080/Chat/controller");
// When a message is received call the onMessage function
socket.onmessage = onMessage;

// Parses the incoming message and processes it by action
function onMessage(event) {
    var receivedMessage = JSON.parse(event.data);
    if(receivedMessage.action === "login") {
        window.location.replace("main.jsp");
    }
    else if(receivedMessage.action === "logout") {
        window.location.replace("index.jsp");
    }
    else if(receivedMessage.action === "usernames") {
        for(i = 0; i < receivedMessage.data.length; i++) {
            alert(receivedMessage.data[i]);
        }
        window.location.replace("main.jsp");
    }
}

// Sends the login signal message
function sendLogin() { 
    var username = document.getElementById('username').value;
    //alert(username);
    var UserObject = {
        action: "login",
	data: [username]
    };
    socket.send(JSON.stringify(UserObject));
}

// Sends the logout signal message
function sendLogout(element) {
    var DeviceAction = {
        action: "logout",
	data: [element]
    };
    socket.send(JSON.stringify(UserObject));
}


