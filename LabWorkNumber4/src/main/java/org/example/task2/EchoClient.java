package org.example.task2;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    private final DatagramSocket socket;
    private final InetAddress address;

    private final byte[] buf = new byte[256];
    private static final String HOST_NAME = "localhost";
    private static final Integer PORT = 4445;

    public EchoClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName(HOST_NAME);
    }

    @SneakyThrows
    public Double sendEcho() {
        double[] doubles1 = writeValues();
        byte[] bytes = serializeDoubles(doubles1);

        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
        socket.send(packet);

        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());

        return Double.parseDouble(received);
    }

    private byte[] serializeDoubles(double[] doubles) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        for (double d : doubles) {
            dos.writeDouble(d);
        }

        return bos.toByteArray();
    }

    private double[] writeValues() {
        Scanner scanner = new Scanner(System.in);

        double x = readDouble("Write x: ", scanner);
        double y = readDouble("Write y: ", scanner);
        double z = readDouble("Write z: ", scanner);

        return new double[]{x, y, z};
    }

    private double readDouble(String prompt, Scanner scanner) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Invalid input. Please enter a valid double value.");
                scanner.next();
            }
        }
    }
}