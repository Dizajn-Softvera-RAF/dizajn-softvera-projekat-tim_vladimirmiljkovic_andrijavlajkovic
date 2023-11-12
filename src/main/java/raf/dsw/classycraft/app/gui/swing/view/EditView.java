package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class EditView extends JDialog {

    public JTextField textField;

    public EditView(JFrame parent) {
        super(parent,"Edit name");
        textField = new JTextField(10);

        JButton ok = new JButton("Confirm");
        ok.addActionListener(e -> {
            MainFrame.getInstance().getActionManager().getEditProjectAction().dialogName(textField.getText());
            dispose();
        });
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> dispose());

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        JLabel label  = new JLabel("Enter name: ");

        panel.add(label);
        panel.add(textField);
        add(panel,BorderLayout.NORTH);
        add(ok,BorderLayout.EAST);
        add(cancel,BorderLayout.WEST);
        pack();
        setLocationRelativeTo(parent);
    }
}
