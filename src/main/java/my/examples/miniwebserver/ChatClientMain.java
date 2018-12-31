package my.examples.miniwebserver;

public class ChatClientMain {
    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient("127.0.0.1", 9999);
        chatClient.run();
    }
}
