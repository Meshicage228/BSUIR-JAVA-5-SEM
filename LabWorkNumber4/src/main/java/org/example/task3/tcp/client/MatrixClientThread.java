package org.example.task3.tcp.client;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

@RequiredArgsConstructor
public class MatrixClientThread {
    private final Socket socket;
    @Setter
    private static String day;

    public String call() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(day);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String inverseMatrix = (String) ois.readObject();

            socket.close();
            return inverseMatrix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}