package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final String HELLO = "Hello";
    private static final String EXIT = "Exit";
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("?msg=")) {
                            break;
                        }
                    }
                    String outMessage;
                    if (str.contains(HELLO)) {
                        outMessage = HELLO;
                    } else if (str.contains(EXIT)) {
                        break;
                    } else {
                        String[] ss = str.split(" ");
                        outMessage = ss[1].substring(6);
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".concat(outMessage).concat("\r\n\r\n").getBytes());
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in log example", e);
        }
    }
}
