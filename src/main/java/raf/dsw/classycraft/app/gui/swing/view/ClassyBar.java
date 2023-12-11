package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.state.DodavanjeVezeState;

import javax.swing.*;

public class ClassyBar extends JToolBar {
    private JButton btnAddICO;
    private JButton btnAddVeza;
    private JButton btnAddSadrzaj;
    private JButton btnDelete;
    private JButton btnSelect;
    private PackageView packageView;

    public ClassyBar(PackageView packageView) {
        this.packageView = packageView;
        setOrientation(VERTICAL);


        btnAddICO = new JButton("Dodaj ICO");
        btnAddVeza = new JButton("Dodaj vezu");
        btnAddSadrzaj = new JButton("Dodaj sadržaj");
        btnDelete = new JButton("Obriši");
        btnSelect = new JButton("Selektuj");


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

        add(btnAddICO);
        add(btnAddVeza);
        add(btnAddSadrzaj);
        add(btnDelete);
        add(btnSelect);
    }
}