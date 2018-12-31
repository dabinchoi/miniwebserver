package my.examples.miniwebserver;

import java.net.Socket;
import java.util.List;

public class ChatServerHandler extends Thread {
    private Socket socket;
    private ChatHouse chatHouse;
    private boolean inRoom;

    public ChatServerHandler(Socket socket, ChatHouse chatHouse) {
        this.socket = socket;
        this.chatHouse = chatHouse;
        inRoom = false;
    }

    @Override
    public void run() {
        ChatUser chatUser = new ChatUser(socket);
        String nickname = chatUser.read();
        chatUser.setNickname(nickname);
        System.out.println(chatUser);

        chatHouse.addChatUser(chatUser);

        try {
            while (true) {
                String message = chatUser.read();
                System.out.println("message :" + message);
                if (!inRoom) { //로비에 있을 결우
                    if (message.indexOf("/create") == 0) {
                        String title = message.substring(message.indexOf(" ") + 1);
                        chatHouse.createRoom(chatUser, title);
                        inRoom = true;
                    } else if (message.indexOf("/list") == 0) {
                        List<ChatRoom> chatRooms = chatHouse.getChatRooms();
                        int i = 0;
                        for (ChatRoom cr : chatRooms) {
                            chatUser.write(i + " : " + cr.getTitle());
                            i++;
                        }
                    } else if (message.indexOf("/quit") == 0) {

                    } else if (message.indexOf("/join") == 0) {
                        String strRoomNum = message.substring(message.indexOf(" ") + 1);
                        int roomNum = Integer.parseInt(strRoomNum);
                        chatHouse.joinRoom(roomNum, chatUser);
                        inRoom = true;
                    }
                } else {
                    List<ChatUser> chatUsers = chatHouse.getUser(chatUser);
                    for (ChatUser cu : chatUsers) {
                        cu.write(chatUser.getNickname() + " : " + message);
                    }
                }

            }
        } catch (Exception ex) {
            chatHouse.exit(chatUser);
        }

    }
}