package com.ucmo.chat.model;

import java.util.Objects;
import java.util.Timer;
import javax.websocket.Session;

/**
 * An object representing the user. 
 * @author Jeff Trimmer
 */
public class User implements java.io.Serializable, Comparable{
    private final long delay = 5000;
    private String username;
    private Session session;
    private Timer timer;
    
    public User(){}
    
    public User(String userName, Session session){
        this.username = userName;        
        this.session = session;
        this.timer = new Timer();
        timer.schedule(new Logout(userName), delay);
    }

    /**
     * Returns the user username
     * @return the userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user username
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the user session
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    /**
     * Sets the user session
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Resets the timer delay to 5 seconds.
     */
    public void resetTimer() {
        timer.cancel();
        timer.purge();
        this.timer = new Timer();
        timer.schedule(new Logout(username), delay);
    }

    /**
     * Compares this object with the specified object for order. Returns a 
     * negative integer, zero, or a positive integer as this object is less 
     * than, equal to, or greater than the specified object.
     * @param anotherUser - the object to be compared.
     * @return - a negative integer, zero, or a positive integer as this object 
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object anotherUser) {
        if (!(anotherUser instanceof User)) throw new ClassCastException("A User object expected.");
    String anotherPersonAge = ((User) anotherUser).getUsername();  
    return this.getUsername().compareTo(anotherPersonAge);
    }
    
    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o - the reference object with which to compare.
     * @return - true if this object is the same as the o argument; false otherwise.
     */
    @Override
    public boolean equals(Object o){
                
        if (o == null) {
        return false;
    }
    if (getClass() != o.getClass()) {
        return false;
    }
    final User other = (User) o;
    return !((this.username == null) ? (other.username != null) : !this.username.equals(other.username));
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap.
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.username);
        return hash;
    }
}
