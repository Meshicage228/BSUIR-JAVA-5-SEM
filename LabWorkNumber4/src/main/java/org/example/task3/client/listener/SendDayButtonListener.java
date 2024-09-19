package org.example.task3.client.listener;

import lombok.RequiredArgsConstructor;
import org.example.task3.tcp.client.MatrixClientThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@RequiredArgsConstructor
public class SendDayButtonListener implements ActionListener {
    private final JTextField dayField;
    private final JTextArea outputField;
    public static MatrixClientThread matrixClientThread;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (matrixClientThread != null) {
            String day = dayField.getText();

            MatrixClientThread.setDay(day);

            String call = matrixClientThread.call();

            outputField.setText(call);
        }
    }
}
