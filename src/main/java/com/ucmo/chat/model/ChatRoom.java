package com.ucmo.chat.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoom {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap();
    private ArrayList<String> messages = new ArrayList();
    private UUID chatRoomID;

    public ChatRoom() {}

    public ChatRoom(User user1, User user2) {
        this.chatRoomID = UUID.randomUUID();
        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }
    /**
     * Removes the user from the chat room
     * @param userName - the user to remove.
     */
    public void removeUser(String userName) {
        users.remove(userName);
    }

    /**
    * Returns a string array containing all the member usernames in alphabetic order.
    * @return - Returns  a string array containing the set of online user names in alphabetic order.
    */
   public String[] getUsernames(){
       Set keys = users.keySet();
       String[] array = (String[]) keys.toArray(new String[keys.size()]);
       Arrays.sort(array);
       return array;
   }

    public void addMessage(String message) {
        messages.add(message);
    }

    public String getLastMessage(){
        int count = messages.size();
        return messages.get(count-1);
    }

    public String getMessage(int index) {
        return messages.get(index);
    }

    public String[] getMessages() {
        String[] array = messages.toArray(new String[0]);
        return array;
    }
    /**
     * Sends a text message to all the active users blocking until all of the message has been transmitted.
     * @param message - The message to send
     * @throws IOException - if there is a problem delivering the message.
     */
    public void sendMessage(String message) throws IOException{
        for (Enumeration<User> e = users.elements(); e.hasMoreElements();) {
            e.nextElement().getSession().getBasicRemote().sendText(message);
        }
    }

    public int messageCount() {
        return messages.size();
    }

    /**
     * @return the chatRoomID
     */
    public String getChatRoomID() {
        return chatRoomID.toString();
    }
}

