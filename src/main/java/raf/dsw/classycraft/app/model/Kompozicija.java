package raf.dsw.classycraft.app.model;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public class Kompozicija extends Connection{
    public Kompozicija(String name, ClassyNode parent, Interclass fromInterclass, Interclass toInterclass) {
        super(name, parent, fromInterclass, toInterclass);
    }
}
