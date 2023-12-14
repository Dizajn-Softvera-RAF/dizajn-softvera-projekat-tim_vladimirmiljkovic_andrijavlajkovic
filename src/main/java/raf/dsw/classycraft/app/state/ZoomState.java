package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ZoomState implements State {
    private double scaleFactor = 1.0;
    private Point startDragPoint;

    @Override
    public void misKliknut(int x, int y, DiagramView diagramView) {
        startDragPoint = new Point(x, y);
    }
    @Override
    public void misPovucen(int x, int y, DiagramView diagramView) {
        if (startDragPoint == null) {
            return;
        }

        int dx = x - startDragPoint.x;
        double zoomFactor = 1.0;

        if (dx > 0) {
            zoomFactor = 1.003;
        } else if (dx < 0) {
            zoomFactor = 0.997;
        }

        scaleFactor *= zoomFactor;

        AffineTransform transform = diagramView.getTransform();
        transform.setToScale(scaleFactor, scaleFactor);
        diagramView.setTransform(transform);
        diagramView.repaint();
    }


    @Override
    public void misOtpusten(int x, int y, DiagramView diagramView) {
        startDragPoint = null;
    }



}
