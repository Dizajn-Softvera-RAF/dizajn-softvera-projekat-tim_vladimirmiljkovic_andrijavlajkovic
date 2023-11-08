package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

public class Package extends ClassyNodeComposite {

    private String author;
    private String resourceFolderPath;

    public Package(String name, String author, String resourceFolderPath, ClassyNode parent) {
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
