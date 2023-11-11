package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

public class FactoryDiagram extends FactoryAbstract{
    public FactoryDiagram(String name, ClassyNode parent) {
        super(name, parent);
    }
    static int i=1;


    @Override
    public ClassyNode createNode(ClassyNode parent) {
        String diagramName = "Diagram" + i++;
        return new Diagram(diagramName, parent);
    }
}
