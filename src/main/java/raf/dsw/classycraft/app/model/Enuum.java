package raf.dsw.classycraft.app.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Enuum extends Interclass{
    private List<ClanEnuma> clanoviEnuma = new ArrayList<>();
    public Enuum(String name, ClassyNode parent) {
        super(name, parent);
    }
}
