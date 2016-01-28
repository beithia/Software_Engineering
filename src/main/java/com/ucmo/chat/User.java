package com.ucmo.chat;

/**
 * An object representing the user.
 * 
 * @author jtrimmer
 */
public class User implements java.io.Serializable, Comparable{
    
    private String userName;
    private String IPAddress;
    
    public User(){}
    
    public User(String userName, String IPAddress){
        this.userName = userName;        
        this.IPAddress = IPAddress;
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
}
