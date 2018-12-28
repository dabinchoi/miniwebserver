package my.examples.miniwebserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private int port;

    public WebServer(int port) {
        this.port = port;
    }

    public void run() {
        //접속을 대기하고 있다.
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                System.out.println("접속을 대기합니다.");
                Socket socket = serverSocket.accept();//클라이언트가 접속할때까지 대기
                //System.out.println(socket.toString());
                HttpHandler httpHandler = new HttpHandler(socket);
                httpHandler.start();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();

        } finally {
            try {
                serverSocket.close();
            } catch (Exception ignore) {
            }
        }

    }
}

class HttpHandler extends Thread {


    private Socket socket;

    public HttpHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() { //별도로 동작할 코드는 run메소드에서 작성한다.
        final String baseDir = "C:\\Users\\dabin\\Desktop\\me\\WebContent";

        BufferedReader in = null;
        PrintStream out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());


            String requestLine = in.readLine();
            String[] s = requestLine.split("");



            String line = null;
            while ((line = in.readLine()) != null) {//빈줄까지 읽어들인다.
                if ("".equals(line)) {
                    break;
                }
                System.out.println("헤더정보 :" + line);
            }






            out.println("HTTP/1.1 200 OK");
            out.println("content-type : text/html; charset=UTF-8");
            out.println();
            out.println("<html><h1>HELLO</h1></html>");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
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
    }
}
//누군가 접속을 하면, 서버는 한줄씩 읽는다.(반줄나올떄까지)
//path에 해당하는 리소스를 찾는다.
//리소스가 있을 경우, 상태코드를 보낸다.(첫줄)
//헤더들을 보낸다.(body의 길이,body가 무슨 mime type)
//빈줄을 보낸다.
//body를 보낸다.