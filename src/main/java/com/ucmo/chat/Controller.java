package com.ucmo.chat;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import org.codehaus.jackson.map.ObjectMapper;

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
        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Error: {0}", error.getLocalizedMessage());
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        try {
            System.out.println("Message received:" + message);
            HashMap<String,Object> jsonMessage = new ObjectMapper().readValue(message, HashMap.class);
            System.out.println("try login");
            if (((String)jsonMessage.get("action")).equals("login")) {
                String username = (String)jsonMessage.get("username");
                ActiveUsers.addUser(new User(username, session));
                try {
                    session.getBasicRemote().sendText("{\"message\":\"login reply\"}");
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Failed to send login reply", ex);
                }
            }
            System.out.println("try logout");
            if (((String)jsonMessage.get("action")).equals("logout")) {
                String username = (String)jsonMessage.get("username");
                ActiveUsers.removeUser(username);
                try {
                    session.getBasicRemote().sendText("logout reply");
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Failed to send logout reply", ex);
                }
            }
        } catch (IOException ex) {
            try {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                session.getBasicRemote().sendText(ex.getMessage());
            } catch (IOException ex1) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Could not send message", ex1);
            }
        }
    }
}
