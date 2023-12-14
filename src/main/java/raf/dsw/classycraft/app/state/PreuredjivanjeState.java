package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementPainter;
import raf.dsw.classycraft.app.model.*;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class PreuredjivanjeState implements State {
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        for (ElementPainter painter : diagramView.getPainters()) {
            if (painter.elementAt(painter.getElement(), new Point(x, y))) {
                if (painter.getElement() instanceof Klasa) {
                    prikaziDijalogZaUredjivanjeKlase((Klasa) painter.getElement(), diagramView);
                } else if (painter.getElement() instanceof Interfejs) {
                    prikaziDijalogZaUredjivanjeInterfejsa((Interfejs) painter.getElement(), diagramView);
                } else if  (painter.getElement() instanceof Enuum) {
                    prikaziDijalogZaUredjivanjeEnuma((Enuum) painter.getElement(), diagramView);
                }
                break;
            }
        }
    }

    private void prikaziDijalogZaUredjivanjeKlase(Klasa klasa, DiagramView diagramView) {
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.setSize(800, 500);

        JPanel atributiPanel = new JPanel();
        atributiPanel.setLayout(new BoxLayout(atributiPanel, BoxLayout.Y_AXIS));
        popuniPanelSaSadrzajem(atributiPanel, klasa.getAtributi(), "Atribut");

        JPanel metodePanel = new JPanel();
        metodePanel.setLayout(new BoxLayout(metodePanel, BoxLayout.Y_AXIS));
        popuniPanelSaSadrzajem(metodePanel, klasa.getMetode(), "Metodu");

        JTextField nazivKlaseField = new JTextField(klasa.getName());
        JPanel nazivPanel = new JPanel(new BorderLayout());
        nazivPanel.add(new JLabel("Naziv klase:"), BorderLayout.NORTH);
        nazivPanel.add(nazivKlaseField, BorderLayout.CENTER);

        dialog.add(nazivPanel, BorderLayout.NORTH);
        dialog.add(atributiPanel, BorderLayout.WEST);
        dialog.add(metodePanel, BorderLayout.EAST);

        JButton potvrdiBtn = new JButton("Potvrdi");
        potvrdiBtn.addActionListener(e -> {
            klasa.setName(nazivKlaseField.getText());
            izvuciPodatkeIzAtributiPanela(atributiPanel, klasa);
            izvuciPodatkeIzMetodePanela(metodePanel, klasa);
            dialog.dispose();
            diagramView.repaint();
        });
        dialog.add(potvrdiBtn, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    private void prikaziDijalogZaUredjivanjeInterfejsa(Interfejs interfejs, DiagramView diagramView) {
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.setSize(800, 500);

        JPanel metodePanel = new JPanel();
        metodePanel.setLayout(new BoxLayout(metodePanel, BoxLayout.Y_AXIS));
        popuniPanelSaSadrzajem(metodePanel, interfejs.getMetode(), "Metodu");

        JTextField nazivInterfejsaField = new JTextField(interfejs.getName());
        JPanel nazivPanel = new JPanel(new BorderLayout());
        nazivPanel.add(new JLabel("Naziv interfejsa:"), BorderLayout.NORTH);
        nazivPanel.add(nazivInterfejsaField, BorderLayout.CENTER);

        dialog.add(nazivPanel, BorderLayout.NORTH);
        dialog.add(metodePanel, BorderLayout.CENTER);
        JButton potvrdiBtn = new JButton("Potvrdi");
        potvrdiBtn.addActionListener(e -> {
            interfejs.setName(nazivInterfejsaField.getText());
            izvuciPodatkeIzMetodePanela(metodePanel, interfejs);
            dialog.dispose();
            diagramView.repaint();
        });
        dialog.add(potvrdiBtn, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    private void prikaziDijalogZaUredjivanjeEnuma(Enuum enuum, DiagramView diagramView) {

        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.setSize(800, 500);


        JPanel clanoviPanel = new JPanel();
        clanoviPanel.setLayout(new BoxLayout(clanoviPanel, BoxLayout.Y_AXIS));
        popuniPanelSaClanovima(clanoviPanel, enuum.getClanoviEnuma());


        JTextField nazivEnumaField = new JTextField(enuum.getName());
        JPanel nazivPanel = new JPanel(new BorderLayout());
        nazivPanel.add(new JLabel("Naziv enuma:"), BorderLayout.NORTH);
        nazivPanel.add(nazivEnumaField, BorderLayout.CENTER);


        dialog.add(nazivPanel, BorderLayout.NORTH);
        dialog.add(clanoviPanel, BorderLayout.CENTER);
        JButton potvrdiBtn = new JButton("Potvrdi");
        potvrdiBtn.addActionListener(e -> {
            enuum.setName(nazivEnumaField.getText());
            izvuciPodatkeIzClanoviPanela(clanoviPanel, enuum);
            dialog.dispose();
            diagramView.repaint();
        });
        dialog.add(potvrdiBtn, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void popuniPanelSaSadrzajem(JPanel panel, List<? extends ClassContent> sadrzaj, String tipSadrzaja) {
        // Postojeci atributi i metode
        for (ClassContent content : sadrzaj) {
            JPanel sadrzajPanel = new JPanel(new FlowLayout());
            JComboBox<Character> vidljivostBox = new JComboBox<>(new Character[]{'+', '-', '#', ' '});
            JComboBox<TipoviPodataka> tipBox = new JComboBox<>(TipoviPodataka.values());
            JTextField nazivField = new JTextField(content.getNaziv(), 10);

            if (content instanceof Atribut) {
                Atribut atribut = (Atribut) content;
                vidljivostBox.setSelectedItem(Character.valueOf(atribut.getVidljivost()));
                tipBox.setSelectedItem(atribut.getTip());
            } else if (content instanceof Metod) {
                Metod metod = (Metod) content;
                vidljivostBox.setSelectedItem(Character.valueOf(metod.getVidljivost()));
                tipBox.setSelectedItem(metod.getTip());
            }

            sadrzajPanel.add(vidljivostBox);
            sadrzajPanel.add(tipBox);
            sadrzajPanel.add(nazivField);



            panel.add(sadrzajPanel);
        }

        //Novi atributi i metode
        JButton addBtn = new JButton("+ Dodaj " + tipSadrzaja);
        addBtn.addActionListener(e -> {
            JPanel newContentPanel = new JPanel(new FlowLayout());
            newContentPanel.add(new JComboBox<>(new Character[]{'+', '-', '#', ' '}));
            newContentPanel.add(new JComboBox<>(TipoviPodataka.values()));
            newContentPanel.add(new JTextField(10));
            JButton removeButton = new JButton("Obrisi");
            removeButton.addActionListener(event -> {
                panel.remove(newContentPanel);
                panel.revalidate();
                panel.repaint();
            });
            newContentPanel.add(removeButton);
            panel.add(newContentPanel);
            panel.revalidate();
            panel.repaint();
        });
        panel.add(addBtn);
    }
    private void popuniPanelSaClanovima(JPanel panel, List<ClanEnuma> clanovi) {
        for (ClanEnuma clan : clanovi) {
            JPanel clanPanel = new JPanel(new FlowLayout());
            JTextField nazivField = new JTextField(clan.getNaziv(), 10);
            clanPanel.add(nazivField);

            panel.add(clanPanel);
        }

        JButton addBtn = new JButton("+ Dodaj clana");
        addBtn.addActionListener(e -> {
            JPanel newClanPanel = new JPanel(new FlowLayout());
            newClanPanel.add(new JTextField(10));
            JButton removeButton = new JButton("Obrisi");
            removeButton.addActionListener(event -> {
                panel.remove(newClanPanel);
                panel.revalidate();
                panel.repaint();
            });
            newClanPanel.add(removeButton);
            panel.add(newClanPanel);
            panel.revalidate();
            panel.repaint();
        });
        panel.add(addBtn);
    }

    private void izvuciPodatkeIzAtributiPanela(JPanel panel, Klasa klasa) {
        List<Atribut> noviAtributi = new ArrayList<>();
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel contentPanel = (JPanel) comp;
                JComboBox<Character> vidljivostBox = (JComboBox<Character>) contentPanel.getComponent(0);
                JComboBox<TipoviPodataka> tipBox = (JComboBox<TipoviPodataka>) contentPanel.getComponent(1);
                JTextField nazivField = (JTextField) contentPanel.getComponent(2);

                Character vidljivost = (Character) vidljivostBox.getSelectedItem();
                TipoviPodataka tip = (TipoviPodataka) tipBox.getSelectedItem();
                String naziv = nazivField.getText();

                if (!naziv.isEmpty()) {
                    noviAtributi.add(new Atribut(naziv, vidljivost, tip));
                }
            }
        }
        klasa.setAtributi(noviAtributi);
    }

    private void izvuciPodatkeIzMetodePanela(JPanel panel, Klasa klasa) {
        List<Metod> noveMetode = new ArrayList<>();
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel contentPanel = (JPanel) comp;
                JComboBox<Character> vidljivostBox = (JComboBox<Character>) contentPanel.getComponent(0);
                JComboBox<TipoviPodataka> tipBox = (JComboBox<TipoviPodataka>) contentPanel.getComponent(1);
                JTextField nazivField = (JTextField) contentPanel.getComponent(2);

                Character vidljivost = (Character) vidljivostBox.getSelectedItem();
                TipoviPodataka tip = (TipoviPodataka) tipBox.getSelectedItem();
                String naziv = nazivField.getText();

                if (!naziv.isEmpty()) {
                    noveMetode.add(new Metod(naziv, vidljivost, tip));
                }
            }
        }
        klasa.setMetode(noveMetode);
    }
    private void izvuciPodatkeIzMetodePanela(JPanel panel, Interfejs interfejs) {
        List<Metod> noveMetode = new ArrayList<>();
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JPanel) {
                JPanel contentPanel = (JPanel) comp;
                JComboBox<Character> vidljivostBox = (JComboBox<Character>) contentPanel.getComponent(0);
                JComboBox<TipoviPodataka> tipBox = (JComboBox<TipoviPodataka>) contentPanel.getComponent(1);
                JTextField nazivField = (JTextField) contentPanel.getComponent(2);

                Character vidljivost = (Character) vidljivostBox.getSelectedItem();
                TipoviPodataka tip = (TipoviPodataka) tipBox.getSelectedItem();
                String naziv = nazivField.getText();

                if (!naziv.isEmpty()) {
                    noveMetode.add(new Metod(naziv, vidljivost, tip));
                }
            }
        }
        interfejs.setMetode(noveMetode);
    }
    private void izvuciPodatkeIzClanoviPanela(JPanel panel, Enuum enuum) {
        List<ClanEnuma> noviClanovi = new ArrayList<>();
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JPanel) {
                JTextField nazivField = (JTextField) ((JPanel) comp).getComponent(0);
                String naziv = nazivField.getText();
                if (!naziv.isEmpty()) {
                    noviClanovi.add(new ClanEnuma(naziv));
                }
            }
        }
        enuum.setClanoviEnuma(noviClanovi);
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
    }


}
