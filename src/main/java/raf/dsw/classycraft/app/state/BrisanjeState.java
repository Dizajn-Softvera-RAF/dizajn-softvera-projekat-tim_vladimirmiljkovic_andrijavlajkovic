package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementPainter;
import raf.dsw.classycraft.app.model.DiagramElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrisanjeState implements State{
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        boolean clickedOnSelectedElement = false;

        for (ElementPainter painter : diagramView.getSelectedModel()) {
            if (painter.elementAt(painter.getElement(), new Point(x, y))) {
                clickedOnSelectedElement = true;
                break;
            }
        }

        if (clickedOnSelectedElement) {
            for (ElementPainter selectedPainter : diagramView.getSelectedModel()) {
                DiagramElement selectedElement = selectedPainter.getElement();
                System.out.println("Brisanje selektovanog elementa: " + selectedElement.getName());
                diagramView.getDiagram().removeChild(selectedElement);
                diagramView.getPainters().remove(selectedPainter);
            }
            diagramView.getSelectedModel().clear();
        } else {
            Iterator<ElementPainter> painterIterator = diagramView.getPainters().iterator();
            while (painterIterator.hasNext()) {
                ElementPainter painter = painterIterator.next();
                if (painter.elementAt(painter.getElement(), new Point(x, y))) {
                    System.out.println("Brisanje elementa: " + painter.getElement().getName());
                    diagramView.getDiagram().removeChild(painter.getElement());
                    painterIterator.remove();
                    break;
                }
            }
        }
        diagramView.repaint();
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

    }
}
