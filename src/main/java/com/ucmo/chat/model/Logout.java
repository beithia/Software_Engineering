/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucmo.chat.model;

import com.ucmo.chat.beans.JsonMessage;
import java.io.IOException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Logs the user out automatically when the timer runs out.
 * @author Jeff Trimmer
 */
public class Logout extends TimerTask{
    
    private final String username;

    public Logout(String username){
        this.username = username;
    }
    
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {        
        ActiveUsers.removeUser(username);
        System.out.println(username + " Logged out");
        JsonMessage send = new JsonMessage("usernames", ActiveUsers.getUsernames());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String strSend = objectMapper.writeValueAsString(send);
            ActiveUsers.broadcast(strSend);
        } catch (IOException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
        }        
        //Remove user from all active chat rooms
        ActiveChatRooms.removeUser(username);
    }
    
}
