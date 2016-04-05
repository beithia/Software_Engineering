package com.ucmo.chat.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
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
     * @return - the array of chat room id's from which the user was removed
     */
    public static String[] removeUser(String username){
        List<String> IDs = new ArrayList<>();
        for (Enumeration<ChatRoom> e = chatRooms.elements(); e.hasMoreElements();){
            ChatRoom chatRoom = e.nextElement();
            if (chatRoom.containsUser(username)){
                chatRoom.removeUser(username);
                IDs.add(chatRoom.getChatRoomID());
            }
        }
        return IDs.toArray(new String[IDs.size()]);
    }
}
