package com.ucmo.chat.beans;

import java.io.Serializable;

/**
 * A JsonChatRoom represents the serialized form of the outgoing chat room to be bundled and sent to the end users.
 * @author Jeff Trimmer
 */
public class JsonChatRoom implements Serializable {
    private String action;
    private String ID;
    private String[] users;
    private String[] messages;
    
    public JsonChatRoom(){}
    
    public JsonChatRoom(String action, String ID, String[] users, String[] messages){
        this.action = action;
        this.ID = ID;
        this.users = users;
        this.messages = messages;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the users
     */
    public String[] getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(String[] users) {
        this.users = users;
    }

    /**
     * @return the messages
     */
    public String[] getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(String[] messages) {
        this.messages = messages;
    }
}
