package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.errorHandler.MessageType;
import raf.dsw.classycraft.app.gui.swing.view.*;
import raf.dsw.classycraft.app.model.*;

import javax.swing.*;
import java.awt.*;

public class DodavanjeICOState implements State{

    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        String[] options = {"Klasa", "Enum", "Interfejs"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Izaberite tip elementa:",
                "Dodavanje elementa",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice != -1) {
            String selectedType = options[choice];
            createAndAddElement(selectedType, x, y, diagramView);
        }
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {

    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {

    }

    private void createAndAddElement(String elementType, int x, int y, DiagramView diagramView) {
        String elementName = diagramView.getPackageView().generateElementName(diagramView.getDiagram(), elementType);
        Dimension size = odrediVelicinuNaOsnovuTipa(elementType);

        if (intersectsWithOld(new Point(x, y), size, diagramView)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Novi element ne sme da se preklapa sa postojecim", MessageType.WARNING);
            return;
        }

        Interclass element = kreirajElement(elementType, elementName, diagramView);
        element.setPosition(new Point(x, y));
        element.setSize(size);

        ElementPainter elementPainter;
        switch (elementType) {
            case "Klasa":
                elementPainter = new KlasaPainter((Klasa) element);
                break;
            case "Enum":
                elementPainter = new EnuumPainter((Enuum) element);
                break;
            case "Interfejs":
                elementPainter = new InterfejsPainter((Interfejs) element);
                break;
            default:
                throw new IllegalArgumentException("Nepoznat tip elementa: " + elementType);
        }

        diagramView.getPainters().add(elementPainter);
        diagramView.getDiagram().addChild(element);
        diagramView.repaint();
    }


    private Dimension odrediVelicinuNaOsnovuTipa(String elementType) {
        switch (elementType) {
            case "Klasa":
                return new Dimension(75, 100);
            case "Enum":
                return new Dimension(75, 50);
            case "Interfejs":
                return new Dimension(75, 100);
            default:
                throw new IllegalArgumentException("Nepoznat tip elementa: " + elementType);
        }
    }

    private Interclass kreirajElement(String elementType, String elementName, DiagramView diagramView) {
        switch (elementType) {
            case "Klasa":
                return new Klasa(elementName, diagramView.getDiagram());
            case "Enum":
                return new Enuum(elementName, diagramView.getDiagram());
            case "Interfejs":
                return new Interfejs(elementName, diagramView.getDiagram());
            default:
                throw new IllegalArgumentException("Nepoznat tip elementa: " + elementType);
        }
    }

    private boolean intersectsWithOld(Point newPos, Dimension newSize, DiagramView diagramView) {
        Rectangle newBounds = new Rectangle(newPos, newSize);
        for (ElementPainter painter : diagramView.getPainters()) {
            DiagramElement element = painter.getElement();

            if (element instanceof Interclass) {
                Rectangle elementBounds = new Rectangle(((Interclass) element).getPosition(), ((Interclass) element).getSize());
                if (elementBounds.intersects(newBounds)) {
                    return true;
                }
            }
        }
        return false;
    }


}
