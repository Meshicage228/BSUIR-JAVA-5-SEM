package org.example.task1;

import lombok.RequiredArgsConstructor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@RequiredArgsConstructor
public class MatrixClientThread extends Thread {
    private final Socket socket;

    @Override
    public void run() {
        try {
            double[][] matrix = {
                    {1.0, 2.0, 3.0},
                    {4.0, 5.0, 6.0},
                    {7.0, 5.0, 9.0}
            };

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(matrix);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            double[][] inverseMatrix = (double[][]) ois.readObject();

            for (double[] row : inverseMatrix) {
                for (double element : row) {
                    System.out.print(element + " ");
                }
                System.out.println();
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}