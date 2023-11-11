package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;

import javax.swing.*;
import java.awt.*;

public class PackageView extends JPanel implements ISubscriber {
    private JTabbedPane tabbedPane;

    public PackageView() {
        tabbedPane = new JTabbedPane();
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
    }

    @Override
    public void update(Notification notification) {

    }

    public void displayDiagramsForPackage(Package pkg) {
        System.out.println("Displaying diagrams for package: " + pkg.getName());
        tabbedPane.removeAll();
        for (Diagram diagram : pkg.getDiagrams()) {
            System.out.println("Adding diagram: " + diagram.getName());
            DiagramView view = new DiagramView(diagram);
            tabbedPane.addTab(diagram.getName(), view);
            diagram.addSubscriber(view); // Subscribing to changes in Diagram
        }
    }


}
