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
            System.out.println(message);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonMessage jsonMessage = objectMapper.readValue(message, JsonMessage.class);
            switch (jsonMessage.getAction()) {
                case "addChatUser":
                    {
                        String username = jsonMessage.getData()[0];
                        String id = jsonMessage.getData()[1];
                        User user = ActiveUsers.getUser(username);
                        ChatRoom chatRoom = ActiveChatRooms.getChatRoom(id);
                        JsonChatRoom jsonChatRoom = new JsonChatRoom(
                            "newChat",
                            chatRoom.getChatRoomID(),
                            chatRoom.getUsernames(),
                            chatRoom.getMessages()
                        );
                        String strSend = objectMapper.writeValueAsString(jsonChatRoom);
                        user.sendMessage(strSend);                        
                        chatRoom.addUser(user);
                        String[] data = {username, id};
                        JsonMessage send = new JsonMessage(
                                "addChatUser",
                                data
                        );
                        strSend =  objectMapper.writeValueAsString(send);
                        chatRoom.sendMessage(strSend);
                    }
                case "heartbeat":
                    {
                        String username = jsonMessage.getData()[0];
                        if (ActiveUsers.containsUser(username)){                            
                            ActiveUsers.getUser(username).resetTimer();
                        } else {
                            login(username, session);
                        }
                        break;
                    }
                case "login":
                    {
                        String username = jsonMessage.getData()[0];
                        login(username, session);
                        break;
                    }
                case "logout":
                    {
                        String username = jsonMessage.getData()[0];
                        String[] IDs = ActiveChatRooms.removeUser(username);
                        for (int i=0; i < IDs.length; i++){                            
                            removeChatUser(username, IDs[i]);
                        }                        
                        ActiveUsers.getUser(username).cancelTimer();
                        ActiveUsers.removeUser(username);
                        JsonMessage send = new JsonMessage(
                                "usernames", 
                                ActiveUsers.getUsernames()
                        );
                        String strSend = objectMapper.writeValueAsString(send);
                        ActiveUsers.broadcast(strSend);
                        break;
                    }
                case "newChat":
                    {
                        String username1 = jsonMessage.getData()[0];
                        String username2 = jsonMessage.getData()[1];
                        ChatRoom chatRoom = new ChatRoom(
                            ActiveUsers.getUser(username1),
                            ActiveUsers.getUser(username2)
                        );
                        ActiveChatRooms.addChatRoom(chatRoom.getChatRoomID(), chatRoom);
                        JsonChatRoom jsonChatRoom = new JsonChatRoom(
                            "newChat",
                            chatRoom.getChatRoomID(),
                            chatRoom.getUsernames(),
                            chatRoom.getMessages()
                        );
                        String strSend = objectMapper.writeValueAsString(jsonChatRoom);
                        chatRoom.sendMessage(strSend);
                        break;
                    }
                case "removeChatUser":
                    {
                        String username = jsonMessage.getData()[0];
                        String id = jsonMessage.getData()[1];
                        removeChatUser(username, id);
                        break;
                    }
                case "sendMessage":
                    {
                        String username = jsonMessage.getData()[0];
                        String id = jsonMessage.getData()[1];
                        String textMessage = jsonMessage.getData()[2];
                        ChatRoom chatRoom = ActiveChatRooms.getChatRoom(id);
                        chatRoom.addMessage(username + ": " + textMessage);
                        chatRoom.sendMessage(message);
                        break;
                    }
                default:
                    Logger.getLogger(Controller.class.getName()).log(
                        Level.WARNING, 
                        "Received an unknown message action: {0}", 
                        jsonMessage.getAction()
                    );
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);         
        }
    }
    
    /**
     * Creates a new user, adds them to the collection of online users, and updates all clients.
     * @param username - the user to login
     * @param session - the user's session
     * @throws IOException
     */
    private void login(String username, Session session) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        ActiveUsers.addUser(new User(username, session));
        JsonMessage send = new JsonMessage(
                "usernames", 
                ActiveUsers.getUsernames()
        );
        String strSend = objectMapper.writeValueAsString(send);
        ActiveUsers.broadcast(strSend);
    }
    /**
     * Removes the given user from the chat room with the given id
     * @param username - the user to remove
     * @param id - the id of the chat room
     * @throws IOException
     */
    private void removeChatUser(String username, String id) throws IOException{
        
        ObjectMapper objectMapper = new ObjectMapper();
        ChatRoom chatRoom = ActiveChatRooms.getChatRoom(id);
        chatRoom.removeUser(username);
        if (chatRoom.isEmpty()){
            ActiveChatRooms.removeChatRoom(id);
            return;
        }
        String[] data = {username, id};
        JsonMessage send = new JsonMessage(
                "removeChatUser",
                data
        );
        String strSend =  objectMapper.writeValueAsString(send);
        chatRoom.sendMessage(strSend);
    }
}
