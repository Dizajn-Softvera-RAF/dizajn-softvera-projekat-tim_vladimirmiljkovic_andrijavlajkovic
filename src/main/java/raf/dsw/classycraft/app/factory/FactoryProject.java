package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Project;

public class FactoryProject extends FactoryAbstract{
    static int i=1;

    public FactoryProject(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public ClassyNode createNode(ClassyNode parent) {
            String projectName = "Project" + i++;
            String author = "Author" + i;
            String resourceFolderPath = "Path" + i;
            return new Project(projectName, author, resourceFolderPath, parent);
    }


}
