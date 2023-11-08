package raf.dsw.classycraft.app.repository;

import raf.dsw.classycraft.app.core.ClassyRepository;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public class ClassyRepositoryImplementation implements ClassyRepository {
    private ProjectExplorer root;

    public ClassyRepositoryImplementation() {
        root = new ProjectExplorer("My Project Explorer");
    }
    @Override
    public ProjectExplorer getRoot() {
        return root;
    }

    @Override
    public void addChild(ClassyNodeComposite parent, ClassyNode child) {

    }


}
