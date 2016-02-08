/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucmo.chat;

import java.util.Arrays;

/**
 *
 * @author jtrimmer
 */
public class Test {
    
    public static void main(String[] args) throws Exception{
        
        ActiveUsers.addUser(new User("Vicky", "178.158.21.10"));
        ActiveUsers.addUser(new User("Jeff", "192.168.1.1"));
        ActiveUsers.addUser(new User("Mauricio", "73.217.236.199"));
        ActiveUsers.addUser(new User("Victoria", "178.158.21.10"));
        
        System.out.println(ActiveUsers.containsUser("Jeff"));
        System.out.println(ActiveUsers.containsUser("Bob"));
        System.out.println(ActiveUsers.containsUser("Victoria"));
        System.out.println(ActiveUsers.containsUser("Mauric"));
        System.out.println(ActiveUsers.containsUser(""));
        System.out.println(ActiveUsers.containsUser("Vicky"));        
        System.out.println(Arrays.toString(ActiveUsers.getUserNames()));
        
        
        
    }
}
