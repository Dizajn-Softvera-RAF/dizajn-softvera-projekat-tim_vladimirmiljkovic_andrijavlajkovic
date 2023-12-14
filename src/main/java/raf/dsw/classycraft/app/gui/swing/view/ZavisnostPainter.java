package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.Interclass;
import raf.dsw.classycraft.app.model.Zavisnost;

import java.awt.*;

public class ZavisnostPainter extends ConnectionPainter {
    public ZavisnostPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {
        if (element instanceof Zavisnost) {
            Zavisnost zavisnost = (Zavisnost) element;
            Interclass from = zavisnost.getFromInterclass();
            Interclass to = zavisnost.getToInterclass();

            String fromEdge = najblizaIvica(from, to);
            String toEdge = najblizaIvica(to, from);

            Point fromPoint = sredinaIvice(from, fromEdge);
            Point toPoint = sredinaIvice(to, toEdge);

            float[] dashPattern = {10, 10};

            Stroke originalStroke= g.getStroke();

            g.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));

            g.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);

            g.setStroke(originalStroke);

            g.setStroke(new BasicStroke(2));

            nacrtajPraznuStrelicu(g, toPoint, toEdge);

            g.setStroke(originalStroke);
        }
    }

    private void nacrtajPraznuStrelicu(Graphics2D g, Point toPoint, String edge) {
        int strelicaSize = 10;

        Point teme1, teme2;
        switch (edge) {
            case "bottom":
                teme1 = new Point(toPoint.x - strelicaSize, toPoint.y + strelicaSize);
                teme2 = new Point(toPoint.x + strelicaSize, toPoint.y + strelicaSize);
                break;
            case "left":
                teme1 = new Point(toPoint.x - strelicaSize, toPoint.y - strelicaSize);
                teme2 = new Point(toPoint.x - strelicaSize, toPoint.y + strelicaSize);
                break;
            case "right":
                teme1 = new Point(toPoint.x + strelicaSize, toPoint.y - strelicaSize);
                teme2 = new Point(toPoint.x + strelicaSize, toPoint.y + strelicaSize);
                break;
            default:
                teme1 = new Point(toPoint.x - strelicaSize, toPoint.y - strelicaSize);
                teme2 = new Point(toPoint.x + strelicaSize, toPoint.y - strelicaSize);
        }

        g.drawLine(toPoint.x, toPoint.y, teme1.x, teme1.y);
        g.drawLine(toPoint.x, toPoint.y, teme2.x, teme2.y);
    }
}

