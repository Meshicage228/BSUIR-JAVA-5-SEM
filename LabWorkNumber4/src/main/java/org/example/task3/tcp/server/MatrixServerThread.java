package org.example.task3.tcp.server;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@RequiredArgsConstructor
public class MatrixServerThread extends Thread {
    @Setter
    private static Socket socket;
    private static ServerSocket serverSocket;

    static {
        try {
            serverSocket = new ServerSocket(8000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                socket = serverSocket.accept();

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                String day = (String) ois.readObject();

                String weather = getWeather(day);

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(weather);

                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getWeather(String day) {
        switch (day) {
            case "monday" -> {
                return "ahahahahahahaha";
            }
            default -> {
                return "gsagssga";
            }
        }
    }
}