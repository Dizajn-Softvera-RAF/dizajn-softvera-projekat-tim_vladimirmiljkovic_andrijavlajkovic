package raf.dsw.classycraft.app.factory;

import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;

import java.util.Random;

public class FactoryPackage extends FactoryAbstract{
    public FactoryPackage(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public ClassyNode createNode(ClassyNode parent) {
        String packageName = "Package";
        return new Package(packageName, parent);
    }
}
