package raf.dsw.classycraft.app.model;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public class Agregacija extends Connection {
    public Agregacija(String name, ClassyNode parent, Interclass fromInterclass, Interclass toInterclass) {
        super(name, parent, fromInterclass, toInterclass);
    }
}
