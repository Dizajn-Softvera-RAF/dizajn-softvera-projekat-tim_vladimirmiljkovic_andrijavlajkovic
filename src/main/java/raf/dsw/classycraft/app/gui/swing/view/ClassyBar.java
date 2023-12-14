package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.state.DodavanjeVezeState;

import javax.swing.*;
import java.awt.*;

public class ClassyBar extends JToolBar {
    private JButton btnAddICO;
    private JButton btnAddVeza;
    private JButton btnAddSadrzaj;
    private JButton btnDelete;
    private JButton btnSelect;
    private JButton btnMove;
    private JButton btnZoom;
    private JButton btnDupliraj;
    private PackageView packageView;

    public ClassyBar(PackageView packageView) {
        this.packageView = packageView;
        setOrientation(VERTICAL);

        ImageIcon icon1 = new ImageIcon("src/main/resources/images/crniplus.png");
        ImageIcon icon2 = new ImageIcon("src/main/resources/images/connection.png");
        ImageIcon icon3 = new ImageIcon("src/main/resources/images/editcontent.png");
        ImageIcon icon4 = new ImageIcon("src/main/resources/images/deleteicon.png");
        ImageIcon icon5 = new ImageIcon("src/main/resources/images/selecticon.png");
        ImageIcon icon6 = new ImageIcon("src/main/resources/images/moveicon.png");
        ImageIcon icon7 = new ImageIcon("src/main/resources/images/zoomicon.png");
        ImageIcon icon8 = new ImageIcon("src/main/resources/images/duplicateicon.png");
        btnAddICO = new JButton("New Attribute",icon1);
        btnAddVeza = new JButton("New Connection",icon2);
        btnAddSadrzaj = new JButton("Edit Content",icon3);
        btnDelete = new JButton("Delete",icon4);
        btnSelect = new JButton("Select",icon5);
        btnMove = new JButton("Move",icon6);
        btnZoom = new JButton("Zoom",icon7);
        btnDupliraj = new JButton("Duplicate",icon8);

        btnAddICO.addActionListener(e -> packageView.startDodavanjeICOState());
        btnAddVeza.addActionListener(e -> {
            String[] options = {"Agregacija", "Generalizacija", "Kompozicija", "Zavisnost"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Izaberite tip veze:",
                    "Dodavanje veze",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (choice != -1) {
                String selectedType = options[choice];
                packageView.startDodavanjeVezeState();
                if (packageView.getStateManager().getCurrent() instanceof DodavanjeVezeState) {
                    ((DodavanjeVezeState) packageView.getStateManager().getCurrent()).setSelectedType(selectedType);
                }
            }
        });
        btnDelete.addActionListener(e -> packageView.startBrisanjeState());
        btnSelect.addActionListener(e -> packageView.startSelekcijaState());
        btnMove.addActionListener(e -> packageView.startPomeranjeState());
        btnAddSadrzaj.addActionListener(e -> packageView.startPreuredjivanjeState());
        btnDupliraj.addActionListener(e -> packageView.startDupliranjeState());
        btnZoom.addActionListener(e -> packageView.startZoomState());

        add(btnAddICO);
        add(btnAddVeza);
        add(btnAddSadrzaj);
        add(btnDelete);
        add(btnSelect);
        add(btnMove);
        add(btnZoom);
        add(btnDupliraj);
    }
}