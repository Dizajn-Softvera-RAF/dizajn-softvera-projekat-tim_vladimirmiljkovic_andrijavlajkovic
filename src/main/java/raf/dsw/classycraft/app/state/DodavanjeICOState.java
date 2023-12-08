package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.errorHandler.MessageType;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.view.ICPainter;
import raf.dsw.classycraft.app.model.*;

import javax.swing.*;
import java.awt.*;

public class DodavanjeICOState implements State{


//    @Override
//    public void misKliknut(int x, int y, DiagramView diagramView) {
//       Klasa klasa=new Klasa("KLASA",diagramView.getDiagram(),1,Color.BLUE,new Point(x,y),new Dimension(100,50));
//       ICPainter klasaPainter = new ICPainter(klasa);
//       diagramView.getPainters().add(klasaPainter);
//       diagramView.getDiagram().addChild(klasa);
//    }
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

        ICPainter elementPainter = new ICPainter(element);
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
        for (ElementPainter painter : diagramView.getPainters()) {
            Interclass element = (Interclass) painter.getElement();
            Rectangle oldBounds = new Rectangle(element.getPosition(), element.getSize());
            Rectangle newBounds = new Rectangle(newPos, newSize);
            if (oldBounds.intersects(newBounds)) {
                return true;
            }
        }
        return false;
    }

}
