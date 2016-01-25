/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucmo.chat;

/**
 *
 * @author jtrimmer
 */
public class Test {
    
    public static void main(String[] args) throws Exception{
        ActiveUsers.addUser("Brett", "Carty");
        ActiveUsers.addUser("Jeff", "Trimmer");
        ActiveUsers.addUser("John", "Brown");
        ActiveUsers.addUser("Bob", "Smith");
        ActiveUsers.addUser("Inglebert", "Humperdink");
        
        System.out.println(ActiveUsers.getJsonString());
    }
}
