var socket = new WebSocket("ws://localhost:8080/Chat/controller");
socket.onmessage = onMessage;

function onMessage(event) {
    alert(event.data);
}

function sendLogin(element) {
    alert("working");    
    var DeviceAction = {
        action: "login",
	username: element
    };
    socket.send(JSON.stringify(DeviceAction));
}

function sendLogout(element) {
    var DeviceAction = {
        action: "logout",
	username: element
    };
    socket.send(JSON.stringify(DeviceAction));
}


