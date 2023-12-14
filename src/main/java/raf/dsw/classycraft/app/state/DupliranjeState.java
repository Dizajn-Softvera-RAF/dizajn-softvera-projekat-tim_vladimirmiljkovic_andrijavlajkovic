package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.*;
import raf.dsw.classycraft.app.model.*;

import java.awt.*;
import java.util.ArrayList;

public class DupliranjeState implements State{
    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        for (ElementPainter selectedPainter : diagramView.getSelectedModel()) {
            if (selectedPainter.getElement() instanceof Interclass) {
                Interclass selectedElement = (Interclass) selectedPainter.getElement();

                if (selectedElement.getPosition() == null || selectedElement.getSize() == null) {
                    System.out.println("Element nema validnu poziciju ili veličinu.");
                    return;
                }

                Interclass duplicatedElement = duplirajInterclass(selectedElement, diagramView);

                adjustPositionForDuplicate(duplicatedElement, diagramView);

                ElementPainter duplicatedPainter = napraviPainterZaInterclass(duplicatedElement);
                diagramView.getPainters().add(duplicatedPainter);
                diagramView.getDiagram().addChild(duplicatedElement);
            }
        }
        diagramView.repaint();
    }

    private void adjustPositionForDuplicate(Interclass duplicatedElement, DiagramView diagramView) {
        Rectangle duplicatedBounds = new Rectangle(duplicatedElement.getPosition(), duplicatedElement.getSize());
        boolean intersects;
        do {
            intersects = false;
            for (ElementPainter otherPainter : diagramView.getPainters()) {
                if (otherPainter.getElement() instanceof Interclass && otherPainter.getElement() != duplicatedElement) {
                    Interclass otherElement = (Interclass) otherPainter.getElement();
                    Rectangle otherBounds = new Rectangle(otherElement.getPosition(), otherElement.getSize());
                    if (duplicatedBounds.intersects(otherBounds)) {
                        Point newPosition = calculateNewPosition(duplicatedBounds, otherBounds);
                        duplicatedBounds.setLocation(newPosition);
                        intersects = true;
                    }
                }
            }
        } while (intersects);
        duplicatedElement.setPosition(duplicatedBounds.getLocation());
    }

    private Interclass duplirajInterclass(Interclass original, DiagramView diagramView) {
        Interclass duplikat = null;
        if (original instanceof Klasa) {
            duplikat = new Klasa(original.getName() + diagramView.getPackageView().generateElementName(diagramView.getDiagram(),"KlasaKopija"), original.getParent());
            ((Klasa) duplikat).setAtributi(new ArrayList<>(((Klasa)original).getAtributi()));
            ((Klasa) duplikat).setMetode(new ArrayList<>(((Klasa)original).getMetode()));
        } else if (original instanceof Interfejs) {
            duplikat = new Interfejs(original.getName() + diagramView.getPackageView().generateElementName(diagramView.getDiagram(),"InterfejsKopija"), original.getParent());
            ((Interfejs) duplikat).setMetode(new ArrayList<>(((Interfejs) original).getMetode()));
        } else if (original instanceof Enuum) {
            duplikat = new Enuum(original.getName() + diagramView.getPackageView().generateElementName(diagramView.getDiagram(),"EnuumKopija"), original.getParent());
            ((Enuum) duplikat).setClanoviEnuma(new ArrayList<>(((Enuum) original).getClanoviEnuma()));
        }
        if (duplikat != null) {
            duplikat.setPosition(new Point(original.getPosition().x, original.getPosition().y));
            duplikat.setSize(new Dimension(original.getSize().width, original.getSize().height));
        }
        return duplikat;
    }

    private ElementPainter napraviPainterZaInterclass(Interclass interclass) {
        if (interclass instanceof Klasa) {
            return new KlasaPainter((Klasa) interclass);
        } else if (interclass instanceof Interfejs) {
            return new InterfejsPainter((Interfejs) interclass);
        } else if (interclass instanceof Enuum) {
            return new EnuumPainter((Enuum) interclass);
        }
        return null;
    }
    private Point calculateNewPosition(Rectangle selectedBounds, Rectangle otherBounds) {
        int newX = otherBounds.x + otherBounds.width;
        int newY = selectedBounds.y;
        return new Point(newX, newY);
    }
    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

    }

}
