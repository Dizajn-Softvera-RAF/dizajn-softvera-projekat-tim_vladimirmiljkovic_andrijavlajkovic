package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public class FactoryProject extends FactoryAbstract{

    public FactoryProject(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public ClassyNode createNode(ClassyNode parent) {
            String projectName = "Project";
            String author = "Author";
            String resourceFolderPath = "Path";
            return new Project(projectName, author, resourceFolderPath, parent);
    }


}
