/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucmo.chat.model;
import java.util.ArrayList;
/**
 *
 * @author Owner
 */
public class ActiveChatRooms {
	private ArrayList<ChatRoom> chatRooms = new ArrayList();
	
	public ActiveChatRooms() {
	
	}
	
	public void addChatRoom(ChatRoom chatRoom) {
		chatRooms.add(chatRoom);
	}
	
	public void removeChatRoom(int ID) {
                //I don't know if this is going to work properly
                //Double check parameters during testing
		chatRooms.remove(ID);
	}
	
	public ChatRoom getChatRoom(int ID) {
		int count = chatRooms.size();
		return chatRooms.get(count-1);
	}
}
