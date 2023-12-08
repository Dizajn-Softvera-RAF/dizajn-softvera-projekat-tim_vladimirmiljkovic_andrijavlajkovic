package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.*;

import java.awt.*;

public class ICPainter extends ElementPainter{
    public ICPainter(DiagramElement element) {
        super(element);

    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {
        if (element instanceof Klasa) {
            drawElement(g, (Klasa) element);
        } else if (element instanceof Interfejs) {
            drawElement(g, (Interfejs) element);
        } else if (element instanceof Enuum) {
            drawElement(g, (Enuum) element);
        }
    }

    private void drawElement(Graphics2D g, Interclass element) {
        g.setColor(element.getColor());
        Point position = element.getPosition();
        Dimension size = element.getSize();
        g.fillRect(position.x, position.y, size.width, size.height);
        drawCenteredString(g, element.getName(), position, size);
    }

    private void drawCenteredString(Graphics2D g, String text, Point position, Dimension size) {
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int x = position.x + (size.width - fm.stringWidth(text)) / 2;
        int y = position.y + ((size.height - fm.getHeight()) / 2) + fm.getAscent();
        g.setColor(Color.BLACK);
        g.drawString(text, x, y);
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {

        if (element instanceof Klasa) {
            Klasa klasa = (Klasa) element;
            Point position = klasa.getPosition();
            Dimension size = klasa.getSize();
            Rectangle elementBounds = new Rectangle(position.x, position.y, size.width, size.height);
            return elementBounds.contains(pos);
        }
        else if (element instanceof Interfejs) {
            Interfejs interfejs = (Interfejs) element;
            Point position = interfejs.getPosition();
            Dimension size = interfejs.getSize();
            Rectangle elementBounds = new Rectangle(position.x, position.y, size.width, size.height);
            return elementBounds.contains(pos);
        }
        else if (element instanceof Enuum) {
            Enuum enuum = (Enuum) element;
            Point position = enuum.getPosition();
            Dimension size = enuum.getSize();
            Rectangle elementBounds = new Rectangle(position.x, position.y, size.width, size.height);
            return elementBounds.contains(pos);
        }
        return false;
    }
}
