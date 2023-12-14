package raf.dsw.classycraft.app.model;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public class Generalizacija extends Connection{
    public Generalizacija(String name, ClassyNode parent, Interclass fromInterclass, Interclass toInterclass) {
        super(name, parent, fromInterclass, toInterclass);
    }
}
