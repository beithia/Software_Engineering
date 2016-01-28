package com.ucmo.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author jtrimmer
 * 
 * A thread-safe collection of currently online user objects. 
 */
public class ActiveUsers {
    private static final List users = Collections.synchronizedList(new ArrayList<User>());
    
    /**
     * Adds a user to the collection.
     * 
     * @param user  - The user object.
     */
    public static void addUser(User user){
        users.add(user);
    }
    
    /**
     * Returns the user object corresponding to the userName parameter.
     * 
     * @param index - the specified position in this list.
     * @return - Returns the user object corresponding to the userName parameter.
     */
    public static User getUser(int index){
        return (User) users.get(index);
    }
    /**
     * Removes the user object corresponding to the userName parameter from the collection
     * 
     * @param index - the specified position in this list.
     */
    public static void removeUser(int index){
        users.remove(index);
    }
    
    /**
     * Removes the user object corresponding to the userName parameter from the collection
     * 
     * @param user - the User object.
     */
    public static void removeUser(User user){
        users.remove(user);
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
