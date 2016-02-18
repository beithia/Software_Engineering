package com.ucmo.chat.model;

import java.util.Objects;
import java.util.Timer;
import javax.websocket.Session;

/**
 * An object representing the user.
 * 
 * @author jtrimmer
 */
public class User implements java.io.Serializable, Comparable{
    
    private String userName;
    private String IPAddress;
    private Session session;
    private Timer timer;
    
    public User(){}
    
    public User(String userName, String IPAddress){
        this.userName = userName;        
        this.IPAddress = IPAddress;
    }
    
    public User(String userName, Session session){
        this.userName = userName;        
        this.session = session;
        //this.timer.schedule(null, delay);
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }    

    /**
     * @return the IPAddress
     */
    public String getIPAddress() {
        return IPAddress;
    }

    /**
     * @param IPAddress the IPAddress to set
     */
    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    @Override
    public int compareTo(Object anotherUser) {
        if (!(anotherUser instanceof User)) throw new ClassCastException("A User object expected.");
    String anotherPersonAge = ((User) anotherUser).getUserName();  
    return this.getUserName().compareTo(anotherPersonAge);
    }
    
    @Override
    public boolean equals(Object o){
                
        if (o == null) {
        return false;
    }
    if (getClass() != o.getClass()) {
        return false;
    }
    final User other = (User) o;
    if ((this.userName == null) ? (other.userName != null) : !this.userName.equals(other.userName)) {
        return false;
    }
    
    return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.userName);
        return hash;
    }

    /**
     * @return the session
     */
    public Session getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * @param timer the timer to set
     */
    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
