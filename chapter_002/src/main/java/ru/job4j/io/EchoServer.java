package ru.job4j.io;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final String HELLO = "Hello";
    private static final String EXIT = "Exit";
    private static final String ANY = "Any";

    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    boolean isBreak = false;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("?msg=")) {
                            break;
                        }
                    }
                    if (isBreak) {
                        break;
                    }
                    String outMessage = "";
                    if (str.contains(HELLO)) {
                        outMessage = HELLO;
                    } else if (str.contains(ANY)) {
                        outMessage = ANY;
                    } else if (str.contains(EXIT)) {
                        break;
                    } else {
                        outMessage = "Hello, dear friend.";
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(outMessage.concat("\r\n\r\n").getBytes());
                }
            }
        }
    }
}
