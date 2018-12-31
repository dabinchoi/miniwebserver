package my.examples.miniwebserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ChatUser {
    private String nickname;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ChatUser(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception ex) {

        }
    }

    public void close() {
        try {
            in.close();
        } catch (Exception ignore) {
        }
        try {
            out.close();
        } catch (Exception ignore) {
        }
        try {
            socket.close();
        } catch (Exception ignore) {
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public void write(String msg) {
        try {
            out.writeUTF(msg);
        } catch (Exception ex) {
            throw new RuntimeException("메시지 전송시 오류");
        }
    }

    public String read() {
        try {
            return in.readUTF();
        } catch (Exception ex) {
            throw new RuntimeException("메시지 읽을때  오류");
        }
    }
}
