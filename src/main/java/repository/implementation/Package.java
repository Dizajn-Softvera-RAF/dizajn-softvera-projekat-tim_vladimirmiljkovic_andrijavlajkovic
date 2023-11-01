package repository.implementation;

import repository.composite.ClassyNode;
import repository.composite.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {
    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        children.add(child);
    }

    @Override
    public void removeChild(ClassyNode child) {
        children.remove(child);
    }
}
