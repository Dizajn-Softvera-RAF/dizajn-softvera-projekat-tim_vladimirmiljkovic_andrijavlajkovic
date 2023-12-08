package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

public interface State {
    void misKliknut(int x, int y, DiagramView diagramView);
    void misPovucen(int x, int y, DiagramView diagramView);
    void misOtpusten(int x, int y, DiagramView diagramView);

}
