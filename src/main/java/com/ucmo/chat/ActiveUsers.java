package com.ucmo.chat;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * A thread-safe collection of currently online user objects. 
 * @author jtrimmer
 */
public class ActiveUsers {
    private static final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    
    /**
     * Adds a user to the collection. 
     * @param user  - The user object.
     */
    public static void addUser(User user){
        users.put(user.getUserName(),user);
    }
    
    /**
     * Returns the user object corresponding to the userName parameter.
     * @param userName - the user name corresponding to the user object.
     * @return - Returns the user object corresponding to the userName parameter.
     */
    public static User getUser(String userName){
        return users.get(userName);
    }
    
    /**
     * Returns a string array containing all the user names in alphabetic order.
     * @return - Returns  a string array containing the set of online user names in alphabetic order.
     */
    public static String[] getUserNames(){
        Set keys = users.keySet();
        String[] keyArray = (String[]) keys.toArray(new String[keys.size()]);
        Arrays.sort(keyArray);
        return keyArray;
    }
    
    /**
     * Removes the user object corresponding to the supplied user name.
     * @param userName - the user name corresponding to the user object.
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

    /**
     * Returns an integer value of the collection element count.
     * @return the count of user objects in the collection
     */
    public static int size() {
        return users.size();
    }
    
    /**
     * Returns a boolean stating whether or not a user object corresponding to 
     * the submitted username is present in the collection.
     * @param userName - the user name corresponding to the user object.
     * @return - Returns whether a user is present.
     */
    public static boolean containsUser(String userName){
        return users.containsKey(userName);
    }
    
}
