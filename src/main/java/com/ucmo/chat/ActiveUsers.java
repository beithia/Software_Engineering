/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucmo.chat;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author jtrimmer
 */
public class ActiveUsers {
    private static final ConcurrentHashMap<String, Object> users = new ConcurrentHashMap<>();
    
    public static void addUser(String userName, Object user){
        users.put(userName, user);
    }
    
    public static Object getUser(String userName){
        return users.get(userName);
    }
    
    public static void removeUser(String userName){
        users.remove(userName);
    }
    
    public static String getJsonString() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(users.elements());
    }
    
}
