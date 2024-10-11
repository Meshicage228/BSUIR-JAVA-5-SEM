package org.bsuir.part2;

import org.bsuir.part2.service.CreateDialogWindow;

import javax.swing.*;

public class StartSecondPart {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CreateDialogWindow::new);
    }
}
