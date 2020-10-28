package ru.job4j.io;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
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
                        if (str.contains("?msg=Bye")) {
                            isBreak = true;
                            break;
                        }
                    }
                    if (isBreak) {
                        break;
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}
