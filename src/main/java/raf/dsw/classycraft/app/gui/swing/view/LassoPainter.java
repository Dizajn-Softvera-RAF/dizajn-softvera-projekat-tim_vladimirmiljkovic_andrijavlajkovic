package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.DiagramElement;

import java.awt.*;

public class LassoPainter extends ElementPainter{
    private Rectangle lassoRect;

    public LassoPainter(Rectangle lassoRect) {
        super(null);
        this.lassoRect = lassoRect;
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {


        g.setColor(Color.BLUE);


        g.draw(lassoRect);

    }

    @Override
    public boolean elementAt(DiagramElement element, Point clickPoint) {
        return false;
    }


}
