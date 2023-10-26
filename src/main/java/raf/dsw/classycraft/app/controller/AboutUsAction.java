package raf.dsw.classycraft.app.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction{

    public AboutUsAction() {
        putValue(NAME, "About Us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame aboutUsFrame = new JFrame("About Us");
        aboutUsFrame.setSize(1000, 1000);  // Set the window size
        aboutUsFrame.setLocationRelativeTo(null);  // Center the window

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        aboutUsFrame.add(mainPanel);

        // Skaliranje slike
        ImageIcon originalIconAndrija = loadImage("/images/andrijavlajkovic_slika.jpg");
        Image imgAndrija = originalIconAndrija.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIconAndrija = new ImageIcon(imgAndrija);

        // Andrija panel
        JPanel panelAndrija = new JPanel();
        panelAndrija.setLayout(new BoxLayout(panelAndrija, BoxLayout.Y_AXIS));
        JLabel imageLabelAndrija = new JLabel(scaledIconAndrija);
        JLabel nameLabelAndrija = new JLabel("Name: Andrija Vlajković");
        panelAndrija.add(imageLabelAndrija);
        panelAndrija.add(nameLabelAndrija);
        mainPanel.add(panelAndrija);

        // Skaliranje slike
        ImageIcon originalIconVladimir = loadImage("/images/vladimirmiljkovic_slika.jpg");
        Image imgVladimir = originalIconVladimir.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
        ImageIcon scaledIconVladimir = new ImageIcon(imgVladimir);

        // Vladimir panel
        JPanel panelVladimir = new JPanel();
        panelVladimir.setLayout(new BoxLayout(panelVladimir, BoxLayout.Y_AXIS));
        JLabel imageLabelVladimir = new JLabel(scaledIconVladimir);
        JLabel nameLabelVladimir = new JLabel("Name: Vladimir Miljković");
        panelVladimir.add(imageLabelVladimir);
        panelVladimir.add(nameLabelVladimir);
        mainPanel.add(panelVladimir);

        aboutUsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aboutUsFrame.setVisible(true);
    }

}
