package com.ucmo.chat.model;

import java.util.concurrent.ConcurrentHashMap;

public class ActiveChatRooms {
    private static final ConcurrentHashMap<Integer, ChatRoom> chatRooms = new ConcurrentHashMap();
    
    public static void addChatRoom(int id, ChatRoom chatRoom) {
        chatRooms.put(id, chatRoom);
    }

    public static void removeChatRoom(int ID) {
        chatRooms.remove(ID);
    }

    public static ChatRoom getChatRoom(int ID) {
        return chatRooms.get(ID);
    }

    public static int nextID(){
        int nextID = 0;
        while (!chatRooms.containsKey(nextID)){
            nextID++;
        }
        return nextID;
    }
}
