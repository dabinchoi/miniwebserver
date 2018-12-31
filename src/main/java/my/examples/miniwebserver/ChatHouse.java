package my.examples.miniwebserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatHouse {
    List<ChatUser> lobby;
    List<ChatRoom> chatRooms;

    public ChatHouse(){
        lobby = Collections.synchronizedList(new ArrayList<>());
        chatRooms = Collections.synchronizedList(new ArrayList<>());
    }

    public void createRoom(ChatUser chatUser, String title){
        ChatRoom chatRoom = new ChatRoom(chatUser, title);
        chatRooms.add(chatRoom);
    }

    // ChatUser를 추가
    public void addChatUser(ChatUser chatUser){
        lobby.add(chatUser);
    }

    // exit
    public void exit(ChatUser chatUser){
        lobby.remove(chatUser);
    }

    public void printLobby(){
        for(ChatUser chatUser : lobby){
            System.out.println(chatUser.getNickname());
        }
    }

    public List<ChatUser> getUser(ChatUser chatUser) {
        for(ChatRoom cr : chatRooms){
            if(cr.existsUser(chatUser)){
                return cr.getChatUsers();
            }
        }
        return new ArrayList<ChatUser>();
    }

    public List<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public void joinRoom(int roomNum, ChatUser chatUser) {
        ChatRoom chatRoom = chatRooms.get(roomNum);
        chatRoom.addChatUser(chatUser);
    }
}