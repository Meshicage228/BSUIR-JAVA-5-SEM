package org.bsuir.part2.service;

import lombok.Getter;
import org.bsuir.part2.model.Book;
import org.bsuir.part2.util.ReadWriteTxtFileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.bsuir.part2.util.Constraints.*;

@Getter
public class CreateDialogWindow {
    private final ReadWriteTxtFileUtil txtFileUtil = new ReadWriteTxtFileUtil();
    private final JTextField titleField;
    private final JTextField authorComboBox;
    private final JTextArea descriptionArea;
    private final JCheckBox availableCheckBox;
    private final JRadioButton fictionRadioButton;
    private final JLabel statusLabel;
    private final DefaultListModel<String> bookListModel;

    public CreateDialogWindow() {
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleField = new JTextField(20);
        authorComboBox = new JTextField(20);
        descriptionArea = new JTextArea(5, 20);
        availableCheckBox = new JCheckBox("Available");
        fictionRadioButton = new JRadioButton("Fiction");
        JRadioButton nonFictionRadioButton = new JRadioButton("Non-Fiction");
        statusLabel = new JLabel("Book Registration Window");

        ButtonGroup categoryGroup = new ButtonGroup();
        categoryGroup.add(fictionRadioButton);
        categoryGroup.add(nonFictionRadioButton);

        bookListModel = new DefaultListModel<>();
        JList<String> bookList = new JList<>(bookListModel);

        JButton submitButton = new JButton("Register Book");
        submitButton.addActionListener(new SubmitButtonListener());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        panel.add(new JLabel("Title: "));
        panel.add(titleField);

        panel.add(new JLabel("Author: "));
        panel.add(authorComboBox);

        panel.add(new JLabel("Description: "));
        panel.add(new JScrollPane(descriptionArea));

        panel.add(availableCheckBox);

        panel.add(fictionRadioButton);
        panel.add(nonFictionRadioButton);

        panel.add(submitButton);

        panel.add(new JLabel("Book List: "));
        panel.add(new JScrollPane(bookList));

        panel.add(statusLabel);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

        txtFileUtil.loadBookData(bookListModel);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String author = authorComboBox.getText();
            String description = descriptionArea.getText();
            Boolean available = availableCheckBox.isSelected();
            String category = fictionRadioButton.isSelected() ? "Fiction" : "Non-Fiction";

            Book book = new Book(title, author, description, available, category);

            if (bookIsValid(book)) {

                bookListModel.addElement(book.getTitle());

                txtFileUtil.saveBookData(book);

                statusLabel.setText(REGISTRATION_SUCCESS);
            } else {
                statusLabel.setText(REGISTRATION_FAIL);
            }
        }

        private boolean bookIsValid(Book book) {
            return nonNull(book) && isValid(book);
        }

        private boolean isValid(Object obj) {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (isNull(value) || (value instanceof String && ((String) value).isBlank())) {
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            return true;
        }
    }
}