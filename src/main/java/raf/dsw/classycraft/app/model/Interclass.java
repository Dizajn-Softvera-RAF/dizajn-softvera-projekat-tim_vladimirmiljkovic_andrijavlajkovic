package raf.dsw.classycraft.app.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.observer.NotificationType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

import java.awt.*;
import java.util.List;
@Getter
@Setter
public class Interclass extends DiagramElement{
    private String vidljivost;
    private Point position;
    private Dimension size;
    private List<ClassContent> classContents;

    public Interclass(String name, ClassyNode parent) {
        super(name, parent);
    }

    public void setPosition(Point newPosition) {
        this.position = newPosition;
        Diagram parentDiagram = findParentDiagram();
        if (parentDiagram != null) {
            parentDiagram.notifySubscribers(new Notification(this, NotificationType.POSITION_CHANGED));
        }
    }
}

