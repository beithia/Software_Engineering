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
public class ChatRoom {
	private ArrayList<String> users = new ArrayList<String>();
	private ArrayList<String> messages = new ArrayList<String>();
	private int chatRoomID;

	public ChatRoom() {

	}

	public void addUser(String userName) {
		users.add(userName);
	}

	public void removeUser(String userName) {
		users.remove(userName);
	}

	public void addMessage(String message) {
		messages.add(message);
	}

	public String getLastMessage(){
		int count = messages.size();
                return messages.get(count-1);
	}

	public String getMessage(int index) {
		return messages.get(index);
	}

//	public ArrayList<String> getMessages(int start, int finish) {
//		ArrayList<String> array = new ArrayList();
//		for(int i = start; int i <= finish; i++){
//			array.add(messages.get(i));
//		}
//                return array;
//	}

	public void sendMessage(User to, User from, String message) {
		//empty
	}

	public void setChatRoomID(int ID) {
		chatRoomID = ID;
	}

	public int getChatRoomID() {
		return chatRoomID;
	}

	public int messageCount() {
		return messages.size();
	}


}

