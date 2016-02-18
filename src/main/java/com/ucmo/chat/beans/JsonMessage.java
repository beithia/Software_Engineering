package com.ucmo.chat.beans;

import java.io.Serializable;

public class JsonMessage implements Serializable{
    private String action;
    private String[] data;
    
    public JsonMessage(){}
    
    public JsonMessage(String action){
        this.action = action;
    }
    
    public JsonMessage(String action, String[] data){
        this.action = action;
        this.data = data;
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
     * @return the data
     */
    public String[] getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String[] data) {
        this.data = data;
    }
}
