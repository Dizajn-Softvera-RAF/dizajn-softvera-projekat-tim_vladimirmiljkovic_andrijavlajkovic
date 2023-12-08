package raf.dsw.classycraft.app.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import java.awt.*;
import java.util.List;
@Getter
@Setter
public class Interclass extends DiagramElement{
    private String vidljivost;
    private Point position;
    private Dimension size;
    private List<ClassContent> classContents;

    public Interclass(String name, ClassyNode parent, int stroke, Color color, Point position, Dimension size) {
        super(name, parent, stroke, color);
        this.position=position;
        this.size=size;
    }
}

