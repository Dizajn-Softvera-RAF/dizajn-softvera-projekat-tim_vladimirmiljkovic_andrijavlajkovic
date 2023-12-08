package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.ICPainter;
import raf.dsw.classycraft.app.model.Enuum;
import raf.dsw.classycraft.app.model.Interclass;
import raf.dsw.classycraft.app.model.Interfejs;
import raf.dsw.classycraft.app.model.Klasa;

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

    private void createAndAddElement(String elementType, int x, int y, DiagramView diagramView) {
        String elementName = diagramView.getPackageView().generateElementName(diagramView.getDiagram(), elementType);
        Color color = determineColor(elementType);
        Interclass element;

        switch (elementType) {
            case "Klasa":
                element = new Klasa(elementName, diagramView.getDiagram(), 1, color, new Point(x, y), new Dimension(100, 50));
                break;
            case "Enum":
                element = new Enuum(elementName, diagramView.getDiagram(), 1, color, new Point(x, y), new Dimension(100, 50));
                break;
            case "Interfejs":
                element = new Interfejs(elementName, diagramView.getDiagram(), 1, color, new Point(x, y), new Dimension(100, 50));
                break;
            default:
                throw new IllegalArgumentException("Nepoznat tip elementa: " + elementType);
        }

        ICPainter elementPainter = new ICPainter(element);
        diagramView.getPainters().add(elementPainter);
        diagramView.getDiagram().addChild(element);
        diagramView.repaint();
    }

    private Color determineColor(String type) {
        switch (type) {
            case "Klasa": return Color.BLUE; // Plava
            case "Enum": return Color.ORANGE; // Braon
            case "Interfejs": return Color.GREEN; // Zelena
            default: return Color.BLACK;
        }
    }
}
