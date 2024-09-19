package org.example.task2;

import java.net.SocketException;
import java.net.UnknownHostException;

public class Task2UDP {
    public static void main(String[] args) throws SocketException, UnknownHostException {
        new EchoServer().start();

        EchoClient echoClient = new EchoClient();
        System.out.println(echoClient.sendEcho());
    }
}
