package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

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
        if (child != null &&  child instanceof Package){
            Package paket = (Package) child;
            if (!this.getChildren().contains(paket)){
                this.getChildren().add(paket);
            }
        }
    }
    @Override
    public void removeChild(ClassyNode child) {
        children.remove(child);
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
