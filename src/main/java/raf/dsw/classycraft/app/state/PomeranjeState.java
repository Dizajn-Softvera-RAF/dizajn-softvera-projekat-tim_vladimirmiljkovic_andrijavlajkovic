package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementPainter;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.Interclass;

import java.awt.*;

public class PomeranjeState implements State {
    private Point lastDragPoint;

    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        lastDragPoint = new Point(x, y);
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        int dx = x - lastDragPoint.x;
        int dy = y - lastDragPoint.y;

        for (ElementPainter painter : diagramView.getSelectedModel()) {
            DiagramElement element = painter.getElement();
            if (element instanceof Interclass) {
                Interclass interclass = (Interclass) element;
                Point currentPosition = interclass.getPosition();
                interclass.setPosition(new Point(currentPosition.x + dx, currentPosition.y + dy));
            }
        }

        lastDragPoint = new Point(x, y);
        diagramView.repaint();
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        for (ElementPainter selectedPainter : diagramView.getSelectedModel()) {
            if (selectedPainter.getElement() instanceof Interclass) {
                Interclass selectedElement = (Interclass) selectedPainter.getElement();
                Rectangle selectedBounds = new Rectangle(selectedElement.getPosition(), selectedElement.getSize());

                boolean intersects;
                do {
                    intersects = false;
                    for (ElementPainter otherPainter : diagramView.getPainters()) {
                        if (otherPainter.getElement() instanceof Interclass && otherPainter != selectedPainter) {
                            Interclass otherElement = (Interclass) otherPainter.getElement();
                            Rectangle otherBounds = new Rectangle(otherElement.getPosition(), otherElement.getSize());
                            if (selectedBounds.intersects(otherBounds)) {
                                Point newPosition = calculateNewPosition(selectedBounds, otherBounds);
                                selectedBounds.setLocation(newPosition);
                                intersects = true;
                            }
                        }
                    }
                } while (intersects);

                selectedElement.setPosition(selectedBounds.getLocation());
            }
        }
        diagramView.repaint();
    }

    private Point calculateNewPosition(Rectangle selectedBounds, Rectangle otherBounds) {
        int newX = otherBounds.x + otherBounds.width;
        int newY = selectedBounds.y;
        return new Point(newX, newY);
    }
}

