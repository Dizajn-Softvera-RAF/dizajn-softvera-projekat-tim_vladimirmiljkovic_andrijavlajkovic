package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.Connection;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.Interclass;

import java.awt.*;

public class ConnectionPainter extends ElementPainter{
    public ConnectionPainter(DiagramElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics2D g, DiagramElement element) {
        if (element instanceof Connection) {
            Connection conn = (Connection) element;
            Interclass from = conn.getFromInterclass();
            Interclass to = conn.getToInterclass();

            String fromEdge = najblizaIvica(from, to);
            String toEdge = najblizaIvica(to, from);

            Point fromPoint = sredinaIvice(from, fromEdge);
            Point toPoint = sredinaIvice(to, toEdge);

            g.drawLine(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);
        }
    }

    @Override
    public boolean elementAt(DiagramElement element, Point clickPoint) {
        if (element instanceof Connection) {
            Connection conn = (Connection) element;
            Point fromPoint = sredinaIvice(conn.getFromInterclass(), najblizaIvica(conn.getFromInterclass(), conn.getToInterclass()));
            Point toPoint = sredinaIvice(conn.getToInterclass(), najblizaIvica(conn.getToInterclass(), conn.getFromInterclass()));

            int clickTolerance = 10;
            Rectangle clickArea = new Rectangle(clickPoint.x - clickTolerance, clickPoint.y - clickTolerance, 2 * clickTolerance, 2 * clickTolerance);

            return lineIntersectsRectangle(fromPoint, toPoint, clickArea);
        }
        return false;
    }

    private boolean lineIntersectsRectangle(Point lineStart, Point lineEnd, Rectangle rect) {
        return rect.intersectsLine(lineStart.x, lineStart.y, lineEnd.x, lineEnd.y);
    }

    protected String najblizaIvica(Interclass from, Interclass to) {
        Point fromCenter = new Point(from.getPosition().x + from.getSize().width / 2, from.getPosition().y + from.getSize().height / 2);
        Point toCenter = new Point(to.getPosition().x + to.getSize().width / 2, to.getPosition().y + to.getSize().height / 2);

        double dx = toCenter.x - fromCenter.x;
        double dy = toCenter.y - fromCenter.y;

        if (Math.abs(dx) > Math.abs(dy)) {
            return (dx > 0) ? "right" : "left";
        } else {
            return (dy > 0) ? "bottom" : "top";
        }
    }
    protected Point sredinaIvice(Interclass element, String edge) {
        Point position = element.getPosition();
        Dimension size = element.getSize();

        switch (edge) {
            case "top":
                return new Point(position.x + size.width / 2, position.y);
            case "bottom":
                return new Point(position.x + size.width / 2, position.y + size.height);
            case "left":
                return new Point(position.x, position.y + size.height / 2);
            case "right":
                return new Point(position.x + size.width, position.y + size.height / 2);
            default:
                return position;
        }
    }
}
