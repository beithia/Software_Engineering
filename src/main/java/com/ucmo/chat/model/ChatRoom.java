package com.ucmo.chat.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoom {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap();
    private ArrayList<String> messages = new ArrayList();
    private int chatRoomID;

    public ChatRoom() {}

    public ChatRoom(int chatRoomID, User user1, User user2) {
        this.chatRoomID = chatRoomID;
        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

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

    public void sendMessage(String message) throws IOException{
        for (Enumeration<User> e = users.elements(); e.hasMoreElements();) {
            e.nextElement().getSession().getBasicRemote().sendText(message);
        }
    }

    public void setChatRoomID(int ID) {
        chatRoomID = ID;
    }

    public int getChatRoomID() {
        return chatRoomID;
    }

    public int messageCount() {
        return messages.size();
    }
}

