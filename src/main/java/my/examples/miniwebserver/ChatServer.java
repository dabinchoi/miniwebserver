package my.examples.miniwebserver;

import java.net.ServerSocket;

public class ChatServer {
    private int port;
    private ChatHouse chatHouse;

    public ChatServer(int port){
        this.port = port;
        chatHouse = new ChatHouse();
    }
    public void run(){
        ServerSocket serverSocket = null;
        while(true){

        }
    }
}
