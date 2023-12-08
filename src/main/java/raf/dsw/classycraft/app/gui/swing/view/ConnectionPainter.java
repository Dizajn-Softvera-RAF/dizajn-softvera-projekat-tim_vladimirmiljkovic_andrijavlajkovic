package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.DiagramElement;

import java.awt.*;

public class ConnectionPainter extends ElementPainter{
    public ConnectionPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {

    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        return false;
    }
}
