package org.example.task3.client.listener;

import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class SendDayButtonListener implements ActionListener {
    private final JTextField dayField;
    private final JTextArea outputField;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TO DO: implement send day logic here
        String day = dayField.getText();
        // send day to server and receive response
        String response = "Response from server: " + day; // TO DO: replace with actual response
        outputField.setText(response);
    }
}
