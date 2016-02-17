package com.ucmo.chat;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Controls and manages incoming and outgoing messages sent between all clients and the server. 
 * @author jtrimmer
 */
@ServerEndpoint("/controller")
public class Controller {
    
    @OnOpen
    public void open(Session session) {
        System.out.println("Session " + session.getId() + " opened");
        Logger.getLogger(Controller.class.getName()).log(Level.INFO, "Session {0} opened", session.getId());
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Session " + session.getId() + " closed");
        Logger.getLogger(Controller.class.getName()).log(Level.INFO, "Session {0} closed", session.getId());
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("Error: " + error.getMessage());
        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Error: {0}", error.getMessage());
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("Message received");        
        try (JsonReader reader = Json.createReader(new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            if ("login".equals(jsonMessage.getString("action"))) {
                String username = jsonMessage.getString("username");
                ActiveUsers.addUser(new User(username, session));
                try {
                    session.getBasicRemote().sendText("login reply");
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Failed to send login reply", ex);
                }
            }

            if ("logout".equals(jsonMessage.getString("action"))) {
                String username = jsonMessage.getString("username");
                ActiveUsers.removeUser(username);
                try {
                    session.getBasicRemote().sendText("logout reply");
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Failed to send logout reply", ex);
                }
            }
        }
    }
}
