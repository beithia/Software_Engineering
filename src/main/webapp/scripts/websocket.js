var socket = new WebSocket("ws://localhost:8080/Chat/controller");
socket.onmessage = onMessage;

function onMessage(event) {    
    alert("event");
}

function sendLogin(element) {
    var DeviceAction = {
        action: "login",
	username: "randomUser"
    };
    socket.send(JSON.stringify(DeviceAction));
}

function sendLogout(element) {
    var DeviceAction = {
        action: "logout",
	username: "randomUser"
    };
    socket.send(JSON.stringify(DeviceAction));
}


