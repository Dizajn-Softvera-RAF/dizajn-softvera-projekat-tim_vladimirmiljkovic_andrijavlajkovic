package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.controller.ActionManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class AboutUsView   {



    public AboutUsView() {



        JFrame aboutUsFrame = new JFrame("About Us");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        aboutUsFrame.setSize(screenWidth / 2, screenHeight / 2);
        aboutUsFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        aboutUsFrame.add(mainPanel);

        // Skaliranje slike
        ImageIcon originalIconAndrija = MainFrame.getInstance().getActionManager().getAboutUsAction().loadImage("/images/andrijavlajkovic_slika.jpg");
      //  Image imgAndrija = originalIconAndrija.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
      //  ImageIcon scaledIconAndrija = new ImageIcon(imgAndrija);

        // Andrija panel
        JPanel panelAndrija = new JPanel();
        panelAndrija.setLayout(new BoxLayout(panelAndrija, BoxLayout.Y_AXIS));
      //  JLabel imageLabelAndrija = new JLabel(scaledIconAndrija);
        JLabel nameLabelAndrija = new JLabel("Name: Andrija Vlajković");
      //  panelAndrija.add(imageLabelAndrija);
        panelAndrija.add(nameLabelAndrija);
        mainPanel.add(panelAndrija);

        // Skaliranje slike
        ImageIcon originalIconVladimir = MainFrame.getInstance().getActionManager().getAboutUsAction().loadImage("/images/vladimirmiljkovic_slika.jpg");
//        Image imgVladimir = originalIconVladimir.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);
//        ImageIcon scaledIconVladimir = new ImageIcon(imgVladimir);

        // Vladimir panel
        JPanel panelVladimir = new JPanel();
        panelVladimir.setLayout(new BoxLayout(panelVladimir, BoxLayout.Y_AXIS));
     //   JLabel imageLabelVladimir = new JLabel(scaledIconVladimir);
        JLabel nameLabelVladimir = new JLabel("Name: Vladimir Miljković");
     //  panelVladimir.add(imageLabelVladimir);
        panelVladimir.add(nameLabelVladimir);
        mainPanel.add(panelVladimir);

        // ne radi
        mainPanel.add(Box.createRigidArea(new Dimension(100, 100)));

        aboutUsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        aboutUsFrame.setVisible(true);
    }

    public ImageIcon loadImage(String fileName) {
        URL imageURL = getClass().getResource(fileName);
        ImageIcon imageIcon = null;
        if (imageURL != null) {
            imageIcon = new ImageIcon(imageURL);
        } else {
            System.err.println("Resource not found: " + fileName);
        }
        return imageIcon;
    }

}
