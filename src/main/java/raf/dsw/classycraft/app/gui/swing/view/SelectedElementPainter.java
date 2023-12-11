package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.model.Connection;
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
        } else if (element instanceof Connection) {
            Connection conn = (Connection) element;
            Point fromPoint = sredinaIvice(conn.getFromInterclass(), najblizaIvica(conn.getFromInterclass(), conn.getToInterclass()));
            Point toPoint = sredinaIvice(conn.getToInterclass(), najblizaIvica(conn.getToInterclass(), conn.getFromInterclass()));

            int minX = Math.min(fromPoint.x, toPoint.x);
            int minY = Math.min(fromPoint.y, toPoint.y);
            int width = Math.abs(fromPoint.x - toPoint.x);
            int height = Math.abs(fromPoint.y - toPoint.y);

            return new Rectangle(minX, minY, width, height);
        }

        return null;
    }

    @Override
    public boolean elementAt(DiagramElement element, Point pos) {

        return false;
    }
    private Point sredinaIvice(Interclass element, String edge) {
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

    private String najblizaIvica(Interclass from, Interclass to) {
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
}
