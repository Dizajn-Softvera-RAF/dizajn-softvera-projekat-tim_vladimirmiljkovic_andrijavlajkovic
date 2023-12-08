package raf.dsw.classycraft.app.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import java.awt.*;
@Getter
@Setter
public class Connection extends DiagramElement{
    private Interclass fromInterclass;
    private Interclass toInterclass;

    public Connection(String name, ClassyNode parent, Interclass fromInterclass) {
        super(name, parent);
        this.fromInterclass=fromInterclass;



    }
}
