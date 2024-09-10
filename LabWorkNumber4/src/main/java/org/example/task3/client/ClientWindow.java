package org.example.task3.client;

import org.example.task3.client.listener.ConnectButtonListener;
import org.example.task3.client.listener.SendDayButtonListener;

import javax.swing.*;
import java.awt.*;

public class ClientWindow {
    private final JFrame frame;
    private final JTextField ipAddressField;
    private final JTextField portField;
    private final JTextField dayField;
    private final JTextArea outputField;
    private final JButton connectButton;
    private final JButton sendDayButton;

    public ClientWindow() {
        frame = new JFrame("Client Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 1600);
        frame.setLayout(new GridLayout(7, 10));

        ipAddressField = new JTextField("IP Address", 15);
        portField = new JTextField("Port", 15);

        connectButton = new JButton("Connect");
        connectButton.addActionListener(new ConnectButtonListener(ipAddressField, portField));

        JPanel jPanel1 = new JPanel(new FlowLayout());

        jPanel1.add(connectButton);
        jPanel1.add(ipAddressField);
        jPanel1.add(portField);
        frame.add(jPanel1);

        outputField = new JTextArea(10, 20);
        outputField.setEditable(false);

        dayField = new JTextField("Write Day", 15);
        sendDayButton = new JButton("Send Day");
        sendDayButton.addActionListener(new SendDayButtonListener(dayField, outputField));

        JPanel jPanel = new JPanel(new FlowLayout());

        jPanel.add(connectButton);
        jPanel.add(ipAddressField);
        jPanel.add(portField);
        frame.add(jPanel);

        frame.add(sendDayButton);
        frame.add(dayField);

        JPanel jPanel3 = new JPanel(new FlowLayout());

        jPanel3.add(sendDayButton);
        jPanel3.add(dayField);
        frame.add(jPanel3);

        JPanel jPanel4 = new JPanel(new FlowLayout());
        jPanel4.add(new JScrollPane(outputField));
        frame.add(jPanel4);

        frame.pack();
        frame.setVisible(true);
    }
}