var socket = new WebSocket("ws://localhost:8080/Chat/controller");
socket.onmessage = onMessage;

function onMessage(event) {
    alert(event.data);
    if(event.data === "login") {
        window.location.replace("main.jsp");
    }
    else if(event.data === "logout") {
        window.location.replace("index.jsp");
    }
}
function sendLogin(element) {   
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


