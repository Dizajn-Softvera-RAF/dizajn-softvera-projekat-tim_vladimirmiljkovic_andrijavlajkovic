package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.*;

import java.awt.*;

public class InterClassPainter extends ElementPainter{
    public InterClassPainter(DiagramElement element) {
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
        if (element.getPosition() == null) {
            System.out.println("Position je null");
        }

        Color fillColor;
        if (element instanceof Klasa) {
            fillColor = Color.CYAN;

        } else if (element instanceof Interfejs) {
            fillColor = Color.GREEN;

        } else {
            fillColor = Color.ORANGE;

        }
        if (element.getSize() == null) {
            System.out.println("Size je null");
        }

        g.setColor(fillColor);
        Point position = element.getPosition();
        g.fillRect(position.x, position.y, element.getSize().width, element.getSize().height);


        g.setColor(Color.BLACK);
        g.drawRect(position.x, position.y, element.getSize().width, element.getSize().height);

        drawNazivSaLinijom(g, element.getName(), position, element.getSize());
    }

    private void drawNazivSaLinijom(Graphics2D g, String text, Point position, Dimension size) {
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();

        int textX = position.x + (size.width - fm.stringWidth(text)) / 2;
        int textY = position.y + fm.getAscent();
        g.setColor(Color.BLACK);
        g.drawString(text, textX, textY);

        int lineY = textY + fm.getDescent();
        g.drawLine(position.x, lineY, position.x + size.width, lineY);
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        if (element instanceof Klasa) {
            Klasa klasa = (Klasa) element;
//            if (klasa == null || klasa.getPosition() == null || klasa.getSize() == null) {
//                System.out.println("Element, position ili size su null");
//                return false;
//            }
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
