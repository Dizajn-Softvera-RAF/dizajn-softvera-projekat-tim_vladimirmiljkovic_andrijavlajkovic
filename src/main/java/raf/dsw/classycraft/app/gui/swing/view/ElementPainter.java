package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.DiagramElement;

import java.awt.*;
@Getter
@Setter
public abstract class ElementPainter {
    private DiagramElement element;

    public ElementPainter(DiagramElement element) {
        this.element = element;
    }

    public abstract void paint(Graphics2D g, DiagramElement element);

    public abstract boolean elementAt(DiagramElement element, Point pos);


}
