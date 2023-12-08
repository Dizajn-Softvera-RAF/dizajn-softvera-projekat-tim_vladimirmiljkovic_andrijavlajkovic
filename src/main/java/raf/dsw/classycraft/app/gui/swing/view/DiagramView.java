package raf.dsw.classycraft.app.gui.swing.view;

import com.sun.tools.javac.Main;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {
    private Diagram diagram;
    private PackageView packageView;
    private List<ElementPainter> painters = new ArrayList<>();
    private List<ElementPainter> selectedModel = new ArrayList<>();
    public DiagramView(Diagram diagram,PackageView packageView) {
        this.diagram = diagram;
        this.packageView = packageView;
        MouseController mouseController = new MouseController(this);
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
    }


    @Override
    public void update(Notification notification) {
        repaint();
    }

    private class MouseController extends MouseAdapter {
        private DiagramView diagramView;
        public MouseController(DiagramView diagramView) {
            this.diagramView = diagramView;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            MainFrame.getInstance().getPackageView().misKliknut(e.getX(),e.getY(),diagramView);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            MainFrame.getInstance().getPackageView().misOtpusten(e.getX(),e.getY(),diagramView);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            MainFrame.getInstance().getPackageView().misPovucen(e.getX(), e.getY(), diagramView);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(ElementPainter p: painters){
            p.paint(g2,p.getElement());
        }
        for (ElementPainter p : selectedModel) {
            SelectedElementPainter selectedPainter = new SelectedElementPainter(p.getElement());
            selectedPainter.paint(g2, p.getElement());
        }

    }
}
