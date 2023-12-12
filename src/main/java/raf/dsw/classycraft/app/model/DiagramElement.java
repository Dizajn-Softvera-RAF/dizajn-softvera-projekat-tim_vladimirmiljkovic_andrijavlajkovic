package raf.dsw.classycraft.app.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

import java.awt.*;
@Getter
@Setter
public abstract class DiagramElement extends ClassyNode {

    Diagram diagram;

    private Color color;
    private int stroke;
    //private String description;
    public DiagramElement(String name, ClassyNode parent) {
        super(name, parent);

    }

}
