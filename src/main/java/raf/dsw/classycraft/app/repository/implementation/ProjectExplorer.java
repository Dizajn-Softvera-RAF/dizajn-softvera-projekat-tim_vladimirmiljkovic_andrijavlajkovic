package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {
    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Project){
            Project projekat = (Project) child;
            if (!this.getChildren().contains(projekat)){
                this.getChildren().add(projekat);
            }
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        children.remove(child);
    }
}
