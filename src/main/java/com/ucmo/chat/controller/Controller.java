package com.ucmo.chat.controller;

import com.ucmo.chat.beans.JsonMessage;
import com.ucmo.chat.model.ActiveUsers;
import com.ucmo.chat.model.User;
import java.io.IOException;
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
            
            JsonMessage jsonMessage = new ObjectMapper().readValue(message, JsonMessage.class);
            if (jsonMessage.getAction().equals("login")) {
                String username = jsonMessage.getData()[0];
                ActiveUsers.addUser(new User(username, session));
                JsonMessage send = new JsonMessage("usernames", ActiveUsers.getUserNames());
                ObjectMapper objectMapper = new ObjectMapper();
                String strSend = objectMapper.writeValueAsString(send);
                System.out.println(send);
                try {
                    session.getBasicRemote().sendText(strSend);
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Failed to send login reply", ex);
                }
            }
            if (jsonMessage.getAction().equals("logout")) {
                String username = (String)jsonMessage.getData()[0];
                ActiveUsers.removeUser(username);
                try {
                    session.getBasicRemote().sendText("logout");
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Failed to send logout reply", ex);
                }
            }
            
            //Test case for heartbeat. 
            if (jsonMessage.getAction().equals("heartbeat")) {
                String[] testMsg = {(String)jsonMessage.getData()[0]};
                JsonMessage send = new JsonMessage("heartbeat", testMsg);
                ObjectMapper objectMapper = new ObjectMapper();
                String strSend = objectMapper.writeValueAsString(send);
                try {
                    session.getBasicRemote().sendText(strSend);
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
