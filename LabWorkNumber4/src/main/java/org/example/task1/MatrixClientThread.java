package org.example.task1;

import lombok.RequiredArgsConstructor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

@RequiredArgsConstructor
public class MatrixClientThread extends Thread {
    private final Socket socket;

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            int rows = getValidIntInput(scanner, "Enter the number of rows: ");
            int cols = getValidIntInput(scanner, "Enter the number of columns: ");

            double[][] matrix = createMatrix(scanner, rows, cols);

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

    private static int getValidIntInput(Scanner scanner, String prompt) {
        int value = 0;
        while (true) {
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                if (value <= 0) {
                    System.out.println("Number must be a positive integer. Please try again.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
        return value;
    }

    private static double[][] createMatrix(Scanner scanner, int rows, int cols) {
        double[][] matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = getValidDoubleInput(scanner, "Enter element [" + i + "][" + j + "]: ");
            }
        }

        return matrix;
    }

    private static double getValidDoubleInput(Scanner scanner, String prompt) {
        double value = 0;
        while (true) {
            try {
                System.out.print(prompt);
                value = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid double value.");
                scanner.next();
            }
        }
        return value;
    }
}