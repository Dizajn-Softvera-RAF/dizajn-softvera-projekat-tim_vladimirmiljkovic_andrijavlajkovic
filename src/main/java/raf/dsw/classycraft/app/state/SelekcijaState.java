package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.LassoPainter;
import raf.dsw.classycraft.app.model.Connection;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.Interclass;

import java.awt.*;

public class SelekcijaState implements State{

    private Point startDragPoint;
    private Rectangle selectionRectangle;

    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        boolean elementSelected = false;
        for (ElementPainter painter : diagramView.getPainters()) {
            DiagramElement element = painter.getElement();
            if ((element instanceof Interclass || element instanceof Connection)
                    && painter.elementAt(element, new Point(x, y))) {
                if (diagramView.getSelectedModel().contains(painter)) {
                    diagramView.getSelectedModel().remove(painter);
                } else {
                    diagramView.getSelectedModel().add(painter);
                }
                elementSelected = true;
                break;
            }
        }

        if (!elementSelected) {
            diagramView.getSelectedModel().clear();
        }
        diagramView.repaint();
    }


    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        if (startDragPoint == null) {
            startDragPoint = new Point(x, y);
        }

        int minX = Math.min(startDragPoint.x, x);
        int minY = Math.min(startDragPoint.y, y);
        int width = Math.abs(startDragPoint.x - x);
        int height = Math.abs(startDragPoint.y - y);

        selectionRectangle = new Rectangle(minX, minY, width, height);
        diagramView.getPainters().removeIf(p -> p instanceof LassoPainter);
        diagramView.getPainters().add(new LassoPainter(selectionRectangle));
        lassoSelekcija(diagramView, false);
        diagramView.repaint();
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        if (selectionRectangle != null) {
            lassoSelekcija(diagramView, true);
            diagramView.getPainters().removeIf(p -> p instanceof LassoPainter);

            selectionRectangle = null;
            startDragPoint = null;
        }
        diagramView.repaint();
    }

    private void lassoSelekcija(DiagramView diagramView, boolean finalizeSelection) {
        for (ElementPainter painter : diagramView.getPainters()) {
            DiagramElement element = painter.getElement();
            if (isElementInRectangle(painter, element, selectionRectangle)) {
                if (!diagramView.getSelectedModel().contains(painter)) {
                    diagramView.getSelectedModel().add(painter);
                }
            } else if (!finalizeSelection && diagramView.getSelectedModel().contains(painter)) {
                diagramView.getSelectedModel().remove(painter);
            }
        }
    }

    private boolean isElementInRectangle(ElementPainter painter, DiagramElement element, Rectangle selectionRectangle) {
        for (int x = selectionRectangle.x; x < selectionRectangle.x + selectionRectangle.width; x++) {
            for (int y = selectionRectangle.y; y < selectionRectangle.y + selectionRectangle.height; y++) {
                if (painter.elementAt(element, new Point(x, y))) {
                    return true;
                }
            }
        }
        return false;
    }
}


