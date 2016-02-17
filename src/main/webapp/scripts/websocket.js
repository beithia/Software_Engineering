var socket = new WebSocket("ws://localhost:8080/Chat/controller");
socket.onmessage = onMessage;

function onMessage(event) {    
    alert(event);
}

function sendLogin(element) {
    var JsonMessage = {
        action: "login",
	username: element
    };
    socket.send(JSON.stringify(JsonMessage));
}

function sendLogout(element) {
    var JsonMessage = {
        action: "logout",
	username: element
    };
    socket.send(JSON.stringify(JsonMessage));
}


