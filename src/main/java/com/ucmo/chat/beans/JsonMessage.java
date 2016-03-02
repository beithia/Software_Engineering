package com.ucmo.chat.beans;

import java.io.Serializable;

/**
 * A JsonMessage represents the object form of the incoming JSON String message. 
 * The incoming JSON string is parsed and values are populated to the instance 
 * variables when this class is instantiated.
 * @author jtrimmer
 */
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
