package com.ucmo.chat.model;

import java.util.concurrent.ConcurrentHashMap;

public class ActiveChatRooms {
    private static final ConcurrentHashMap<String, ChatRoom> chatRooms = new ConcurrentHashMap();
    
    public static void addChatRoom(String id, ChatRoom chatRoom) {
        chatRooms.put(id, chatRoom);
    }

    public static void removeChatRoom(int ID) {
        chatRooms.remove(ID);
    }

    public static ChatRoom getChatRoom(int ID) {
        return chatRooms.get(ID);
    }
}
