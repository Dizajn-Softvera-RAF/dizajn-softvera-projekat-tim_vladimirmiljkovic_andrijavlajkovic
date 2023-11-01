package repository.implementation;

import repository.composite.ClassyNode;
import repository.composite.ClassyNodeComposite;

public class Project extends ClassyNodeComposite {

    private String author;
    private String resourceFolderPath;

    public Project(String name, String author, String resourceFolderPath, ClassyNode parent) {
        super(name, parent);
        this.author = author;
        this.resourceFolderPath = resourceFolderPath;
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
