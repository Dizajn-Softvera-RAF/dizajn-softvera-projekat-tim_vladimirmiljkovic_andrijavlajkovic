package raf.dsw.classycraft.app.gui.swing.view;

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
        btnAddVeza.addActionListener(e -> packageView.startDodavanjeVezeState());
        btnAddSadrzaj.addActionListener(e -> packageView.startDodavanjeSadrzajaState());
        btnDelete.addActionListener(e -> packageView.startBrisanjeState());
        btnSelect.addActionListener(e -> packageView.startSelekcijaState());

        add(btnAddICO);
        add(btnAddVeza);
        add(btnAddSadrzaj);
        add(btnDelete);
        add(btnSelect);
    }
}