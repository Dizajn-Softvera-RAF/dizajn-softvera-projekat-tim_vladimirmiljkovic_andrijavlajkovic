package raf.dsw.classycraft.app.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import java.awt.*;
@Getter
@Setter
public class Interfejs extends Interclass{
    public Interfejs(String name, ClassyNode parent) {
        super(name, parent);
    }
}
