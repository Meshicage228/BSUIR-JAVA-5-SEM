package org.bsuir.part1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;

public class ListManagerDialog extends JDialog {
    private final JList<String> list1;
    private final JList<String> list2;
    private final JList<String> list3;
    private final JCheckBox[] checkBoxes;
    private final DefaultListModel<String> listModel1 = new DefaultListModel<>();;
    private final DefaultListModel<String> listModel2 = new DefaultListModel<>();;
    private final DefaultListModel<String> listModel3 = new DefaultListModel<>();;

    public ListManagerDialog() {
        setModal(true);
        setLayout(new BorderLayout());

        list1 = new JList<>(listModel1);
        list2 = new JList<>(listModel2);
        list3 = new JList<>(listModel3);

        checkBoxes = new JCheckBox[3];
        ButtonGroup checkBoxGroup = new ButtonGroup();

        for (int i = 0; i < 3; i++) {
            checkBoxes[i] = new JCheckBox("List " + (i + 1));
            checkBoxGroup.add(checkBoxes[i]);
        }

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(new JScrollPane(list1), BorderLayout.WEST);
        listPanel.add(new JScrollPane(list2), BorderLayout.CENTER);
        listPanel.add(new JScrollPane(list3), BorderLayout.EAST);

        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.add(checkBoxes[0]);
        checkBoxPanel.add(checkBoxes[1]);
        checkBoxPanel.add(checkBoxes[2]);

        JButton moveButton = new JButton("Move");
        moveButton.addActionListener(new MoveButtonListener());
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ClearButtonListener());

        add(listPanel, BorderLayout.CENTER);
        add(checkBoxPanel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(moveButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        Arrays.stream(checkBoxes).forEach(checkBox -> checkBox.addActionListener(new CheckBoxListener()));

        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        for (int i = 0; i < 10; i++) {
            listModel1.addElement("Element " + (i + 1));
        }

        pack();
        setVisible(true);
    }

    private class CheckBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            int selectedIndex = -1;
            for (int i = 0; i < 3; i++) {
                if (checkBoxes[i] == checkBox) {
                    selectedIndex = i;
                    break;
                }
            }

            if (selectedIndex != -1) {
                JList<String> sourceList = null;
                DefaultListModel<String> sourceListModel = null;
                DefaultListModel<String> targetListModel = null;
                switch (selectedIndex) {
                    case 0:
                        sourceList = list1;
                        sourceListModel = listModel1;
                        targetListModel = listModel2;
                        break;
                    case 1:
                        sourceList = list2;
                        sourceListModel = listModel2;
                        targetListModel = listModel3;
                        break;
                    case 2:
                        sourceList = list3;
                        sourceListModel = listModel3;
                        targetListModel = listModel1;
                        break;
                }

                int[] indices = sourceList.getSelectedIndices();
                for (int i = indices.length - 1; i >= 0; i--) {
                    String element = sourceListModel.get(indices[i]);
                    targetListModel.addElement(element);
                }
            }
        }
    }

    private class MoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 3; i++) {
                if (checkBoxes[i].isSelected()) {
                    JList<String> sourceList = null;
                    DefaultListModel<String> sourceListModel = null;
                    DefaultListModel<String> targetListModel = null;
                    switch (i) {
                        case 0:
                            sourceList = list1;
                            sourceListModel = listModel1;
                            targetListModel = listModel2;
                            break;
                        case 1:
                            sourceList = list2;
                            sourceListModel = listModel2;
                            targetListModel = listModel3;
                            break;
                        case 2:
                            sourceList = list3;
                            sourceListModel = listModel3;
                            targetListModel = listModel1;
                            break;
                    }

                    int[] indices = sourceList.getSelectedIndices();
                    for (int j = indices.length - 1; j >= 0; j--) {
                        String element = sourceListModel.get(indices[j]);
                        targetListModel.addElement(element);
                    }
                }
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 3; i++) {
                if (checkBoxes[i].isSelected()) {
                    DefaultListModel<String> listModel = switch (i) {
                        case 0 -> listModel1;
                        case 1 -> listModel2;
                        case 2 -> listModel3;
                        default -> null;
                    };

                    listModel.clear();
                }
            }
        }
    }

    public static void main(String[] args) {
        new ListManagerDialog();
    }
}