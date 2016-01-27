package com.ucmo.chat;

import java.io.IOException;
import java.util.concurrent.ConcurrentSkipListMap;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author jtrimmer
 * 
 * A thread-safe collection of online user objects identified uniquely by the username. 
 */
public class ActiveUsers {
    private static final ConcurrentSkipListMap<String, User> users = new ConcurrentSkipListMap<>();
    
    /**
     * Adds a user to the collection
     * 
     * @param userName - The unique identifier of the user. This is used when logging into the system.
     * @param user  - The user object
     */
    public static void addUser(String userName, User user){
        users.put(userName, user);
    }
    
    /**
     * Returns the user object corresponding to the userName parameter.
     * 
     * @param userName - The unique identifier of the user. This is used when logging into the system.
     * @return - Returns the user object corresponding to the userName parameter.
     */
    public static Object getUser(String userName){
        return users.get(userName);
    }
    /**
     * Removes the user object corresponding to the userName parameter from the collection
     * 
     * @param userName - The unique identifier of the user. This is used when logging into the system.
     */
    public static void removeUser(String userName){
        users.remove(userName);
    }
    
    /**
     * Returns the collection of online users in JSON form.
     * @return - The collection of online users in JSON form.
     * @throws IOException 
     */
    public static String getJsonString() throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(users);
    }
    
}
