package raf.dsw.classycraft.app.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import java.awt.*;
import java.util.List;
@Getter
@Setter
public class Klasa extends Interclass {


    public Klasa(String name, ClassyNode parent) {
        super(name, parent);
    }

}
