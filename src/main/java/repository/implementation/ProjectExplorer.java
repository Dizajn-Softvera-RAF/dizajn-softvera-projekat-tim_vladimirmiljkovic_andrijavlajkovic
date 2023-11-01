package repository.implementation;

import repository.composite.ClassyNode;
import repository.composite.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {
    public ProjectExplorer(String name) {
        super(name, null);
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
