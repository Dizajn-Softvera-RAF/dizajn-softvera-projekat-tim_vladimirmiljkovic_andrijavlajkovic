package raf.dsw.classycraft.app.state;

import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.errorHandler.MessageType;
import raf.dsw.classycraft.app.gui.swing.view.*;
import raf.dsw.classycraft.app.model.*;

import java.awt.*;

@Setter
public class DodavanjeVezeState implements State{

    private Interclass startElement;
    private Point currentDragPoint;
    private Connection tempConnection;
    private String selectedType;




    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        Point clickPoint = new Point(x, y);
        startElement = null;
        for (ElementPainter painter : diagramView.getPainters()) {
            if (painter.elementAt(painter.getElement(), clickPoint) && painter.getElement() instanceof Interclass) {
                startElement = (Interclass) painter.getElement();
                currentDragPoint = clickPoint;
                tempConnection = new Connection("temp", null, startElement, null);
                diagramView.getPainters().add(new ConnectionPainter(tempConnection));
                break;
            }
        }
    }

    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        if (startElement != null) {
            currentDragPoint.setLocation(x, y);


            Interclass tempEndElement = new Interclass("temp", null) {
                @Override
                public Point getPosition() {
                    return currentDragPoint;
                }

                @Override
                public Dimension getSize() {
                    return new Dimension(0, 0);
                }
            };

            tempConnection.setToInterclass(tempEndElement);
            diagramView.repaint();
        }
    }

    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        Point releasePoint = new Point(x, y);
        Interclass endElement = null;
        for (ElementPainter painter : diagramView.getPainters()) {
            if (painter.elementAt(painter.getElement(), releasePoint) && painter.getElement() instanceof Interclass) {
                endElement = (Interclass) painter.getElement();
                break;
            }
        }

        if (startElement != null && endElement != null && endElement != startElement) {
            if (!vezaPostoji(startElement, endElement, diagramView)) {
                String connectionName = diagramView.getPackageView().generateElementName(diagramView.getDiagram(), selectedType);
                Connection newConnection = null;
                ConnectionPainter newPainter = null;

                switch (selectedType) {
                    case "Agregacija":
                        newConnection = new Agregacija(connectionName, diagramView.getDiagram(), startElement, endElement);
                        newPainter = new AgregacijaPainter(newConnection);
                        break;
                    case "Generalizacija":
                        newConnection = new Generalizacija(connectionName, diagramView.getDiagram(), startElement, endElement);
                        newPainter = new GeneralizacijaPainter(newConnection);
                        break;
                    case "Kompozicija":
                        newConnection = new Kompozicija(connectionName, diagramView.getDiagram(), startElement, endElement);
                        newPainter = new KompozicijaPainter(newConnection);
                        break;
                    case "Zavisnost":
                        newConnection = new Zavisnost(connectionName, diagramView.getDiagram(), startElement, endElement);
                        newPainter = new ZavisnostPainter(newConnection);
                        break;
                    default:
                        break;
                }
                diagramView.getPainters().add(newPainter);
                diagramView.getDiagram().addChild(newConnection);
                diagramView.repaint();

            }else{
                ApplicationFramework.getInstance().getMessageGenerator().createMessage("Veza vec postoji", MessageType.ERROR);
                diagramView.repaint();
            }
        }else{
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Niste povezali element", MessageType.WARNING);
            diagramView.repaint();
        }

        diagramView.getPainters().removeIf(p -> p.getElement() == tempConnection); //removeIf sklanja sve paintere koji su temp
        tempConnection = null;
        startElement = null;
        currentDragPoint = null;

    }


    private boolean vezaPostoji(Interclass from, Interclass to, DiagramView diagramView) {
        for (ElementPainter painter : diagramView.getPainters()) {
            if (painter.getElement() instanceof Connection) {
                Connection connection = (Connection) painter.getElement();
                if ((connection.getFromInterclass() == from && connection.getToInterclass() == to) ||
                        (connection.getFromInterclass() == to && connection.getToInterclass() == from)) {
                    return true;
                }
            }
        }
        return false;
    }

}
