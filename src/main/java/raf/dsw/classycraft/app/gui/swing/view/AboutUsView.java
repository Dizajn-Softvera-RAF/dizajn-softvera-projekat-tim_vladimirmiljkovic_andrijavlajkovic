package raf.dsw.classycraft.app.gui.swing.view;


import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class AboutUsView   {



    public AboutUsView() {



        JFrame aboutUsFrame = new JFrame("About Us");

        aboutUsFrame.setLayout(new BorderLayout());

        JPanel containerPanel = new JPanel(new GridBagLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));


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

        Component horizontalStrut = Box.createHorizontalStrut(50); // 50 is the width of the invisible component, acting as a spacer.
        mainPanel.add(horizontalStrut);

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

        containerPanel.add(mainPanel, new GridBagConstraints());


        aboutUsFrame.add(containerPanel, BorderLayout.CENTER);

        aboutUsFrame.pack(); // koristiti ovo ili relativne veilicine
        aboutUsFrame.setLocationRelativeTo(null);
        aboutUsFrame.setVisible(true);
        aboutUsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);




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
