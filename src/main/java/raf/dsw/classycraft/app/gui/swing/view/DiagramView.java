package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

import javax.swing.*;

public class DiagramView extends JPanel implements ISubscriber {
    private Diagram diagram;

    public DiagramView(Diagram diagram) {
        this.diagram = diagram;
    }


    @Override
    public void update(Notification notification) {

    }

}
