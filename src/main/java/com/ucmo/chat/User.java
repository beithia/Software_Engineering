package com.ucmo.chat;

/**
 * An object representing the user.
 * 
 * @author jtrimmer
 */
public class User implements java.io.Serializable{
    
    private String userName;
    
    public User(){}
    
    public User(String userName){
        this.userName = userName;
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
}
