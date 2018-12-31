package my.examples.miniwebserver;

public class ChatClientHandler extends Thread{
    private ChatUser chatUser;
    public ChatClientHandler(ChatUser chatUser){
        this.chatUser = chatUser;
    }
    public void run(){
        while(true){
            while(true){
                String line = chatUser.read();
                System.out.println(line);
            }
        }
    }
}
