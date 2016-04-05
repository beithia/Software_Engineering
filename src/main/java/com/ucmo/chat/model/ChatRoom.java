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

    public ChatRoom() {
    }

    public ChatRoom(User user1, User user2) {
        this.chatRoomID = UUID.randomUUID();
        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);
    }

    /**
     * Adds the user to the chat room
     *
     * @param user - the user to be added
     */
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    /**
     * Removes the user from the chat room
     *
     * @param userName - the user to remove.
     */
    public void removeUser(String userName) {
        users.remove(userName);
    }

    /**
     * Returns true if the user is a member of the chat room, otherwise returns
     * false
     *
     * @param userName - the user to check
     * @return - true if the user is a member of the chat room, otherwise
     * returns false
     */
    public boolean containsUser(String userName) {
        return users.containsKey(userName);
    }

    /**
     * Returns a string array containing all the member usernames in alphabetic
     * order.
     *
     * @return - Returns a string array containing the set of online user names
     * in alphabetic order.
     */
    public String[] getUsernames() {
        Set keys = users.keySet();
        String[] array = (String[]) keys.toArray(new String[keys.size()]);
        Arrays.sort(array);
        return array;
    }
    
    /**
     * Adds the message text to the messages list
     * 
     * @param message - the message to add
     */
    public void addMessage(String message) {
        messages.add(message);
    }
    
    /**
     * Returns the last message in the message list
     * 
     * @return - the last message in the message list
     */
    public String getLastMessage() {
        int count = messages.size();
        return messages.get(count - 1);
    }
    
    /**
     * Returns whether the chat room has any users
     * @return - true if the chat room contains users, false otherwise
     */
    public boolean isEmpty(){
        return users.isEmpty();
    }
    
    public String getMessage(int index) {
        return messages.get(index);
    }

    public String[] getMessages() {
        String[] array = messages.toArray(new String[0]);
        return array;
    }

    /**
     * Sends a text message to all the active users blocking until all of the
     * message has been transmitted.
     *
     * @param message - The message to send
     * @throws IOException - if there is a problem delivering the message.
     */
    public void sendMessage(String message) throws IOException {
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
