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
        ActiveUsers.addUser("VictoriaW", new User("VictoriaW"));
        ActiveUsers.addUser("JeffT", new User("JeffT"));
        ActiveUsers.addUser("MauricioB", new User("MauricioB"));
        ActiveUsers.addUser("JessicaB", new User("JessicaB"));
        ActiveUsers.addUser("EngelbertHumperdinck", new User("EngelbertHumperdinck"));
        
        System.out.println(ActiveUsers.getJsonString());
    }
}
