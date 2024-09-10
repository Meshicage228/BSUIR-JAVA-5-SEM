package org.example.task3.client.listener;

import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class ConnectButtonListener implements ActionListener {
    private final JTextField ipAddressField;
    private final JTextField portField;

    @Override
    public void actionPerformed(ActionEvent e) {
        String ipAddress = ipAddressField.getText();
        int port = Integer.parseInt(portField.getText());

        System.out.println("Connected to server at " + ipAddress + ":" + port);
    }
}