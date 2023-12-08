package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementPainter;
import raf.dsw.classycraft.app.model.Connection;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.Interclass;

import java.awt.*;

public class SelekcijaState implements State{
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        for (ElementPainter painter : diagramView.getPainters()) {
            DiagramElement element = painter.getElement();
            if (element instanceof Interclass && painter.elementAt(element, new Point(x, y))
                    || element instanceof Connection && painter.elementAt(element, new Point(x, y))) {
                if (diagramView.getSelectedModel().contains(painter)) {
                    diagramView.getSelectedModel().remove(painter);
                } else {
                    diagramView.getSelectedModel().add(painter);
                }
                diagramView.repaint();
                break;
            }
        }



    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

    }
}
