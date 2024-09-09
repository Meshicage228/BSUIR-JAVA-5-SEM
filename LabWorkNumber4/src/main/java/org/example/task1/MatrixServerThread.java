package org.example.task1;

import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@RequiredArgsConstructor
class MatrixServerThread extends Thread {
    private final Socket socket;

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            double[][] matrix = (double[][]) ois.readObject();

            RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);
            RealMatrix inverseMatrix = MatrixUtils.inverse(realMatrix);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(inverseMatrix.getData());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}