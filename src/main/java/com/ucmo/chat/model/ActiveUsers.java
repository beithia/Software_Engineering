package com.ucmo.chat.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A thread-safe collection of currently online user objects.
 * @author Jeff Trimmer
 */
public class ActiveUsers {
    private static final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    
    /**
     * Adds a user to the collection. 
     * @param user  - The user object.
     */
    public static void addUser(User user){
        users.put(user.getUsername(),user);
    }
    
    /**
     * Returns the user object corresponding to the userName parameter.
     * @param username - the user name corresponding to the user object.
     * @return - Returns the user object corresponding to the userName parameter.
     */
    public static User getUser(String username){
        return users.get(username);
    }
    
    /**
     * Returns a string array containing all the user names in alphabetic order.
     * @return - Returns  a string array containing the set of online user names in alphabetic order.
     */
    public static String[] getUsernames(){
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
    
    /**
     * Sends a text message to all the active users blocking until all of the message has been transmitted.
     * @param message - The message to send
     * @throws IOException - if there is a problem delivering the message.
     */
    public static void broadcast(String message) throws IOException{
        for (Enumeration<User> e = users.elements(); e.hasMoreElements();) {
            e.nextElement().sendMessage(message);
        }
    }
    
}
