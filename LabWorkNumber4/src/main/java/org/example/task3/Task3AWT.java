package org.example.task3;

import org.example.task3.client.ClientWindow;
import org.example.task3.tcp.server.MatrixServerThread;

public class Task3AWT {
    public static void main(String[] args) {
        Thread thread = new Thread(new MatrixServerThread());
        thread.start();
        new ClientWindow();
    }
}
