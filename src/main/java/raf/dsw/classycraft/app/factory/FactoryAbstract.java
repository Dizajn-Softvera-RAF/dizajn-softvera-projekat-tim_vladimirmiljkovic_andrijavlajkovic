package raf.dsw.classycraft.app.factory;


import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public abstract class FactoryAbstract extends ClassyNode {

    public FactoryAbstract(String name, ClassyNode parent) {
        super(name, parent);
    }

    public ClassyNode getClassyNode(ClassyNode parent) {
        ClassyNode c = createNode(parent);
        c.setParent(parent);
        return c;
    }

    public abstract ClassyNode createNode(ClassyNode parent);


}
