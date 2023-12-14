package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.Generalizacija;
import raf.dsw.classycraft.app.model.Interclass;

import java.awt.*;

public class GeneralizacijaPainter extends ConnectionPainter {
    public GeneralizacijaPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {
        if (element instanceof Generalizacija) {
            Generalizacija generalizacija = (Generalizacija) element;
            Interclass from = generalizacija.getFromInterclass();
            Interclass to = generalizacija.getToInterclass();

            String fromEdge = najblizaIvica(from, to);
            String toEdge = najblizaIvica(to, from);

            Point fromPoint = sredinaIvice(from, fromEdge);
            Point toPoint = sredinaIvice(to, toEdge);

            Stroke originalStroke= g.getStroke();

            g.setStroke(new BasicStroke(2));

            g.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);

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
        g.drawLine(teme1.x, teme1.y, teme2.x, teme2.y);
    }
}
