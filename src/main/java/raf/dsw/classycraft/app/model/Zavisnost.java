package raf.dsw.classycraft.app.model;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public class Zavisnost extends Connection{
    public Zavisnost(String name, ClassyNode parent, Interclass fromInterclass, Interclass toInterclass) {
        super(name, parent, fromInterclass, toInterclass);
    }
}
