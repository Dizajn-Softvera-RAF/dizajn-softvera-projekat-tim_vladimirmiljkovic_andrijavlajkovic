package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.*;

import java.awt.*;

public class InterfejsPainter extends ElementPainter {
    public InterfejsPainter(Interfejs interfejs) {
        super(interfejs);
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {
        if (element instanceof Interfejs) {
            Interfejs interfejs = (Interfejs) element;
            Point position = interfejs.getPosition();
            Dimension originalSize = interfejs.getSize();
            Dimension adjustedSize = calculateAdjustedSize(g, interfejs, originalSize);

            if ( !interfejs.getMetode().isEmpty()) {
                interfejs.setSize(adjustedSize);
            }

            g.setColor(Color.GREEN);
            g.fillRect(position.x, position.y, interfejs.getSize().width, interfejs.getSize().height);

            g.setColor(Color.BLACK);
            g.drawRect(position.x, position.y, interfejs.getSize().width, interfejs.getSize().height);

            drawNazivSaLinijom(g, interfejs.getName(), position, interfejs.getSize());
            drawClassContent(g, interfejs, position);
        }
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
    private Dimension calculateAdjustedSize(Graphics2D g, Interfejs interfejs, Dimension originalSize) {
        FontMetrics fm = g.getFontMetrics(new Font("Arial", Font.PLAIN, 12));
        int maxStringWidth = fm.stringWidth(interfejs.getName());


        for (Metod metod : interfejs.getMetode()) {
            int stringWidth = fm.stringWidth(metod.toString());
            if (stringWidth > maxStringWidth) {
                maxStringWidth = stringWidth;
            }
        }

        int novaSirina = Math.max(originalSize.width, maxStringWidth + 10);

        int brojRedova =  interfejs.getMetode().size() + 1;
        int visinaPoRedu = fm.getHeight();
        int novaVisina = Math.max(originalSize.height, visinaPoRedu * brojRedova + 10);

        return new Dimension(novaSirina, novaVisina);
    }


    private void drawClassContent(Graphics2D g, Interfejs interfejs, Point position) {
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();

        int textY = position.y + fm.getHeight() * 2;


        for (Metod metod : interfejs.getMetode()) {
            g.drawString(metod.toString(), position.x + 5, textY);
            textY += fm.getHeight();
        }
    }
    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        Interfejs interfejs = (Interfejs) element;
        Rectangle bounds = new Rectangle(interfejs.getPosition(), interfejs.getSize());
        return bounds.contains(pos);
    }
}
