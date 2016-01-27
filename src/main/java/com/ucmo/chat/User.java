package com.ucmo.chat;

/**
 * An object representing the user.
 * 
 * @author jtrimmer
 */
public class User implements java.io.Serializable{
    
    private String userName;
    private String IPAddress;
    private String hostName;
    
    public User(){}
    
    public User(String userName, String IPAddress, String hostName){
        this.userName = userName;        
        this.IPAddress = IPAddress;
        this.hostName = hostName;
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

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
