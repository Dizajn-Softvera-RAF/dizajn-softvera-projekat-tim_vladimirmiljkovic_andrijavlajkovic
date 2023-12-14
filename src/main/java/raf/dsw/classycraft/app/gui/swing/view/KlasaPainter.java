package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.*;

import java.awt.*;

public class KlasaPainter extends ElementPainter {
    public KlasaPainter(Klasa klasa) {
        super(klasa);
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {
        if (element instanceof Klasa) {
            Klasa klasa = (Klasa) element;
            Point position = klasa.getPosition();
            Dimension originalSize = klasa.getSize();
            Dimension adjustedSize = izracunajAdjustedSize(g, klasa, originalSize);

            if (!klasa.getAtributi().isEmpty() || !klasa.getMetode().isEmpty()) {
                klasa.setSize(adjustedSize);
            }

            g.setColor(Color.CYAN);
            g.fillRect(position.x, position.y, klasa.getSize().width, klasa.getSize().height);

            g.setColor(Color.BLACK);
            g.drawRect(position.x, position.y, klasa.getSize().width, klasa.getSize().height);

            drawNazivSaLinijom(g, klasa.getName(), position, klasa.getSize());
            drawClassContent(g, klasa, position);
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
    private Dimension izracunajAdjustedSize(Graphics2D g, Klasa klasa, Dimension originalSize) {
        FontMetrics fm = g.getFontMetrics(new Font("Arial", Font.PLAIN, 12));
        int maxStringWidth = fm.stringWidth(klasa.getName());

        for (Atribut atribut : klasa.getAtributi()) {
            int stringWidth = fm.stringWidth(atribut.toString());
            if (stringWidth > maxStringWidth) {
                maxStringWidth = stringWidth;
            }
        }

        for (Metod metod : klasa.getMetode()) {
            int stringWidth = fm.stringWidth(metod.toString());
            if (stringWidth > maxStringWidth) {
                maxStringWidth = stringWidth;
            }
        }

        int novaSirina = Math.max(originalSize.width, maxStringWidth + 10);

        int brojRedova = klasa.getAtributi().size() + klasa.getMetode().size() + 1;
        int visinaPoRedu = fm.getHeight();
        int novaVisina = Math.max(originalSize.height, visinaPoRedu * brojRedova + 10);

        return new Dimension(novaSirina, novaVisina);
    }


    private void drawClassContent(Graphics2D g, Klasa klasa, Point position) {
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();

        int textY = position.y + fm.getHeight() * 2;

        for (Atribut atribut : klasa.getAtributi()) {
            g.drawString(atribut.toString(), position.x + 5, textY);
            textY += fm.getHeight();
        }

        for (Metod metod : klasa.getMetode()) {
            g.drawString(metod.toString(), position.x + 5, textY);
            textY += fm.getHeight();
        }
    }
    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        Klasa klasa = (Klasa) element;
        Rectangle bounds = new Rectangle(klasa.getPosition(), klasa.getSize());
        return bounds.contains(pos);
    }
}