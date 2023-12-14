package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.ClanEnuma;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.Enuum;
import raf.dsw.classycraft.app.model.Metod;

import java.awt.*;

public class EnuumPainter extends ElementPainter {
    public EnuumPainter(Enuum enuum) {
        super(enuum);
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {
        if (element instanceof Enuum) {
            Enuum enuum = (Enuum) element;
            Point position = enuum.getPosition();
            Dimension originalSize = enuum.getSize();
            Dimension adjustedSize = calculateAdjustedSize(g, enuum, originalSize);

            if ( !enuum.getClanoviEnuma().isEmpty()) {
                enuum.setSize(adjustedSize);
            }

            g.setColor(Color.ORANGE);
            g.fillRect(position.x, position.y, enuum.getSize().width, enuum.getSize().height);

            g.setColor(Color.BLACK);
            g.drawRect(position.x, position.y, enuum.getSize().width, enuum.getSize().height);

            drawNazivSaLinijom(g, enuum.getName(), position, enuum.getSize());
            drawClassContent(g, enuum, position);
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
    private Dimension calculateAdjustedSize(Graphics2D g, Enuum enuum, Dimension originalSize) {
        FontMetrics fm = g.getFontMetrics(new Font("Arial", Font.PLAIN, 12));
        int maxStringWidth = fm.stringWidth(enuum.getName());


        for (ClanEnuma clanEnuma : enuum.getClanoviEnuma()) {
            int stringWidth = fm.stringWidth(clanEnuma.toString());
            if (stringWidth > maxStringWidth) {
                maxStringWidth = stringWidth;
            }
        }

        int novaSirina = Math.max(originalSize.width, maxStringWidth + 10);

        int brojRedova =  enuum.getClanoviEnuma().size() + 1;
        int visinaPoRedu = fm.getHeight();
        int novaVisina = Math.max(originalSize.height, visinaPoRedu * brojRedova + 10);

        return new Dimension(novaSirina, novaVisina);
    }


    private void drawClassContent(Graphics2D g, Enuum enuum, Point position) {
        Font font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();

        int textY = position.y + fm.getHeight() * 2;


        for (ClanEnuma clanEnuma : enuum.getClanoviEnuma()) {
            g.drawString(clanEnuma.toString(), position.x + 5, textY);
            textY += fm.getHeight();
        }
    }
    @Override
    public boolean elementAt(DiagramElement element, Point pos) {
        Enuum interfejs = (Enuum) element;
        Rectangle bounds = new Rectangle(interfejs.getPosition(), interfejs.getSize());
        return bounds.contains(pos);
    }
}