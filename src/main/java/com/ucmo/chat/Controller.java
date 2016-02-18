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
        Logger.getLogger(Controller.class.getName()).log(Level.INFO, "Session {0} opened", session.getId());
    }

    @OnClose
    public void close(Session session) {
        Logger.getLogger(Controller.class.getName()).log(Level.INFO, "Session {0} closed", session.getId());
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Error: {0}", error.getLocalizedMessage());
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        try {
            HashMap<String,Object> jsonMessage = new ObjectMapper().readValue(message, HashMap.class);
            if (((String)jsonMessage.get("action")).equals("login")) {
                String username = (String)jsonMessage.get("username");
                ActiveUsers.addUser(new User(username, session));
                try {
                    session.getBasicRemote().sendText("login");
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Failed to send login reply", ex);
                }
            }
            if (((String)jsonMessage.get("action")).equals("logout")) {
                String username = (String)jsonMessage.get("username");
                ActiveUsers.removeUser(username);
                try {
                    session.getBasicRemote().sendText("logout");
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
