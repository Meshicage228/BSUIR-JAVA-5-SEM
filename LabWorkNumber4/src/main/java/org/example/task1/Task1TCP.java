package org.example.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Task1TCP {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8000);) {
            System.out.println("Server started. Waiting for client...");

            Socket socketClient = new Socket("localhost", 8000);
            System.out.println("Connected to server.");

            MatrixClientThread matrixClientThread = new MatrixClientThread(socketClient);
            matrixClientThread.start();

            Socket socket = serverSocket.accept();

            MatrixServerThread matrixServerThread = new MatrixServerThread(socket);
            matrixServerThread.start();

            matrixClientThread.join();
            matrixServerThread.join();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}