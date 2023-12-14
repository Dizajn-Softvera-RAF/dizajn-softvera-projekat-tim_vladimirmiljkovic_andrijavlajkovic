package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.Interclass;
import raf.dsw.classycraft.app.model.Kompozicija;

import java.awt.*;

public class KompozicijaPainter extends ConnectionPainter {
    public KompozicijaPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {
        if (element instanceof Kompozicija) {
            Kompozicija kompozicija = (Kompozicija) element;
            Interclass from = kompozicija.getFromInterclass();
            Interclass to = kompozicija.getToInterclass();

            String fromEdge = najblizaIvica(from, to);
            String toEdge = najblizaIvica(to, from);

            Point fromPoint = sredinaIvice(from, fromEdge);
            Point toPoint = sredinaIvice(to, toEdge);

            Stroke originalStroke= g.getStroke();

            g.setStroke(new BasicStroke(2));

            g.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);

            nacrtajPopunjenRomb(g, fromPoint, fromEdge);

            g.setStroke(originalStroke);
        }
    }

    private void nacrtajPopunjenRomb(Graphics2D g, Point fromPoint, String edge) {
        int velicinaRomba = 10;

        Point teme2, teme3, teme4;

        switch (edge) {
            case "bottom":
                teme2 = new Point(fromPoint.x - velicinaRomba, fromPoint.y + velicinaRomba);
                teme3 = new Point(fromPoint.x, fromPoint.y + 2 * velicinaRomba);
                teme4 = new Point(fromPoint.x + velicinaRomba, fromPoint.y + velicinaRomba);
                break;
            case "left":
                teme2 = new Point(fromPoint.x - velicinaRomba, fromPoint.y - velicinaRomba);
                teme3 = new Point(fromPoint.x - 2 * velicinaRomba, fromPoint.y);
                teme4 = new Point(fromPoint.x - velicinaRomba, fromPoint.y + velicinaRomba);
                break;
            case "right":
                teme2 = new Point(fromPoint.x + velicinaRomba, fromPoint.y - velicinaRomba);
                teme3 = new Point(fromPoint.x + 2 * velicinaRomba, fromPoint.y);
                teme4 = new Point(fromPoint.x + velicinaRomba, fromPoint.y + velicinaRomba);
                break;
            default:
                teme2 = new Point(fromPoint.x - velicinaRomba, fromPoint.y - velicinaRomba);
                teme3 = new Point(fromPoint.x, fromPoint.y - 2 * velicinaRomba);
                teme4 = new Point(fromPoint.x + velicinaRomba, fromPoint.y - velicinaRomba);
        }

        int[] xPoints = {fromPoint.x, teme2.x, teme3.x, teme4.x};
        int[] yPoints = {fromPoint.y, teme2.y, teme3.y, teme4.y};

        g.fillPolygon(xPoints, yPoints, 4);
        g.drawPolygon(xPoints, yPoints, 4);
    }
}

