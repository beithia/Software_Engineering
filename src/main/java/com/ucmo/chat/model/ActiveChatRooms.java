package com.ucmo.chat.model;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class ActiveChatRooms {
    private static final ConcurrentHashMap<String, ChatRoom> chatRooms = new ConcurrentHashMap();
    
    public static void addChatRoom(String id, ChatRoom chatRoom) {
        chatRooms.put(id, chatRoom);
    }

    public static void removeChatRoom(String ID) {
        chatRooms.remove(ID);
    }

    public static ChatRoom getChatRoom(String ID) {
        return chatRooms.get(ID);
    }
    /**
     * Removes the user from all active chat rooms.
     * @param username - the user to remove
     */
    public static void removeUser(String username){
        for (Enumeration<ChatRoom> e = chatRooms.elements(); e.hasMoreElements();){
            e.nextElement().removeUser(username);
        }
    }
}
