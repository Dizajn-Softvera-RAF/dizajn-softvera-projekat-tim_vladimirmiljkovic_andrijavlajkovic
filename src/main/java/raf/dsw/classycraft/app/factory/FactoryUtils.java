package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public class FactoryUtils {

    public static FactoryAbstract getFactory(ClassyNode parent){
        if (parent instanceof ProjectExplorer) {
            return new FactoryProject("Project", parent);
        }
        else if (parent instanceof Project) {
            return new FactoryPackage("Package", parent);
        }
        else if (parent instanceof Package) {
            return new FactoryDiagram("Diagram", parent);
        }
        return null;
    }
}
