package com.ucmo.chat.controller;

import com.ucmo.chat.beans.JsonChatRoom;
import com.ucmo.chat.beans.JsonMessage;
import com.ucmo.chat.model.ActiveChatRooms;
import com.ucmo.chat.model.ActiveUsers;
import com.ucmo.chat.model.ChatRoom;
import com.ucmo.chat.model.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Controls and manages incoming and outgoing messages sent between all clients and the server. 
 * @author Jeff Trimmer
 */
@ServerEndpoint("/controller")
public class Controller {
    
    /**
     * Notifies when a new webSocket has just begun.
     * @param session - the session that has just been activated.
     */
    @OnOpen
    public void open(Session session) {
        Logger.getLogger(Controller.class.getName()).log(Level.INFO, "Session {0} opened", session.getId());
    }

    /**
     * This method is called immediately prior to the session with the remote peer being closed. It is called whether the session is being closed because the remote peer initiated a close and sent a close frame, or whether the local webSocket container or this endpoint requests to close the session.
     * @param session - the session about to be closed.
     */
    @OnClose
    public void close(Session session) {
        Logger.getLogger(Controller.class.getName()).log(Level.INFO, "Session {0} closed", session.getId());
    }

    /**
     * Notifies when the web socket session creates some kind of error that is not modeled in the web socket protocol.
     * @param error - the Throwable representing the problem.
     */
    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, "Error: {0}", error.getLocalizedMessage());
    }

    /**
     * Used to select an appropriate method which is called once a new message is received.
     * @param message - the JSON message received.
     * @param session - the session from which the message is received.
     */
    @OnMessage
    public void handleMessage(String message, Session session) {
        try {
            
            JsonMessage jsonMessage = new ObjectMapper().readValue(message, JsonMessage.class);
            switch (jsonMessage.getAction()) {
                case "heartbeat":
                    {
                        String username = jsonMessage.getData()[0];
                        ActiveUsers.getUser(username).resetTimer();
                        break;
                    }
                case "login":
                    {
                        String username = jsonMessage.getData()[0];
                        ActiveUsers.addUser(new User(username, session));
                        JsonMessage send = new JsonMessage(
                                "usernames", 
                                ActiveUsers.getUsernames()
                        );
                        ObjectMapper objectMapper = new ObjectMapper();
                        String strSend = objectMapper.writeValueAsString(send);
                        ActiveUsers.broadcast(strSend);
                        break;
                    }
                case "logout":
                    {
                        String username = (String)jsonMessage.getData()[0];
                        ActiveUsers.removeUser(username);
                        JsonMessage send = new JsonMessage(
                                "usernames", 
                                ActiveUsers.getUsernames()
                        );
                        ObjectMapper objectMapper = new ObjectMapper();
                        String strSend = objectMapper.writeValueAsString(send);
                        ActiveUsers.broadcast(strSend);
                        break;
                    }
                case "newChat":
                    {
                        String username1 = jsonMessage.getData()[0];
                        String username2 = jsonMessage.getData()[1];
                        int newChatID = ActiveChatRooms.nextID();
                        ChatRoom chatRoom = new ChatRoom(
                            newChatID,
                            ActiveUsers.getUser(username1),
                            ActiveUsers.getUser(username2)
                        );
                        ActiveChatRooms.addChatRoom(chatRoom.getChatRoomID(), chatRoom);
                        JsonChatRoom jsonChatRoom = new JsonChatRoom(
                            "newChat",
                            newChatID,
                            chatRoom.getUsernames(),
                            chatRoom.getMessages()
                        );
                        ObjectMapper objectMapper = new ObjectMapper();
                        String strSend = objectMapper.writeValueAsString(jsonChatRoom);
                        chatRoom.sendMessage(strSend);
                        break;
                    }
            }
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);         
        }
    }
}
