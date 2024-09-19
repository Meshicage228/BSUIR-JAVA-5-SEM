package org.example.task2;

import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EchoServer extends Thread {
    private final DatagramSocket socket;
    private final byte[] buf = new byte[256];
    private static final Integer PORT = 4445;

    public EchoServer() throws SocketException {
        socket = new DatagramSocket(PORT);
    }

    @SneakyThrows
    public void run() {
        while (true) {
            DatagramPacket packet = receivePacket();
            double[] doubles = deserializeDoubles(packet.getData());
            Double result = calculateResult(doubles);
            writeVariablesToFile(result, doubles);
            sendResponse(packet.getAddress(), packet.getPort(), result);
        }
    }

    private DatagramPacket receivePacket() throws IOException {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        return packet;
    }

    private double[] deserializeDoubles(byte[] data) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bis);

        double[] doubles = new double[data.length / 8];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = dis.readDouble();
        }
        return doubles;
    }

    private Double calculateResult(double[] doubles) {
        Double x = doubles[0];
        Double y = doubles[1];
        Double z = doubles[2];
        return calculateAbs(x, y) * calculateSecondValue(z);
    }

    private void sendResponse(InetAddress address, int port, Double result) throws IOException {
        String s = String.valueOf(result);
        byte[] responseBuf = s.getBytes();
        DatagramPacket responsePacket = new DatagramPacket(responseBuf, responseBuf.length, address, port);
        socket.send(responsePacket);
    }

    private Double calculateSecondValue(Double z){
        return 1 + z + Math.pow(z, 2) / 2 + Math.pow(z, 3) / 3;
    }

    private Double calculateAbs(Double x, Double y){
        double osn = Math.abs(Math.cos(x) - Math.pow(Math.E, y));
        double pow = Math.pow(osn, 1 + 2 * Math.pow(Math.log(y), 2));
        return pow;
    }

    public void writeVariablesToFile(double result, double ... variables) {
        try (FileWriter writer = new FileWriter("LabWorkNumber4/src/main/java/org/example/task2/variables.txt")){
            writer.write("x = " + variables[0] + "\n");
            writer.write("y = " + variables[1] + "\n");
            writer.write("z = " + variables[2] + "\n");
            writer.write("Result of formula : " + result + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
