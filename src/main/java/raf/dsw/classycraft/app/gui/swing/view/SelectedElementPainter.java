package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.Interclass;

import java.awt.*;

public class SelectedElementPainter extends ElementPainter{
    public SelectedElementPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {
        Rectangle bounds = odrediGranicu(element);
        if (bounds != null) {

            float[] dashPattern = {10, 5};
            g.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));
            g.setColor(Color.RED);
            g.draw(bounds);
        }
    }

    private Rectangle odrediGranicu(DiagramElement element) {
        if (element instanceof Interclass) {
            Point position = ((Interclass)element).getPosition();
            Dimension size = ((Interclass)element).getSize();
            return new Rectangle(position.x, position.y, size.width, size.height);
        }

        return null;
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {

        return false;
    }
}
