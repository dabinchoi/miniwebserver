package my.examples.miniwebserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {
    private String ip;
    private int port;


    public ChatClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        Socket socket = null;
        ChatUser chatUser = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(ip, port); //서버에 접속
            chatUser = new ChatUser(socket);

            System.out.println("닉네임을 입력하시오");
            String nickName = br.readLine();
            chatUser.setNickname(nickName);
            chatUser.write(nickName);

            ChatClientHandler chatClientHandler = new ChatClientHandler(chatUser);
            chatClientHandler.start();

            while (true) {
                String line = br.readLine();
                chatUser.write(line);
            }
        } catch (Exception ex) {
            System.out.println("연결 끊어짐");
        } finally {
            try {
                chatUser.close();
            } catch (Exception ignore) {
            }
        }
    }
}