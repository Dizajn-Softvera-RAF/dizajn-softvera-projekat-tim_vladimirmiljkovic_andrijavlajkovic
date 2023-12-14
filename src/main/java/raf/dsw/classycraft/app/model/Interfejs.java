package raf.dsw.classycraft.app.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Interfejs extends Interclass{
    private List<Metod> metode = new ArrayList<>();
    public Interfejs(String name, ClassyNode parent) {
        super(name, parent);
    }
}
