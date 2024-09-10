package org.example.task3.client.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.task3.tcp.client.MatrixClientThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

@RequiredArgsConstructor
public class ConnectButtonListener implements ActionListener {
    private final JTextField ipAddressField;
    private final JTextField portField;

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        String ipAddress = ipAddressField.getText();
        int port = Integer.parseInt(portField.getText());

        Socket socketClient = new Socket(ipAddress, port);
        MatrixClientThread matrixClientThread = new MatrixClientThread(socketClient);

        System.out.println("Connected to server.");

        SendDayButtonListener.matrixClientThread = matrixClientThread;

        System.out.println("Connected to server at " + ipAddress + ":" + port);
    }
}